package com.summit;

import com.summit.config.SummitConfig;
import com.summit.registry.SummitItems;
import com.summit.spawner.SummitDataComponents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.core.component.DataComponentType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Summit implements ModInitializer {
	public static final String MOD_ID = "summit";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		SummitItems.initialize();
		SummitConfig.load();
		
		// Register custom data components
		Registry.register(net.minecraft.core.registries.BuiltInRegistries.DATA_COMPONENT_TYPE, Summit.id("spawner_modifiers"), SummitDataComponents.SPAWNER_MODIFIERS);
		
		LOGGER.info("Loaded Summit configuration.");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}