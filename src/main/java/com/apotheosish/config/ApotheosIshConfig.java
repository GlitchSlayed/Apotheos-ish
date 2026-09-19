package com.apotheosish.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.apotheosish.ApotheosIsh;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Server-authoritative defaults for Apotheos-ish tweaks. */
public final class ApotheosIshConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("apotheos-ish.json");
    private static ApotheosIshConfig instance = new ApotheosIshConfig();

    public int sugarCaneMaxHeight = 30;
    public final SpawnerSettings spawner = new SpawnerSettings();

    public static ApotheosIshConfig get() {
        return instance;
    }

    public static void load() {
        try {
            if (Files.exists(FILE)) {
                instance = GSON.fromJson(Files.readString(FILE), ApotheosIshConfig.class);
                if (instance == null) instance = new ApotheosIshConfig();
            }
            save(); // Creates a documented default config on first launch.
        } catch (IOException exception) {
            ApotheosIsh.LOGGER.error("Could not load Apotheos-ish config", exception);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(instance));
        } catch (IOException exception) {
            ApotheosIsh.LOGGER.error("Could not save Apotheos-ish config", exception);
        }
    }

    public static final class SpawnerSettings {
        public int minimumSpawnDelay = 200;
        public int maximumSpawnDelay = 800;
        public int spawnCount = 4;
        public int maxNearbyEntities = 6;
        public int requiredPlayerRange = 16;
        public int spawnRange = 4;
        /** Percentage of the target's normal max health; 100 is normal health. */
        public int initialHealth = 100;
        public boolean ignorePlayers = false;
        public boolean ignoreConditions = false;
        public boolean redstoneControl = false;
        public boolean ignoreLight = false;
        public boolean noAi = false;
        public boolean silent = false;
        public boolean youthful = false;
        public boolean burning = false;
        public boolean echoing = false;
    }
}
