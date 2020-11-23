package com.craftingdead.immerse.client.gui.component;

import java.io.Closeable;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.craftingdead.immerse.CraftingDeadImmerse;
import com.craftingdead.immerse.client.util.RenderUtil;
import com.google.gson.JsonSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;

public class Blur implements Closeable {

  private static final Logger logger = LogManager.getLogger();

  private static final ResourceLocation BLUR_SHADER =
      new ResourceLocation(CraftingDeadImmerse.ID, "shaders/post/blur.json");

  private final Minecraft minecraft = Minecraft.getInstance();

  private ShaderGroup blurShader;


  private float lastFramebufferWidth;
  private float lastFramebufferHeight;

  public Blur() {
    this(0);
  }

  public Blur(float radius) {
    try {
      this.blurShader = new ShaderGroup(this.minecraft.getTextureManager(),
          this.minecraft.getResourceManager(), this.minecraft.getFramebuffer(), BLUR_SHADER);
      if (radius > 0) {
        RenderUtil.updateUniform("Radius", radius, this.blurShader);
      }
      this.blurShader.createBindFramebuffers(this.minecraft.getMainWindow().getFramebufferWidth(),
          this.minecraft.getMainWindow().getFramebufferHeight());
    } catch (JsonSyntaxException | IOException ioexception) {
      logger.warn("Failed to load shader: {}", BLUR_SHADER, ioexception);
      this.blurShader = null;
    }
  }

  public void tick() {
    float framebufferWidth = this.minecraft.getFramebuffer().framebufferTextureWidth;
    float framebufferHeight = this.minecraft.getFramebuffer().framebufferTextureHeight;
    // Can't use #resized as it's called before the framebuffer is resized.
    if (framebufferWidth != this.lastFramebufferWidth
        || framebufferHeight != this.lastFramebufferHeight) {
      if (this.blurShader != null) {
        this.blurShader.createBindFramebuffers(this.minecraft.getMainWindow().getFramebufferWidth(),
            this.minecraft.getMainWindow().getFramebufferHeight());
      }
      this.lastFramebufferWidth = framebufferWidth;
      this.lastFramebufferHeight = framebufferHeight;
    }
  }

  public void render(float x, float y, float width, float height, float partialTicks) {
    this.blurShader.render(partialTicks);
    this.minecraft.getFramebuffer().bindFramebuffer(false);
    this.blurShader.getFramebufferRaw("output").bindFramebufferTexture();
    RenderUtil.blit(x, y, x + width, y + height, x, y, x + width, y + height,
        this.minecraft.getMainWindow().getScaledWidth(),
        this.minecraft.getMainWindow().getScaledHeight());
  }

  @Override
  public void close() {
    this.blurShader.close();
  }
}
