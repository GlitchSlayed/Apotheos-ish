package com.summit.registry;

import com.summit.Summit;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

/** Items which vanilla does not normally expose in survival inventories. */
public final class SummitItems {
    public static final Item SPAWNER = Registry.register(BuiltInRegistries.ITEM, Summit.id("spawner"),
            new BlockItem(Blocks.SPAWNER, new Item.Properties()));

    private SummitItems() { }

    public static void initialize() { }
}
