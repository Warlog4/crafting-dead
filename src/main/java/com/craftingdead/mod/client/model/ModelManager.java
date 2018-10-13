package com.craftingdead.mod.client.model;

import java.util.HashMap;
import java.util.Map;

import com.craftingdead.mod.client.ClientProxy;
import com.craftingdead.mod.client.renderer.item.ArcRenderer;
import com.craftingdead.mod.common.registry.forge.ItemRegistry;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelManager {

	private final ClientProxy client;

	public ModelManager(ClientProxy client) {
		this.client = client;
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		CustomModelLoader modelLoader = new CustomModelLoader();
		ModelLoaderRegistry.registerLoader(modelLoader);

		registerModel(ItemRegistry.ARC, modelLoader, new ModelBuiltInRenderer(new ArcRenderer(client)));
		registerModel(ItemRegistry.ROAD, "normal");
		registerModel(ItemRegistry.LINED_ROAD, "normal");
		registerModel(ItemRegistry.BROKEN_LINED_ROAD, "normal");
		registerModel(ItemRegistry.BARBED_WIRE, "normal");
		registerModel(ItemRegistry.RESIDENTIAL_LOOT, "normal");
	}

	private void registerModel(Item item, CustomModelLoader modelLoader, IModel model) {
		ModelResourceLocation modelLocation = registerModel(item, "normal");
		modelLoader.registerModel(modelLocation, model);
	}

	private ModelResourceLocation registerModel(Item item, String variant) {
		ModelResourceLocation modelLocation = new ModelResourceLocation(item.getRegistryName(), variant);
		ModelLoader.setCustomModelResourceLocation(item, 0, modelLocation);
		return modelLocation;
	}

	private class CustomModelLoader implements ICustomModelLoader {

		private Map<ResourceLocation, IModel> locationToModel = new HashMap<ResourceLocation, IModel>();

		@Override
		public void onResourceManagerReload(IResourceManager resourceManager) {
			;
		}

		@Override
		public boolean accepts(ResourceLocation modelLocation) {
			return locationToModel.containsKey(modelLocation);
		}

		@Override
		public IModel loadModel(ResourceLocation modelLocation) throws Exception {
			return locationToModel.get(modelLocation);
		}

		private void registerModel(ResourceLocation modelLocation, IModel model) {
			locationToModel.put(modelLocation, model);
		}

	}

}
