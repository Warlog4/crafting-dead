/*
 * Crafting Dead
 * Copyright (C) 2021  NexusNode LTD
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.craftingdead.immerse.client.gui.view;

import com.craftingdead.immerse.client.gui.tween.FloatTweenType;
import com.craftingdead.immerse.client.util.RenderUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.noties.tumbleweed.TweenType;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;

public class Tooltip {

  public static final TweenType<Tooltip> ALPHA =
      new FloatTweenType<>(Tooltip::getAlpha, Tooltip::setAlpha);
  public static final TweenType<Tooltip> TEXT_ALPHA =
      new FloatTweenType<>(Tooltip::getTextAlpha, Tooltip::setTextAlpha);

  private final ITextComponent text;
  private float alpha = 0;
  private float textAlpha = 0;

  public Tooltip(ITextComponent text) {
    this.text = text;
  }

  public void render(FontRenderer fontRenderer, MatrixStack matrixStack, float x, float y) {
    final float width = 10.0F + fontRenderer.width(this.text);
    final float height = 14;

    RenderSystem.enableBlend();
    RenderUtil.enableRoundedRectShader(x, y, x + width, y + height, 2);
    RenderUtil.fill(matrixStack,
        x, y, x + width, y + height, +((int) (this.alpha * 0.5F * 255.0F) << 24));
    RenderUtil.resetShader();

    final int textOpacity = Math.min((int) (this.textAlpha * 255.0F), 255);
    if (textOpacity >= 8) {
      matrixStack.pushPose();
      {
        matrixStack.translate(0.0D, 0.0D, 400.0D);
        fontRenderer.drawShadow(matrixStack, this.text,
            (float) (x + (width - fontRenderer.width(this.text)) / 2), (float) y + 4,
            0xFFFFFF + (textOpacity << 24));
      }
      matrixStack.popPose();
    }

    RenderSystem.disableBlend();
  }

  public float getAlpha() {
    return this.alpha;
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
  }

  public float getTextAlpha() {
    return this.textAlpha;
  }

  public void setTextAlpha(float textAlpha) {
    this.textAlpha = textAlpha;
  }
}
