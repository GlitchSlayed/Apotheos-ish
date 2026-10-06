package com.summit.spawner;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * Represents a modifier recipe that can be applied to a spawner.
 * Each recipe defines how a specific ingredient modifies a specific stat.
 * Recipes are data-driven and replaceable through datapacks.
 */
public final class ModifierRecipe {
    public final Item ingredient;
    public final boolean inverse;
    public final StatType stat;
    public final int additiveValue;
    public final int maxValue;
    public final int minValue;
    public final boolean isPercentage;

    public ModifierRecipe(Item ingredient, boolean inverse, StatType stat, int additiveValue, int maxValue, int minValue, boolean isPercentage) {
        this.ingredient = ingredient;
        this.inverse = inverse;
        this.stat = stat;
        this.additiveValue = additiveValue;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.isPercentage = isPercentage;
    }

    public boolean canApply(SpawnerModifiers modifiers) {
        int currentValue = getCurrentValue(modifiers);
        int targetValue = inverse ? minValue : maxValue;
        return currentValue != targetValue;
    }

    public void apply(SpawnerModifiers modifiers) {
        int currentValue = getCurrentValue(modifiers);
        int newValue;
        if (inverse) {
            newValue = Math.max(minValue, currentValue - additiveValue);
        } else {
            newValue = Math.min(maxValue, currentValue + additiveValue);
        }
        setValue(modifiers, newValue);
    }

    private int getCurrentValue(SpawnerModifiers modifiers) {
        return switch (stat) {
            case MIN_SPAWN_DELAY -> modifiers.minSpawnDelay;
            case MAX_SPAWN_DELAY -> modifiers.maxSpawnDelay;
            case SPAWN_COUNT -> modifiers.spawnCount;
            case MAX_NEARBY_ENTITIES -> modifiers.maxNearbyEntities;
            case REQUIRED_PLAYER_RANGE -> modifiers.requiredPlayerRange;
            case SPAWN_RANGE -> modifiers.spawnRange;
            case INITIAL_HEALTH -> modifiers.initialHealth;
            case ECHOING -> modifiers.echoing;
            default -> 0;
        };
    }

    private void setValue(SpawnerModifiers modifiers, int value) {
        switch (stat) {
            case MIN_SPAWN_DELAY -> modifiers.minSpawnDelay = value;
            case MAX_SPAWN_DELAY -> modifiers.maxSpawnDelay = value;
            case SPAWN_COUNT -> modifiers.spawnCount = value;
            case MAX_NEARBY_ENTITIES -> modifiers.maxNearbyEntities = value;
            case REQUIRED_PLAYER_RANGE -> modifiers.requiredPlayerRange = value;
            case SPAWN_RANGE -> modifiers.spawnRange = value;
            case INITIAL_HEALTH -> modifiers.initialHealth = value;
            case ECHOING -> modifiers.echoing = value;
            default -> {}
        }
    }

    public enum StatType {
        MIN_SPAWN_DELAY,
        MAX_SPAWN_DELAY,
        SPAWN_COUNT,
        MAX_NEARBY_ENTITIES,
        REQUIRED_PLAYER_RANGE,
        SPAWN_RANGE,
        INITIAL_HEALTH,
        ECHOING
    }

    public enum BooleanStat {
        IGNORE_PLAYERS,
        IGNORE_CONDITIONS,
        REDSTONE_CONTROL,
        IGNORE_LIGHT,
        NO_AI,
        SILENT,
        YOUTHFUL,
        BURNING
    }
}
