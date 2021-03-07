package net.vdragondev.permanence.quern;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.vdragondev.permanence.PermanenceMod;



public class QuernBlockEntity extends AbstractFurnaceBlockEntity {

    public QuernBlockEntity() {
        super(PermanenceMod.QUERN_TYPE, PermanenceMod.QUERN_RECIPE_TYPE);
    }


    public static final Text NAME = new TranslatableText("container.quern");


    @Override protected Text getContainerName() {
        return NAME;
    }

    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new QuernScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
