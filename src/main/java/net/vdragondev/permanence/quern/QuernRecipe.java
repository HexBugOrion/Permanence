package net.vdragondev.permanence.quern;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.vdragondev.permanence.PermanenceMod;

public class QuernRecipe extends AbstractCookingRecipe {

    public QuernRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(PermanenceMod.QUERN_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }

    @Override
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Items.BLACKSTONE);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PermanenceMod.QUERN_SERIALIZER;
    }
}
