package net.vdragondev.permanence.kiln;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.vdragondev.permanence.PermanenceMod;



public class KilnBlockEntity extends AbstractFurnaceBlockEntity {

    public KilnBlockEntity() {
        super(PermanenceMod.KILN_TYPE, PermanenceMod.KILN_RECIPE_TYPE);
    }


    public static final Text NAME = new TranslatableText("container.kiln");


    @Override protected Text getContainerName() {
        return NAME;
    }

    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new KilnScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
