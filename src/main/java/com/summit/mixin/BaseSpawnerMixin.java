package com.summit.mixin;

import com.summit.config.SummitConfig;
import com.summit.spawner.SpawnerBlockEntityHelper;
import com.summit.spawner.SpawnerModifiers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseSpawner.class)
public abstract class BaseSpawnerMixin {
    @Shadow private int minSpawnDelay;
    @Shadow private int maxSpawnDelay;
    @Shadow private int spawnCount;
    @Shadow private int maxNearbyEntities;
    @Shadow private int requiredPlayerRange;
    @Shadow private int spawnRange;

    @Inject(method = "serverTick", at = @At("HEAD"), cancellable = true)
    private void summit$applyConfiguredStats(ServerLevel level, BlockPos position, CallbackInfo callback) {
        SpawnerModifiers modifiers = getSummitModifiers(level, position);
        minSpawnDelay = Math.max(0, modifiers.minSpawnDelay);
        maxSpawnDelay = Math.max(minSpawnDelay, modifiers.maxSpawnDelay);
        spawnCount = Math.max(1, modifiers.spawnCount);
        maxNearbyEntities = Math.max(1, modifiers.maxNearbyEntities);
        requiredPlayerRange = Math.max(1, modifiers.requiredPlayerRange);
        spawnRange = Math.max(1, modifiers.spawnRange);
        if (modifiers.redstoneControl && !level.hasNeighborSignal(position)) callback.cancel();
    }

    @Redirect(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/BaseSpawner;isNearPlayer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean summit$ignorePlayerRequirement(BaseSpawner spawner, ServerLevel level, BlockPos position) {
        return getSummitModifiers(level, position).ignorePlayers || isNearPlayer(level, position);
    }

    @Shadow
    private boolean isNearPlayer(ServerLevel level, BlockPos position) {
        return false;
    }

    private SpawnerModifiers getSummitModifiers(ServerLevel level, BlockPos position) {
        if (level.getBlockEntity(position) instanceof net.minecraft.world.level.block.entity.SpawnerBlockEntity spawner) {
            return SpawnerBlockEntityHelper.getModifiers(spawner);
        }
        return new SpawnerModifiers();
    }
}
