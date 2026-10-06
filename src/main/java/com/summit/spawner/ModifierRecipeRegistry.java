package com.summit.spawner;

import com.summit.config.SummitConfig;
import com.summit.Summit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.*;

public final class ModifierRecipeRegistry {

    private static final List<ModifierRecipe> RECIPES = new ArrayList<>();

    static {
        registerDefaults();
    }

    private static void registerDefaults() {
        int maxDelay = 1600;
        int minDelay = 20;

        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.SUGAR, false, ModifierRecipe.StatType.MIN_SPAWN_DELAY, 10, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.CLOCK, false, ModifierRecipe.StatType.MAX_SPAWN_DELAY, 20, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.FERMENTED_SPIDER_EYE, false, ModifierRecipe.StatType.SPAWN_COUNT, 2, 16, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.GHAST_TEAR, false, ModifierRecipe.StatType.MAX_NEARBY_ENTITIES, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.PRISMARINE_CRYSTAL, false, ModifierRecipe.StatType.REQUIRED_PLAYER_RANGE, 4, 48, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.PISTON, false, ModifierRecipe.StatType.SPAWN_RANGE, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.POINTED_DRIPSTONE, false, ModifierRecipe.StatType.INITIAL_HEALTH, 5, 20, 100, true));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.ECHO_SHARD, false, ModifierRecipe.StatType.ECHOING, 1, 3, 0, false));

        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.SUGAR, true, ModifierRecipe.StatType.MIN_SPAWN_DELAY, 10, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.CLOCK, true, ModifierRecipe.StatType.MAX_SPAWN_DELAY, 20, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.FERMENTED_SPIDER_EYE, true, ModifierRecipe.StatType.SPAWN_COUNT, 2, 16, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.GHAST_TEAR, true, ModifierRecipe.StatType.MAX_NEARBY_ENTITIES, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.PRISMARINE_CRYSTAL, true, ModifierRecipe.StatType.REQUIRED_PLAYER_RANGE, 4, 48, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.PISTON, true, ModifierRecipe.StatType.SPAWN_RANGE, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.POINTED_DRIPSTONE, true, ModifierRecipe.StatType.INITIAL_HEALTH, 5, 20, 100, true));
        RECIPES.add(new ModifierRecipe(SpawnerModifierItems.ECHO_SHARD, true, ModifierRecipe.StatType.ECHOING, 1, 3, 0, false));
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
