package com.apotheosish.mixin;

import com.apotheosish.config.ApotheosIshConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Applies the configured spawner defaults without replacing vanilla's spawning algorithm. */
@Mixin(BaseSpawner.class)
public abstract class BaseSpawnerMixin {
    @Shadow private int minSpawnDelay;
    @Shadow private int maxSpawnDelay;
    @Shadow private int spawnCount;
    @Shadow private int maxNearbyEntities;
    @Shadow private int requiredPlayerRange;
    @Shadow private int spawnRange;
    @Shadow protected abstract boolean isNearPlayer(ServerLevel level, BlockPos position);

    @Inject(method = "serverTick", at = @At("HEAD"), cancellable = true)
    private void apotheosIsh$applyConfiguredStats(ServerLevel level, BlockPos position, CallbackInfo callback) {
        ApotheosIshConfig.SpawnerSettings settings = ApotheosIshConfig.get().spawner;
        minSpawnDelay = Math.max(0, settings.minimumSpawnDelay);
        maxSpawnDelay = Math.max(minSpawnDelay, settings.maximumSpawnDelay);
        spawnCount = Math.max(1, settings.spawnCount);
        maxNearbyEntities = Math.max(1, settings.maxNearbyEntities);
        requiredPlayerRange = Math.max(1, settings.requiredPlayerRange);
        spawnRange = Math.max(1, settings.spawnRange);
        if (settings.redstoneControl && !level.hasNeighborSignal(position)) callback.cancel();
    }

    @Redirect(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/BaseSpawner;isNearPlayer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean apotheosIsh$ignorePlayerRequirement(BaseSpawner spawner, ServerLevel level, BlockPos position) {
        return ApotheosIshConfig.get().spawner.ignorePlayers || this.isNearPlayer(level, position);
    }
}
