package com.summit.spawner;

import com.summit.config.SummitConfig;
import com.summit.Summit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

/**
 * Utility methods for working with spawner modifier data on SpawnerBlockEntities.
 */
public final class SpawnerBlockEntityHelper {

    public static SpawnerModifiers getModifiers(SpawnerBlockEntity spawner) {
        HolderLookup.Provider provider = spawner.getLevel().registryAccess();
        CompoundTag tag = spawner.getUpdateTag(provider);
        SpawnerModifiers modifiers = new SpawnerModifiers();
        if (tag.contains(SpawnerModifiers.NBT_KEY)) {
            modifiers.readNbt(tag.getCompound(SpawnerModifiers.NBT_KEY).orElse(new CompoundTag()));
        } else {
            modifiers.reset();
        }
        return modifiers;
    }

    public static void setModifiers(SpawnerBlockEntity spawner, SpawnerModifiers modifiers) {
        HolderLookup.Provider provider = spawner.getLevel().registryAccess();
        CompoundTag tag = spawner.getUpdateTag(provider);
        modifiers.writeNbt(tag.getCompound(SpawnerModifiers.NBT_KEY).orElseGet(CompoundTag::new));
        spawner.setChanged();
    }

    public static boolean hasModifiers(SpawnerBlockEntity spawner) {
        HolderLookup.Provider provider = spawner.getLevel().registryAccess();
        return spawner.getUpdateTag(provider).contains(SpawnerModifiers.NBT_KEY);
    }

    public static void clearModifiers(SpawnerBlockEntity spawner) {
        HolderLookup.Provider provider = spawner.getLevel().registryAccess();
        CompoundTag tag = spawner.getUpdateTag(provider);
        if (tag.contains(SpawnerModifiers.NBT_KEY)) {
            tag.remove(SpawnerModifiers.NBT_KEY);
            spawner.setChanged();
        }
    }
}