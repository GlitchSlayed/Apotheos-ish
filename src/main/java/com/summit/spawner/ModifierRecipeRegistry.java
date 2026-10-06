package com.summit.spawner;

import com.summit.config.SummitConfig;
import com.summit.Summit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.*;
import java.util.function.Function;

public final class ModifierRecipeRegistry {

    private static final List<ModifierRecipe> RECIPES = new ArrayList<>();

    static {
        registerDefaults();
    }

    private static void registerDefaults() {
        int maxDelay = 1600;
        int minDelay = 20;

        // Normal recipes
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.MIN_SPAWN_DELAY, false, ModifierRecipe.StatType.MIN_SPAWN_DELAY, 10, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.MAX_SPAWN_DELAY, false, ModifierRecipe.StatType.MAX_SPAWN_DELAY, 20, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.SPAWN_COUNT, false, ModifierRecipe.StatType.SPAWN_COUNT, 2, 16, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.MAX_NEARBY_ENTITIES, false, ModifierRecipe.StatType.MAX_NEARBY_ENTITIES, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.REQUIRED_PLAYER_RANGE, false, ModifierRecipe.StatType.REQUIRED_PLAYER_RANGE, 4, 48, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.SPAWN_RANGE, false, ModifierRecipe.StatType.SPAWN_RANGE, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.INITIAL_HEALTH, false, ModifierRecipe.StatType.INITIAL_HEALTH, 5, 20, 100, true));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.ECHOING, false, ModifierRecipe.StatType.ECHOING, 1, 3, 0, false));

        // Inverse recipes (quartz in offhand)
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.MIN_SPAWN_DELAY, true, ModifierRecipe.StatType.MIN_SPAWN_DELAY, 10, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.MAX_SPAWN_DELAY, true, ModifierRecipe.StatType.MAX_SPAWN_DELAY, 20, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.SPAWN_COUNT, true, ModifierRecipe.StatType.SPAWN_COUNT, 2, 16, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.MAX_NEARBY_ENTITIES, true, ModifierRecipe.StatType.MAX_NEARBY_ENTITIES, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.REQUIRED_PLAYER_RANGE, true, ModifierRecipe.StatType.REQUIRED_PLAYER_RANGE, 4, 48, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.SPAWN_RANGE, true, ModifierRecipe.StatType.SPAWN_RANGE, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.INITIAL_HEALTH, true, ModifierRecipe.StatType.INITIAL_HEALTH, 5, 20, 100, true));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.ECHOING, true, ModifierRecipe.StatType.ECHOING, 1, 3, 0, false));
    }

    public static ModifierRecipe find(Item ingredient, boolean inverse) {
        for (ModifierRecipe recipe : RECIPES) {
            if (recipe.ingredient == ingredient && recipe.inverse == inverse) {
                return recipe;
            }
        }
        return null;
    }

    public static List<ModifierRecipe> getAll() {
        return Collections.unmodifiableList(RECIPES);
    }

    private ModifierRecipeRegistry() { }
}