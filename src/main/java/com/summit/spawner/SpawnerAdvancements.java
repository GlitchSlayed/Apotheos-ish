package com.summit.spawner;

import com.summit.Summit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public final class SpawnerAdvancements {

    public static final Map<String, Advancement> ADVANCEMENTS = new HashMap<>();

    public static void register() {
        Advancement root = registerAdvancement("spawner/root", null, Items.SPAWNER, "Summit Spawners", AdvancementType.TASK);
        registerAdvancement("spawner/harvest", root, Items.SPAWNER, "Spawner Harvester", AdvancementType.TASK);
        registerAdvancement("spawner/modify", root, Items.SPAWNER, "Spawner Modifier", AdvancementType.TASK);
        registerAdvancement("spawner/capturing", root, Items.SPAWNER, "Capturing", AdvancementType.TASK);
    }

    private static Advancement registerAdvancement(String id, Advancement parent, Item item, String title, AdvancementType type) {
        java.util.Optional<net.minecraft.resources.Identifier> parentId = parent == null ? java.util.Optional.empty() : parent.parent();
        Advancement advancement = new Advancement(
                parentId,
                java.util.Optional.of(new DisplayInfo(
                        new net.minecraft.world.item.ItemStackTemplate(item),
                        Component.literal(title),
                        Component.empty(),
                        java.util.Optional.empty(),
                        type,
                        true,
                        true,
                        false)),
                net.minecraft.advancements.AdvancementRewards.EMPTY,
                java.util.Map.of(),
                net.minecraft.advancements.AdvancementRequirements.EMPTY,
                false);
        ADVANCEMENTS.put(id, advancement);
        return advancement;
    }

    private SpawnerAdvancements() { }
}