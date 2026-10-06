package com.summit.mixin;

import com.summit.spawner.SpawnerBlockEntityHelper;
import com.summit.spawner.SpawnerModifiers;
import com.summit.spawner.SummitDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Block.class)
public class SpawnerBlockDropsMixin {
    @Inject(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;)Ljava/util/List;", at = @At("RETURN"))
    private static void summit$addModifiersToSpawnerDrop(BlockState state, ServerLevel level, BlockPos pos, BlockEntity blockEntity, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (!(state.getBlock() instanceof net.minecraft.world.level.block.SpawnerBlock)) return;
        if (!(blockEntity instanceof SpawnerBlockEntity spawner)) return;

        List<ItemStack> drops = cir.getReturnValue();
        if (drops.isEmpty()) return;

        SpawnerModifiers modifiers = SpawnerBlockEntityHelper.getModifiers(spawner);
        if (!modifiers.playerModified) return;

        for (ItemStack stack : drops) {
            if (stack.is(net.minecraft.world.item.Items.SPAWNER)) {
                CompoundTag tag = new CompoundTag();
                modifiers.writeNbt(tag);
                CustomData.update(SummitDataComponents.SPAWNER_MODIFIERS, stack, inner -> {
                    inner.merge(tag);
                });
            }
        }
    }
}
