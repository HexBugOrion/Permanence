package net.vdragondev.permanence.mats;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Lazy;
import net.vdragondev.permanence.PermanenceMod;

import java.util.function.Supplier;

public enum  PermaToolMaterials implements ToolMaterial {
    BASIC_MACHINE(2, 2000, 8.0F, 3.0F, 0, () -> {
        return Ingredient.ofItems((ItemConvertible) ItemTags.LOGS_THAT_BURN);
    }),
    ENHANCED_MACHINE(3, 2000, 8.0F, 3.0F, 0, () -> {
        return Ingredient.ofItems((ItemConvertible) ItemTags.COALS);
    }),
    ADVANCED_MACHINE(4, 18000, 8.0F, 3.0F, 0, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{PermanenceMod.BITUMEN});
    });

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private PermaToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
