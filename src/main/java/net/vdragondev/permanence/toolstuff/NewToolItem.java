package net.vdragondev.permanence.toolstuff;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class NewToolItem extends Item {

    private final ToolMaterial material;

    public NewToolItem(ToolMaterial material, Settings settings) {
        super(settings.maxDamageIfAbsent(material.getDurability()));
        this.material = material;
    }

    public ToolMaterial getMaterial() {
        return this.material;
    }

    public int getEnchantability() {
        return this.material.getEnchantability();
    }

    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }
    //give @p permanence:basic_drill{Damage:2031} 1
}
