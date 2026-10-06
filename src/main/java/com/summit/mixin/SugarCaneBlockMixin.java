package com.summit.mixin;

import com.summit.config.SummitConfig;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void summit$configureSugarCaneHeight(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        int maxHeight = Math.max(1, SummitConfig.get().sugarCaneMaxHeight);
        int height = 1;
        BlockPos checkPos = pos.below();
        while (level.getBlockState(checkPos).getBlock() == state.getBlock()) {
            height++;
            checkPos = checkPos.below();
        }
        if (height >= maxHeight) {
            ci.cancel();
        }
    }
}
