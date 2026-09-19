package com.apotheosish.mixin;

import com.apotheosish.config.ApotheosIshConfig;
import net.minecraft.world.level.block.SugarCaneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyReturnValue;

/** Vanilla asks this method for its hard-coded height cap before each cane growth tick. */
@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @ModifyReturnValue(method = "getBlocksToGrowUpTo", at = @At("RETURN"))
    private int apotheosIsh$configureSugarCaneHeight(int vanillaMaximum) {
        return Math.max(1, ApotheosIshConfig.get().sugarCaneMaxHeight);
    }
}
