package com.craftingdead.core.client.renderer.entity.grenade;

import com.craftingdead.core.CraftingDead;
import com.craftingdead.core.client.renderer.entity.model.SlimGrenadeModel;
import com.craftingdead.core.entity.grenade.GrenadeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class SlimGrenadeRenderer extends EntityRenderer<GrenadeEntity> {

  private final SlimGrenadeModel model = new SlimGrenadeModel();

  public SlimGrenadeRenderer(EntityRendererManager renderManager) {
    super(renderManager);
  }

  @Override
  public void render(GrenadeEntity entity, float entityYaw, float partialTicks,
      MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int packedLight) {

    matrixStack.translate(0D, 0.2D, 0D);
    matrixStack.scale(0.4f, -0.4f, 0.4f);

    float totalTicks = entity.getTotalTicksInAir();
    if (!entity.isStoppedInGround()) {
      totalTicks += partialTicks;
    }

    matrixStack.rotate(Vector3f.XP
        .rotationDegrees(totalTicks * 30F));

    IVertexBuilder vertexBuilder =
        renderTypeBuffer.getBuffer(model.getRenderType(this.getEntityTexture(entity)));
    model.render(matrixStack, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F,
        1.0F, 0.15F);
  }

  @Override
  public ResourceLocation getEntityTexture(GrenadeEntity entity) {
    return new ResourceLocation(CraftingDead.ID,
        "textures/entity/grenade/" + entity.getType().getRegistryName().getPath() + ".png");
  }
}