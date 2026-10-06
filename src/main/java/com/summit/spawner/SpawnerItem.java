package com.summit.spawner;

import com.summit.Summit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class SpawnerItem extends Item {

    public SpawnerItem() {
        super(new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, Summit.id("spawner")))
                .rarity(Rarity.EPIC)
                .stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        tooltip.accept(Component.translatable("item.summit.spawner.tooltip").withStyle(ChatFormatting.GRAY));

        net.minecraft.world.item.component.CustomData customData = stack.getComponents().get(SummitDataComponents.SPAWNER_MODIFIERS);
        if (customData == null || customData.isEmpty()) {
            tooltip.accept(Component.translatable("item.summit.spawner.tooltip.default").withStyle(ChatFormatting.DARK_GRAY));
            return;
        }

        net.minecraft.nbt.CompoundTag tag = customData.copyTag();
        SpawnerModifiers modifiers = new SpawnerModifiers();
        modifiers.readNbt(tag);

        if (modifiers.ignorePlayers) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.ignore_players").withStyle(ChatFormatting.BLUE));
        if (modifiers.ignoreConditions) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.ignore_conditions").withStyle(ChatFormatting.BLUE));
        if (modifiers.redstoneControl) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.redstone").withStyle(ChatFormatting.BLUE));
        if (modifiers.ignoreLight) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.ignore_light").withStyle(ChatFormatting.BLUE));
        if (modifiers.noAi) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.no_ai").withStyle(ChatFormatting.BLUE));
        if (modifiers.silent) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.silent").withStyle(ChatFormatting.BLUE));
        if (modifiers.youthful) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.youthful").withStyle(ChatFormatting.BLUE));
        if (modifiers.burning) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.burning").withStyle(ChatFormatting.BLUE));
        if (modifiers.playerModified) tooltip.accept(Component.translatable("item.summit.spawner.tooltip.modified").withStyle(ChatFormatting.GREEN));

        tooltip.accept(Component.translatable("item.summit.spawner.tooltip.delay", modifiers.minSpawnDelay, modifiers.maxSpawnDelay).withStyle(ChatFormatting.WHITE));
        tooltip.accept(Component.translatable("item.summit.spawner.tooltip.count", modifiers.spawnCount).withStyle(ChatFormatting.WHITE));
        tooltip.accept(Component.translatable("item.summit.spawner.tooltip.nearby", modifiers.maxNearbyEntities).withStyle(ChatFormatting.WHITE));
        tooltip.accept(Component.translatable("item.summit.spawner.tooltip.range", modifiers.requiredPlayerRange, modifiers.spawnRange).withStyle(ChatFormatting.WHITE));
        if (modifiers.initialHealth != 100) {
            tooltip.accept(Component.translatable("item.summit.spawner.tooltip.health", modifiers.initialHealth).withStyle(ChatFormatting.WHITE));
        }
        if (modifiers.echoing > 0) {
            tooltip.accept(Component.translatable("item.summit.spawner.tooltip.echoing", modifiers.echoing).withStyle(ChatFormatting.WHITE));
        }
    }
}
