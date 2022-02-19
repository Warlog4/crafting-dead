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

package com.craftingdead.core.client.renderer.entity.grenade;

import com.craftingdead.core.client.model.geom.ModModelLayers;
import com.craftingdead.core.world.entity.grenade.Grenade;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CylinderGrenadeRenderer extends EntityRenderer<Grenade> {

  private final ModelPart model;

  public CylinderGrenadeRenderer(EntityRendererProvider.Context context) {
    super(context);
    this.model = context.bakeLayer(ModModelLayers.CYLINDER_GRENADE);
  }

  @Override
  public void render(Grenade entity, float entityYaw, float partialTicks,
      PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

    float totalTicks = entity.getTotalTicksInAir();
    if (!entity.isStoppedInGround()) {
      totalTicks += partialTicks;
    }

    poseStack.mulPose(Vector3f.XP.rotation(totalTicks / 3.25F));

    var vertexConsumer =
        bufferSource.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity)));
    this.model.render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,
        1.0F, 1.0F, 1.0F, 0.15F);
  }

  @Override
  public ResourceLocation getTextureLocation(Grenade entity) {
    return new ResourceLocation(entity.getType().getRegistryName().getNamespace(),
        "textures/entity/grenade/" + entity.getType().getRegistryName().getPath() + ".png");
  }
}
