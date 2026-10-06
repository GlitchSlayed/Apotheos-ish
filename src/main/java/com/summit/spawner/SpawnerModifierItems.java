package com.summit.spawner;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * Spawner modifier ingredients mapped to the stat they modify.
 * Defaults can be overridden in config; names reflect the modified stat.
 */
public final class SpawnerModifierItems {
    public static final Item MIN_SPAWN_DELAY = Items.SUGAR;
    public static final Item MAX_SPAWN_DELAY = Items.CLOCK;
    public static final Item SPAWN_COUNT = Items.FERMENTED_SPIDER_EYE;
    public static final Item MAX_NEARBY_ENTITIES = Items.GHAST_TEAR;
    public static final Item REQUIRED_PLAYER_RANGE = Items.PRISMARINE_CRYSTALS;
    public static final Item SPAWN_RANGE = Items.PISTON;
    public static final Item INITIAL_HEALTH = Items.POINTED_DRIPSTONE;
    public static final Item ECHOING = Items.ECHO_SHARD;
    public static final Item MAX_NEARBY_ENTITIES_INVERSE = Items.PRISMARINE_CRYSTALS;
    public static final Item REQUIRED_PLAYER_RANGE_INVERSE = Items.PRISMARINE_CRYSTALS;
    public static final Item SPAWN_RANGE_INVERSE = Items.PISTON;
    public static final Item MIN_SPAWN_DELAY_INVERSE = Items.SUGAR;
    public static final Item MAX_SPAWN_DELAY_INVERSE = Items.CLOCK;
    public static final Item SPAWN_COUNT_INVERSE = Items.FERMENTED_SPIDER_EYE;
    public static final Item MAX_NEARBY_ENTITIES_INVERSE_ALT = Items.GHAST_TEAR;

    private SpawnerModifierItems() { }
}