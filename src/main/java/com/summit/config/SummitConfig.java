package com.summit.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.summit.Summit;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Server-authoritative defaults for Summit tweaks. */
public final class SummitConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("summit.json");
    private static SummitConfig instance = new SummitConfig();

    public int sugarCaneMaxHeight = 30;
    public final SpawnerSettings spawner = new SpawnerSettings();
    public final SpawnerModuleSettings spawnerModule = new SpawnerModuleSettings();

    public static SummitConfig get() {
        return instance;
    }

    public static void load() {
        try {
            if (Files.exists(FILE)) {
                instance = GSON.fromJson(Files.readString(FILE), SummitConfig.class);
                if (instance == null) instance = new SummitConfig();
            }
            save();
        } catch (IOException exception) {
            Summit.LOGGER.error("Could not load Summit config", exception);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(instance));
        } catch (IOException exception) {
            Summit.LOGGER.error("Could not save Summit config", exception);
        }
    }

    public static final class SpawnerSettings {
        public int minimumSpawnDelay = 200;
        public int maximumSpawnDelay = 800;
        public int spawnCount = 4;
        public int maxNearbyEntities = 6;
        public int requiredPlayerRange = 16;
        public int spawnRange = 4;
        public int initialHealth = 100;
        public boolean ignorePlayers = false;
        public boolean ignoreConditions = false;
        public boolean redstoneControl = false;
        public boolean ignoreLight = false;
        public boolean noAi = false;
        public boolean silent = false;
        public boolean youthful = false;
        public boolean burning = false;
        public int echoing = 0;
    }

    public static final class SpawnerModuleSettings {
        public boolean enabled = true;
        public int silkTouchLevel = 1;
        public int silkTouchDurabilityCost = 100;
        public boolean spawnersDropEmpty = false;
        public int despawnGracePeriod = 600;
        public int maxEchoing = 3;
        public boolean capturingEnabled = true;
        public float[] capturingChances = {0.005f, 0.01f, 0.02f, 0.03f, 0.05f};
        public boolean spawnerHarvestingEnabled = true;
        public boolean modifierRecipesEnabled = true;
        public boolean showStatsInTooltip = true;
        public boolean showModifierInfoInRecipeViewers = true;
    }
}