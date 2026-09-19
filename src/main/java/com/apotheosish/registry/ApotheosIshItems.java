package com.apotheosish.registry;

import com.apotheosish.ApotheosIsh;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

/** Items which vanilla does not normally expose in survival inventories. */
public final class ApotheosIshItems {
    public static final Item SPAWNER = Registry.register(BuiltInRegistries.ITEM, ApotheosIsh.id("spawner"),
            new BlockItem(Blocks.SPAWNER, new Item.Properties()));

    private ApotheosIshItems() { }

    public static void initialize() { }
}
