package net.vdragondev.permanence.quern;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.vdragondev.permanence.PermanenceMod;

public class QuernScreenHandler extends AbstractFurnaceScreenHandler {
    public QuernScreenHandler(int i, PlayerInventory playerInventory) {
        super(PermanenceMod.QUERN_SCREEN, PermanenceMod.QUERN_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory);
    }

    public QuernScreenHandler(int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(PermanenceMod.QUERN_SCREEN, PermanenceMod.QUERN_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory, inventory, propertyDelegate);
    }
}
