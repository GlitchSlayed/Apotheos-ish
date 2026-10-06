package com.summit.spawner;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.EnumMap;
import java.util.Map;

/**
 * Persistent spawner modifier state stored on the spawner block entity and its harvested item.
 * All values are persisted through NBT so they survive world saves and reloads.
 */
public final class SpawnerModifiers {
    public static final String NBT_KEY = "summit:modifiers";

    public int minSpawnDelay = 200;
    public int maxSpawnDelay = 800;
    public int spawnCount = 4;
    public int maxNearbyEntities = 6;
    public int requiredPlayerRange = 16;
    public int spawnRange = 4;
    public int initialHealth = 100;
    public boolean ignorePlayers = false;
    public boolean ignoreConditions = false;
    public boolean redstoneControl = false;
    public boolean ignoreLight = false;
    public boolean noAi = false;
    public boolean silent = false;
    public boolean youthful = false;
    public boolean burning = false;
    public int echoing = 0;
    public boolean playerModified = false;

    public void writeNbt(CompoundTag tag) {
        tag.putBoolean("IgnorePlayers", ignorePlayers);
        tag.putBoolean("IgnoreConditions", ignoreConditions);
        tag.putBoolean("RedstoneControl", redstoneControl);
        tag.putBoolean("IgnoreLight", ignoreLight);
        tag.putBoolean("NoAI", noAi);
        tag.putBoolean("Silent", silent);
        tag.putBoolean("Youthful", youthful);
        tag.putBoolean("Burning", burning);
        tag.putBoolean("PlayerModified", playerModified);
        tag.putInt("MinSpawnDelay", minSpawnDelay);
        tag.putInt("MaxSpawnDelay", maxSpawnDelay);
        tag.putInt("SpawnCount", spawnCount);
        tag.putInt("MaxNearbyEntities", maxNearbyEntities);
        tag.putInt("RequiredPlayerRange", requiredPlayerRange);
        tag.putInt("SpawnRange", spawnRange);
        tag.putInt("InitialHealth", initialHealth);
        tag.putInt("Echoing", echoing);
    }

    public void readNbt(CompoundTag tag) {
        ignorePlayers = tag.getBooleanOr("IgnorePlayers", false);
        ignoreConditions = tag.getBooleanOr("IgnoreConditions", false);
        redstoneControl = tag.getBooleanOr("RedstoneControl", false);
        ignoreLight = tag.getBooleanOr("IgnoreLight", false);
        noAi = tag.getBooleanOr("NoAI", false);
        silent = tag.getBooleanOr("Silent", false);
        youthful = tag.getBooleanOr("Youthful", false);
        burning = tag.getBooleanOr("Burning", false);
        playerModified = tag.getBooleanOr("PlayerModified", false);
        minSpawnDelay = tag.getIntOr("MinSpawnDelay", 200);
        maxSpawnDelay = tag.getIntOr("MaxSpawnDelay", 800);
        spawnCount = tag.getIntOr("SpawnCount", 4);
        maxNearbyEntities = tag.getIntOr("MaxNearbyEntities", 6);
        requiredPlayerRange = tag.getIntOr("RequiredPlayerRange", 16);
        spawnRange = tag.getIntOr("SpawnRange", 4);
        initialHealth = tag.getIntOr("InitialHealth", 100);
        echoing = tag.getIntOr("Echoing", 0);
    }

    public void reset() {
        minSpawnDelay = 200;
        maxSpawnDelay = 800;
        spawnCount = 4;
        maxNearbyEntities = 6;
        requiredPlayerRange = 16;
        spawnRange = 4;
        initialHealth = 100;
        ignorePlayers = false;
        ignoreConditions = false;
        redstoneControl = false;
        ignoreLight = false;
        noAi = false;
        silent = false;
        youthful = false;
        burning = false;
        echoing = 0;
        playerModified = false;
    }

    public SpawnerModifiers copy() {
        SpawnerModifiers copy = new SpawnerModifiers();
        copy.minSpawnDelay = minSpawnDelay;
        copy.maxSpawnDelay = maxSpawnDelay;
        copy.spawnCount = spawnCount;
        copy.maxNearbyEntities = maxNearbyEntities;
        copy.requiredPlayerRange = requiredPlayerRange;
        copy.spawnRange = spawnRange;
        copy.initialHealth = initialHealth;
        copy.ignorePlayers = ignorePlayers;
        copy.ignoreConditions = ignoreConditions;
        copy.redstoneControl = redstoneControl;
        copy.ignoreLight = ignoreLight;
        copy.noAi = noAi;
        copy.silent = silent;
        copy.youthful = youthful;
        copy.burning = burning;
        copy.echoing = echoing;
        copy.playerModified = playerModified;
        return copy;
    }
}