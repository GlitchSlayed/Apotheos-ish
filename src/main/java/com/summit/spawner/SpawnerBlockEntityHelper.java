package com.summit.spawner;

import com.summit.mixin.SpawnerBlockEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

public final class SpawnerBlockEntityHelper {

    public static SpawnerModifiers getModifiers(SpawnerBlockEntity spawner) {
        if (spawner instanceof SpawnerBlockEntityAccessor accessor) {
            SpawnerModifiers modifiers = accessor.summit$getModifiers();
            if (modifiers != null) return modifiers;
        }
        return new SpawnerModifiers();
    }

    public static void setModifiers(SpawnerBlockEntity spawner, SpawnerModifiers modifiers) {
        if (spawner instanceof SpawnerBlockEntityAccessor accessor) {
            accessor.summit$setModifiers(modifiers);
        }
    }

    public static boolean hasModifiers(SpawnerBlockEntity spawner) {
        if (spawner instanceof SpawnerBlockEntityAccessor accessor) {
            return accessor.summit$getModifiers() != null;
        }
        return false;
    }

    public static void clearModifiers(SpawnerBlockEntity spawner) {
        if (spawner instanceof SpawnerBlockEntityAccessor accessor) {
            accessor.summit$setModifiers(null);
        }
    }
}
