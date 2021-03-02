package net.vdragondev.permanence.blockentities;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.vdragondev.permanence.PermanenceMod;
import net.vdragondev.permanence.screens.QuernScreenHandler;



public class QuernBlockEntity extends AbstractFurnaceBlockEntity {

    public QuernBlockEntity() {
        super(PermanenceMod.QUERN_TYPE, PermanenceMod.QUERN_RECIPE_TYPE);
    }


    public Text getContainerName() {
        return Text.of("quern");
    }


    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new QuernScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
