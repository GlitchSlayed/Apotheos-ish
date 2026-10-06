package com.summit.spawner;

import com.summit.config.SummitConfig;
import com.summit.Summit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.*;

/**
 * Registry of all modifier recipes for spawners.
 * Recipes are hardcoded defaults that can be replaced through datapacks.
 */
public final class ModifierRecipeRegistry {

    private static final List<ModifierRecipe> RECIPES = new ArrayList<>();

    static {
        // Register default recipes
        registerDefaults();
    }

    private static void registerDefaults() {
        int maxDelay = 1600;
        int minDelay = 20;

        // Sugar: Reduce min spawn delay by 10, min 20
        RECIPES.add(new ModifierRecipe(Items.SUGAR, false, ModifierRecipe.StatType.MIN_SPAWN_DELAY, 10, minDelay, maxDelay, false));
        // Clock: Reduce max spawn delay by 20, min 20
        RECIPES.add(new ModifierRecipe(Items.CLOCK, false, ModifierRecipe.StatType.MAX_SPAWN_DELAY, 20, minDelay, maxDelay, false));
        // Fermented Spider Eye: Increase spawn count by 2, max 16
        RECIPES.add(new ModifierRecipe(Items.FERMENTED_SPIDER_EYE, false, ModifierRecipe.StatType.SPAWN_COUNT, 2, 16, 1, false));
        // Ghast Tear: Increase max nearby entities by 2, max 32
        RECIPES.add(new ModifierRecipe(Items.GHAST_TEAR, false, ModifierRecipe.StatType.MAX_NEARBY_ENTITIES, 2, 32, 1, false));
        // Prismarine Crystals: Increase required player range by 4, max 48
        RECIPES.add(new ModifierRecipe(Items.PRISMARINE_CRYSTALS, false, ModifierRecipe.StatType.REQUIRED_PLAYER_RANGE, 4, 48, 1, false));
        // Piston: Increase spawn range by 2, max 32
        RECIPES.add(new ModifierRecipe(Items.PISTON, false, ModifierRecipe.StatType.SPAWN_RANGE, 2, 32, 1, false));
        // Pointed Dripstone: Reduce initial health by 5%, min 20%
        RECIPES.add(new ModifierRecipe(Items.POINTED_DRIPSTONE, false, ModifierRecipe.StatType.INITIAL_HEALTH, 5, 20, 100, true));
        // Echo Shard: Increase echoing by 1, max 3
        RECIPES.add(new ModifierRecipe(Items.ECHO_SHARD, false, ModifierRecipe.StatType.ECHOING, 1, 3, 0, false));

        // Inverse recipes
        RECIPES.add(new ModifierRecipe(Items.SUGAR, true, ModifierRecipe.StatType.MIN_SPAWN_DELAY, 10, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(Items.CLOCK, true, ModifierRecipe.StatType.MAX_SPAWN_DELAY, 20, minDelay, maxDelay, false));
        RECIPES.add(new ModifierRecipe(Items.FERMENTED_SPIDER_EYE, true, ModifierRecipe.StatType.SPAWN_COUNT, 2, 16, 1, false));
        RECIPES.add(new ModifierRecipe(Items.GHAST_TEAR, true, ModifierRecipe.StatType.MAX_NEARBY_ENTITIES, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(Items.PRISMARINE_CRYSTALS, true, ModifierRecipe.StatType.REQUIRED_PLAYER_RANGE, 4, 48, 1, false));
        RECIPES.add(new ModifierRecipe(Items.PISTON, true, ModifierRecipe.StatType.SPAWN_RANGE, 2, 32, 1, false));
        RECIPES.add(new ModifierRecipe(Items.POINTED_DRIPSTONE, true, ModifierRecipe.StatType.INITIAL_HEALTH, 5, 20, 100, true));
        RECIPES.add(new ModifierRecipe(Items.ECHO_SHARD, true, ModifierRecipe.StatType.ECHOING, 1, 3, 0, false));
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
