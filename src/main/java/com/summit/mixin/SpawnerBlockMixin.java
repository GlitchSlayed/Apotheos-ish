package com.summit.mixin;

import com.summit.config.SummitConfig;
import com.summit.Summit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class SpawnerBlockMixin {

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void summit$useItemOn(ItemStack stack, Level level, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        BlockState state = (BlockState) (Object) this;
        if (!(state.getBlock() instanceof SpawnerBlock)) return;
        if (level.isClientSide() || !SummitConfig.get().spawnerModule.enabled) return;

        BlockPos pos = hitResult.getBlockPos();
        if (!(level.getBlockEntity(pos) instanceof SpawnerBlockEntity spawner)) return;

        InteractionResult result = com.summit.spawner.SpawnerInteractionHandler.interactSpawner(player, hand, level, pos, state, spawner);
        if (result.consumesAction()) {
            cir.setReturnValue(result);
        }
    }
}
