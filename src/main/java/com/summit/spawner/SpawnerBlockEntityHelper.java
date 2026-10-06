package com.summit.spawner;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

import java.util.HashMap;
import java.util.Map;

public final class SpawnerBlockEntityHelper {

    private static final Map<BlockPos, SpawnerModifiers> MODIFIERS = new HashMap<>();

    public static SpawnerModifiers getModifiers(SpawnerBlockEntity spawner) {
        return MODIFIERS.getOrDefault(spawner.getBlockPos(), new SpawnerModifiers());
    }

    public static void setModifiers(SpawnerBlockEntity spawner, SpawnerModifiers modifiers) {
        MODIFIERS.put(spawner.getBlockPos(), modifiers);
    }

    public static boolean hasModifiers(SpawnerBlockEntity spawner) {
        return MODIFIERS.containsKey(spawner.getBlockPos());
    }

    public static void clearModifiers(SpawnerBlockEntity spawner) {
        MODIFIERS.remove(spawner.getBlockPos());
    }

    public static void onLoaded(SpawnerBlockEntity spawner, SpawnerModifiers modifiers) {
        MODIFIERS.put(spawner.getBlockPos(), modifiers);
    }

    private SpawnerBlockEntityHelper() {}
}
