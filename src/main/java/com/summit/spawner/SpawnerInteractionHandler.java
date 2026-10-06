package com.summit.spawner;

import com.summit.config.SummitConfig;
import com.summit.Summit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
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

        SpawnerModifiers modifiers = getOrCreateModifiers(spawner);
        if (modifiers == null) return InteractionResult.PASS;

        boolean isCreative = player.getAbilities().instabuild;

        // Try to match a modifier recipe
        ModifierRecipe recipe = ModifierRecipeRegistry.find(heldItem, holdingQuartzOffhand);
        if (recipe == null) return InteractionResult.PASS;

        // Check if the stat can change further
        if (!recipe.canApply(modifiers)) {
            if (!isCreative) {
                player.sendSystemMessage(Component.translatable("text.summit.spawner.modifier.cannot_apply"));
            }
            return InteractionResult.CONSUME;
        }

        // Apply the recipe
        recipe.apply(modifiers);
        modifiers.playerModified = true;
        spawner.setChanged();
        spawner.getLevel().sendBlockUpdated(pos, state, state, 1);

        // Consume ingredient only in survival and only on success
        if (!isCreative) {
            player.getItemInHand(hand).shrink(1);
        }

        player.sendSystemMessage(Component.translatable("text.summit.spawner.modifier.applied", heldItem.getName(player.getItemInHand(hand)).getString()));

        return InteractionResult.CONSUME;
    }

    public static SpawnerModifiers getOrCreateModifiers(SpawnerBlockEntity spawner) {
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

    public static void saveModifiers(SpawnerBlockEntity spawner, SpawnerModifiers modifiers) {
        HolderLookup.Provider provider = spawner.getLevel().registryAccess();
        CompoundTag tag = spawner.getUpdateTag(provider);
        modifiers.writeNbt(tag.getCompound(SpawnerModifiers.NBT_KEY).orElseGet(CompoundTag::new));
        spawner.setChanged();
    }

    public static AABB getSpawnAABB(BlockPos pos, int range) {
        return new AABB(pos).inflate(range, range, range);
    }
}