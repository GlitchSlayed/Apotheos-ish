package com.summit.spawner;

import com.summit.config.SummitConfig;
import com.summit.Summit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.network.chat.Component;

/**
 * Handles right-click interactions with spawners to apply modifier upgrades.
 * Performs all modifications on the server side.
 */
public final class SpawnerInteractionHandler {

    public static InteractionResult interactSpawner(Player player, InteractionHand hand, Level level, BlockPos pos, BlockState state, SpawnerBlockEntity spawner) {
        if (level.isClientSide()) return InteractionResult.PASS;
        if (!SummitConfig.get().spawnerModule.enabled) return InteractionResult.PASS;

        Item heldItem = player.getItemInHand(hand).getItem();
        boolean holdingQuartzOffhand = false;
        if (player.getOffhandItem().getItem() == Items.QUARTZ) {
            holdingQuartzOffhand = true;
        }

        SpawnerModifiers modifiers = SpawnerBlockEntityHelper.getModifiers(spawner);
        boolean isCreative = player.getAbilities().instabuild;

        ModifierRecipe recipe = ModifierRecipeRegistry.find(heldItem, holdingQuartzOffhand);
        if (recipe == null) return InteractionResult.PASS;

        if (!recipe.canApply(modifiers)) {
            if (!isCreative) {
                player.sendSystemMessage(Component.translatable("text.summit.spawner.modifier.cannot_apply"));
            }
            return InteractionResult.CONSUME;
        }

        recipe.apply(modifiers);
        modifiers.playerModified = true;
        SpawnerBlockEntityHelper.setModifiers(spawner, modifiers);
        spawner.setChanged();
        spawner.getLevel().sendBlockUpdated(pos, state, state, 1);

        if (!isCreative) {
            player.getItemInHand(hand).shrink(1);
        }

        player.sendSystemMessage(Component.translatable("text.summit.spawner.modifier.applied", heldItem.getName(player.getItemInHand(hand)).getString()));

        return InteractionResult.CONSUME;
    }

    public static AABB getSpawnAABB(BlockPos pos, int range) {
        return new AABB(pos).inflate(range, range, range);
    }
}
