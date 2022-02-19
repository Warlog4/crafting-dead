/*
 * Crafting Dead
 * Copyright (C) 2021  NexusNode LTD
 *
 * This Non-Commercial Software License Agreement (the "Agreement") is made between you (the "Licensee") and NEXUSNODE (BRAD HUNTER). (the "Licensor").
 * By installing or otherwise using Crafting Dead (the "Software"), you agree to be bound by the terms and conditions of this Agreement as may be revised from time to time at Licensor's sole discretion.
 *
 * If you do not agree to the terms and conditions of this Agreement do not download, copy, reproduce or otherwise use any of the source code available online at any time.
 *
 * https://github.com/nexusnode/crafting-dead/blob/1.18.x/LICENSE.txt
 *
 * https://craftingdead.net/terms.php
 */

package com.craftingdead.core.world.action.reload;

import com.craftingdead.core.CraftingDead;
import com.craftingdead.core.client.animation.Animation;
import com.craftingdead.core.world.action.ActionType;
import com.craftingdead.core.world.action.TimedAction;
import com.craftingdead.core.world.entity.extension.LivingExtension;
import com.craftingdead.core.world.item.gun.Gun;
import com.craftingdead.core.world.item.gun.GunAnimationEvent;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractReloadAction extends TimedAction<ActionType> {

  protected final ItemStack gunStack;

  protected final Gun gun;

  protected final ItemStack oldMagazineStack;

  @Nullable
  private Animation animation;

  public AbstractReloadAction(ActionType type, LivingExtension<?, ?> performer,
      @Nullable LivingExtension<?, ?> target) {
    super(type, performer, target);
    this.gunStack = performer.getMainHandItem();
    this.gun = this.gunStack.getCapability(Gun.CAPABILITY)
        .orElseThrow(() -> new IllegalStateException("Performer not holding gun"));
    this.oldMagazineStack = this.gun.getAmmoProvider().getMagazineStack();
  }

  @Override
  protected int getTotalDurationTicks() {
    return this.gun.getReloadDurationTicks() + CraftingDead.serverConfig.reloadDuration.get();
  }

  @Override
  public boolean start() {
    if (this.getPerformer().getEntity().isSprinting()) {
      return false;
    }

    if (this.gun.isPerformingSecondaryAction()) {
      this.gun.setPerformingSecondaryAction(this.getPerformer(), false, false);
    }

    this.gun.getReloadSound()
        .ifPresent(sound -> this.getPerformer().getLevel().playSound(null,
            this.getPerformer().getEntity(), sound, SoundSource.PLAYERS, 1.0F, 1.0F));

    if (this.getPerformer().getLevel().isClientSide()) {
      this.animation = this.gun.getClient().getAnimation(GunAnimationEvent.RELOAD);
      this.gun.getClient().getAnimationController().addAnimation(this.animation);
    }

    return true;
  }

  protected abstract void loadNewMagazineStack(boolean displayOnly);

  @Override
  public boolean tick() {
    if (!this.getPerformer().getLevel().isClientSide()
        && this.getPerformer().getEntity().isSprinting()) {
      this.getPerformer().cancelAction(true);
      return false;
    }
    return super.tick();
  }

  @Override
  protected void finish() {
    if (this.getPerformer().getLevel().isClientSide()) {
      return;
    }
    // This will be synced to the client by the gun.
    this.loadNewMagazineStack(false);
  }

  @Override
  public void cancel() {
    super.cancel();
    if (this.getPerformer().getLevel().isClientSide()) {
      if (this.gun.getReloadSound().isPresent()) {
        // Stop reload sound
        Minecraft.getInstance().getSoundManager()
            .stop(this.gun.getReloadSound().get().getRegistryName(), SoundSource.PLAYERS);
      }
      if (this.animation != null) {
        this.animation.remove();
      }
    }

    // Revert all changes as we've been cancelled
    this.revert();
  }

  protected abstract void revert();
}
