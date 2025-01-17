/*
 * Crafting Dead
 * Copyright (C) 2022  NexusNode LTD
 *
 * This Non-Commercial Software License Agreement (the "Agreement") is made between
 * you (the "Licensee") and NEXUSNODE (BRAD HUNTER). (the "Licensor").
 * By installing or otherwise using Crafting Dead (the "Software"), you agree to be
 * bound by the terms and conditions of this Agreement as may be revised from time
 * to time at Licensor's sole discretion.
 *
 * If you do not agree to the terms and conditions of this Agreement do not download,
 * copy, reproduce or otherwise use any of the source code available online at any time.
 *
 * https://github.com/nexusnode/crafting-dead/blob/1.18.x/LICENSE.txt
 *
 * https://craftingdead.net/terms.php
 */

package com.craftingdead.immerse.data.models;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;

public class ImmerseModelProvider implements DataProvider {

  private static final Logger logger = LogManager.getLogger();
  private static final Gson gson =
      new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
  private final DataGenerator generator;

  public ImmerseModelProvider(DataGenerator generator) {
    this.generator = generator;
  }

  public void run(HashCache directoryCache) {
    Map<ResourceLocation, Supplier<JsonElement>> models = Maps.newHashMap();
    BiConsumer<ResourceLocation, Supplier<JsonElement>> modelConsumer = (model, json) -> {
      Supplier<JsonElement> existingJson = models.put(model, json);
      if (existingJson != null) {
        throw new IllegalStateException("Duplicate model definition for " + model);
      }
    };

    new ImmerseItemModelGenerators(modelConsumer).run();

    Path outputFolder = this.generator.getOutputFolder();

    this.saveCollection(directoryCache, outputFolder, models,
        ImmerseModelProvider::createModelPath);
  }

  private <T> void saveCollection(HashCache p_240081_1_, Path p_240081_2_,
      Map<T, ? extends Supplier<JsonElement>> p_240081_3_, BiFunction<Path, T, Path> p_240081_4_) {
    p_240081_3_.forEach((p_240088_3_, p_240088_4_) -> {
      Path path = p_240081_4_.apply(p_240081_2_, p_240088_3_);

      try {
        DataProvider.save(gson, p_240081_1_, p_240088_4_.get(), path);
      } catch (Exception exception) {
        logger.error("Couldn't save {}", path, exception);
      }

    });
  }

  private static Path createModelPath(Path parentDir, ResourceLocation modelLocation) {
    return parentDir.resolve(
        "assets/" + modelLocation.getNamespace() + "/models/" + modelLocation.getPath() + ".json");
  }

  @Override
  public String getName() {
    return "Railcraft Block State Definitions";
  }
}
