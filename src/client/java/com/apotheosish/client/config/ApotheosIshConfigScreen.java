package com.apotheosish.client.config;

import com.apotheosish.config.ApotheosIshConfig;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/** The client UI edits the common config file; use it on the server for authoritative spawning rules. */
public final class ApotheosIshConfigScreen {
    private ApotheosIshConfigScreen() { }

    public static Screen create(Screen parent) {
        ApotheosIshConfig config = ApotheosIshConfig.get();
        ApotheosIshConfig.SpawnerSettings spawner = config.spawner;
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("text.apotheos-ish.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("text.apotheos-ish.config.growth"))
                        .option(integerOption("text.apotheos-ish.config.sugar_cane_height", 1, 128,
                                () -> config.sugarCaneMaxHeight, value -> config.sugarCaneMaxHeight = value))
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("text.apotheos-ish.config.spawner"))
                        .option(integerOption("text.apotheos-ish.config.minimum_spawn_delay", 0, 12000, () -> spawner.minimumSpawnDelay, value -> spawner.minimumSpawnDelay = value))
                        .option(integerOption("text.apotheos-ish.config.maximum_spawn_delay", 0, 12000, () -> spawner.maximumSpawnDelay, value -> spawner.maximumSpawnDelay = value))
                        .option(integerOption("text.apotheos-ish.config.spawn_count", 1, 64, () -> spawner.spawnCount, value -> spawner.spawnCount = value))
                        .option(integerOption("text.apotheos-ish.config.max_nearby_entities", 1, 256, () -> spawner.maxNearbyEntities, value -> spawner.maxNearbyEntities = value))
                        .option(integerOption("text.apotheos-ish.config.required_player_range", 1, 256, () -> spawner.requiredPlayerRange, value -> spawner.requiredPlayerRange = value))
                        .option(integerOption("text.apotheos-ish.config.spawn_range", 1, 64, () -> spawner.spawnRange, value -> spawner.spawnRange = value))
                        .option(integerOption("text.apotheos-ish.config.initial_health", 1, 100, () -> spawner.initialHealth, value -> spawner.initialHealth = value))
                        .option(booleanOption("text.apotheos-ish.config.ignore_players", () -> spawner.ignorePlayers, value -> spawner.ignorePlayers = value))
                        .option(booleanOption("text.apotheos-ish.config.ignore_conditions", () -> spawner.ignoreConditions, value -> spawner.ignoreConditions = value))
                        .option(booleanOption("text.apotheos-ish.config.redstone_control", () -> spawner.redstoneControl, value -> spawner.redstoneControl = value))
                        .option(booleanOption("text.apotheos-ish.config.ignore_light", () -> spawner.ignoreLight, value -> spawner.ignoreLight = value))
                        .option(booleanOption("text.apotheos-ish.config.no_ai", () -> spawner.noAi, value -> spawner.noAi = value))
                        .option(booleanOption("text.apotheos-ish.config.silent", () -> spawner.silent, value -> spawner.silent = value))
                        .option(booleanOption("text.apotheos-ish.config.youthful", () -> spawner.youthful, value -> spawner.youthful = value))
                        .option(booleanOption("text.apotheos-ish.config.burning", () -> spawner.burning, value -> spawner.burning = value))
                        .option(booleanOption("text.apotheos-ish.config.echoing", () -> spawner.echoing, value -> spawner.echoing = value))
                        .build())
                .save(ApotheosIshConfig::save)
                .build()
                .generateScreen(parent);
    }

    private static Option<Integer> integerOption(String key, int min, int max, java.util.function.Supplier<Integer> getter, java.util.function.Consumer<Integer> setter) {
        return Option.<Integer>createBuilder().name(Text.translatable(key)).description(OptionDescription.of(Text.translatable(key + ".tooltip")))
                .binding(min, getter, setter).controller(option -> IntegerSliderControllerBuilder.create(option).range(min, max).step(1)).build();
    }

    private static Option<Boolean> booleanOption(String key, java.util.function.Supplier<Boolean> getter, java.util.function.Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder().name(Text.translatable(key)).description(OptionDescription.of(Text.translatable(key + ".tooltip")))
                .binding(false, getter, setter).controller(BooleanControllerBuilder::create).build();
    }
}
