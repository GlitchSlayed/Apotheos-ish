package com.apotheosish.mixin;

import com.apotheosish.config.ApotheosIshConfig;
import net.minecraft.world.level.block.SugarCaneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Vanilla asks this method for its hard-coded height cap before each cane growth tick. */
@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @Inject(method = "getBlocksToGrowUpTo", at = @At("RETURN"), cancellable = true)
    private void apotheosIsh$configureSugarCaneHeight(CallbackInfoReturnable<Integer> callback) {
        callback.setReturnValue(Math.max(1, ApotheosIshConfig.get().sugarCaneMaxHeight));
    }
}
