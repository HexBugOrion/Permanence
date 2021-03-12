package net.vdragondev.permanence.mats;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum  PermaToolMaterials implements ToolMaterial {
    BASIC_MACHINE(3, 2031, 8.0F, 3.0F, 0, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.COAL});
    }),
    PICKFINDER(3, 2031, 8.0F, 3.0F, 0, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.STONE_PICKAXE});
    }),
    ADVANCED_MACHINE(3, 2031, 8.0F, 3.0F, 0, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.COAL_BLOCK});
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