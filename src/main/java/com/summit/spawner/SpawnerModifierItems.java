package com.summit.spawner;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

/**
 * Spawner modifier items that can be right-clicked on spawners to apply upgrades.
 * Each item corresponds to a specific modifier stat.
 */
public final class SpawnerModifierItems {
    public static final Item SUGAR = register("sugar", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item CLOCK = register("clock", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item FERMENTED_SPIDER_EYE = register("fermented_spider_eye", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item GHAST_TEAR = register("ghast_tear", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item PRISMARINE_CRYSTAL = register("prismarine_crystal", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item PISTON = register("piston", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item POINTED_DRIPSTONE = register("pointed_dripstone", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item NETHER_STAR = register("nether_star", new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final Item CONDUIT = register("conduit", new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final Item REDSTONE_COMPARATOR = register("redstone_comparator", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item SOUL_LANTERN = register("soul_lantern", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item CHORUS_FRUIT = register("chorus_fruit", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item WOOL = register("wool", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item TURTLE_EGG = register("turtle_egg", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item CAMPFIRE = register("campfire", new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final Item ECHO_SHARD = register("echo_shard", new Item(new Item.Properties().rarity(Rarity.RARE)));

    private static Item register(String name, Item item) {
        return net.minecraft.core.Registry.register(net.minecraft.core.registries.BuiltInRegistries.ITEM, com.summit.Summit.id("spawner_modifier/" + name), item);
    }

    private SpawnerModifierItems() { }
}