package com.summit.client.config;

import com.summit.config.SummitConfig;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/** The client UI edits the common config file; use it on the server for authoritative spawning rules. */
public final class SummitConfigScreen {
    private SummitConfigScreen() { }

    public static Screen create(Screen parent) {
        SummitConfig config = SummitConfig.get();
        SummitConfig.SpawnerSettings spawner = config.spawner;
        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("text.summit.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable("text.summit.config.growth"))
                        .option(integerOption("text.summit.config.sugar_cane_height", 1, 128,
                                () -> config.sugarCaneMaxHeight, value -> config.sugarCaneMaxHeight = value))
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable("text.summit.config.spawner"))
                        .option(integerOption("text.summit.config.minimum_spawn_delay", 0, 12000, () -> spawner.minimumSpawnDelay, value -> spawner.minimumSpawnDelay = value))
                        .option(integerOption("text.summit.config.maximum_spawn_delay", 0, 12000, () -> spawner.maximumSpawnDelay, value -> spawner.maximumSpawnDelay = value))
                        .option(integerOption("text.summit.config.spawn_count", 1, 64, () -> spawner.spawnCount, value -> spawner.spawnCount = value))
                        .option(integerOption("text.summit.config.max_nearby_entities", 1, 256, () -> spawner.maxNearbyEntities, value -> spawner.maxNearbyEntities = value))
                        .option(integerOption("text.summit.config.required_player_range", 1, 256, () -> spawner.requiredPlayerRange, value -> spawner.requiredPlayerRange = value))
                        .option(integerOption("text.summit.config.spawn_range", 1, 64, () -> spawner.spawnRange, value -> spawner.spawnRange = value))
                        .option(integerOption("text.summit.config.initial_health", 1, 100, () -> spawner.initialHealth, value -> spawner.initialHealth = value))
                        .option(booleanOption("text.summit.config.ignore_players", () -> spawner.ignorePlayers, value -> spawner.ignorePlayers = value))
                        .option(booleanOption("text.summit.config.ignore_conditions", () -> spawner.ignoreConditions, value -> spawner.ignoreConditions = value))
                        .option(booleanOption("text.summit.config.redstone_control", () -> spawner.redstoneControl, value -> spawner.redstoneControl = value))
                        .option(booleanOption("text.summit.config.ignore_light", () -> spawner.ignoreLight, value -> spawner.ignoreLight = value))
                        .option(booleanOption("text.summit.config.no_ai", () -> spawner.noAi, value -> spawner.noAi = value))
                        .option(booleanOption("text.summit.config.silent", () -> spawner.silent, value -> spawner.silent = value))
                        .option(booleanOption("text.summit.config.youthful", () -> spawner.youthful, value -> spawner.youthful = value))
                        .option(booleanOption("text.summit.config.burning", () -> spawner.burning, value -> spawner.burning = value))
                        .option(booleanOption("text.summit.config.echoing", () -> spawner.echoing, value -> spawner.echoing = value))
                        .build())
                .save(SummitConfig::save)
                .build()
                .generateScreen(parent);
    }

    private static Option<Integer> integerOption(String key, int min, int max, java.util.function.Supplier<Integer> getter, java.util.function.Consumer<Integer> setter) {
        return Option.<Integer>createBuilder().name(Component.translatable(key)).description(OptionDescription.of(Component.translatable(key + ".tooltip")))
                .binding(min, getter, setter).controller(option -> IntegerSliderControllerBuilder.create(option).range(min, max).step(1)).build();
    }

    private static Option<Boolean> booleanOption(String key, java.util.function.Supplier<Boolean> getter, java.util.function.Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder().name(Component.translatable(key)).description(OptionDescription.of(Component.translatable(key + ".tooltip")))
                .binding(false, getter, setter).controller(BooleanControllerBuilder::create).build();
    }
}
