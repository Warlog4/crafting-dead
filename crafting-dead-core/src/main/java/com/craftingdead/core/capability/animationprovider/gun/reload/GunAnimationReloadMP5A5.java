package com.craftingdead.core.capability.animationprovider.gun.reload;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class GunAnimationReloadMP5A5 extends GunAnimationReload {

  protected float rotation1 = 0F;
  protected float lastRotation1 = 0F;
  protected float maxRotation1 = 20;

  protected float trans1 = -1F;
  protected float lastTrans1 = 0F;
  protected float maxTrans1 = 0.3F;

  protected boolean up = true;

  protected float armMovement = 0.3F;

  /**
   * When set to false, the clip will be loaded into the gun
   */
  public boolean ejectingClip = false;
  
  private int ticks;

  public GunAnimationReloadMP5A5() {
    this.ejectingClip = true;
    trans1 = 0F;
  }

  public GunAnimationReloadMP5A5(boolean par1) {
    this.ejectingClip = par1;
    trans1 = this.ejectingClip ? 0F : -1F;
  }

  public void setEjectingClip(boolean par1) {
    this.ejectingClip = par1;
    trans1 = this.ejectingClip ? 0F : -1F;
  }

  @Override
  public void onUpdate(Minecraft par1, LivingEntity par2, ItemStack par3, float progress) {
    ticks++;
    
    if (progress * this.getMaxAnimationTick() >= 20) {
      up = false;
    }

    lastRotation1 = rotation1;
    lastTrans1 = trans1;

    float roation1Speed = 5F;
    float transSpeed = 0.1F;

    if (this.ejectingClip) {
      trans1 -= transSpeed;
    } else {
      trans1 += transSpeed;

      if (trans1 > 0) {
        trans1 = 0;
      }
    }

    if (up) {
      rotation1 += roation1Speed;
    } else {
      rotation1 -= roation1Speed;
    }

    if (rotation1 > maxRotation1) {
      rotation1 = maxRotation1;
    }

    if (rotation1 < 0) {
      rotation1 = 0;
    }
  }

  @Override
  public void doRenderHand(ItemStack par1, float par2, boolean par3, MatrixStack matrixStack) {
    if (par3) {
      float progress = (lastRotation1 + (rotation1 - lastRotation1) * par2);
      if (!this.ejectingClip) {
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(-progress * 0.4F));
      }
    } else {
      float transprogress = lastTrans1 + (trans1 - lastTrans1) * par2;
      matrixStack.translate(-transprogress, transprogress, transprogress);
    }

    if (!this.ejectingClip && !par3 && ticks >= (getMaxAnimationTick() / 2) - 10) {

      this.armMovement -= 0.015F;

      matrixStack.translate((this.armMovement - 0.20F), this.armMovement + 0.35F, 0.1F);
    }
  }

  @Override
  public void doRender(ItemStack par1, float par2, MatrixStack matrixStack) {

    float progress = (lastRotation1 + (rotation1 - lastRotation1) * par2);

    matrixStack.rotate(new Vector3f((!this.ejectingClip ? -4.0F : 4.0F),
        (!this.ejectingClip ? -0.5F : 0.0F), (!this.ejectingClip ? 2.0F : 0.2F))
            .rotationDegrees(-progress));

    if (!this.ejectingClip) {
      matrixStack.translate(0, (-progress / 75), 0);
    } else {
      matrixStack.rotate(Vector3f.XP.rotationDegrees(progress));
    }
  }

  public void doRenderAmmo(ItemStack par1, float par2, MatrixStack matrixStack) {
    if (this.ejectingClip) {
      float transprogress = lastTrans1 + (trans1 - lastTrans1) * par2;
      matrixStack.translate(0, -transprogress, 0);
    } else {
      float transprogress = lastTrans1 + (trans1 - lastTrans1) * par2;
      matrixStack.translate(transprogress, -transprogress, 0);
    }
  }

  @Override
  public void onAnimationStopped(ItemStack par1) {}

  @Override
  public float getMaxAnimationTick() {
    return 40;
  }
}