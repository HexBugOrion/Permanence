package net.vdragondev.permanence.kiln;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.vdragondev.permanence.PermanenceMod;

public class KilnRecipe extends AbstractCookingRecipe {

    public KilnRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(PermanenceMod.KILN_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }

    @Override
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(PermanenceMod.KILN);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PermanenceMod.KILN_SERIALIZER;
    }
}
