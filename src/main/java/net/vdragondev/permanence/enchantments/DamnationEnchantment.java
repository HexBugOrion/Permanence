package net.vdragondev.permanence.enchantments;

import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class DamnationEnchantment extends Enchantment {
    private static final int[] field1 = new int[]{1, 5, 5};
    private static final int[] field2 = new int[]{11, 8, 8};
    private static final int[] field3 = new int[]{20, 20, 20};
    public final int typeIndex;

    public DamnationEnchantment(int typeIndex){
        super(Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
        this.typeIndex = typeIndex;
    }

    @Override public int getMaxLevel() {
        return 5;
    }

    @Override public int getMinPower(int level) {
        return field1[this.typeIndex] + (level - 1) * field2[this.typeIndex];
    }

    @Override public int getMaxPower(int level) {
        return this.getMinPower(level) + field3[this.typeIndex];
    }

    @Override public float getAttackDamage(int level, EntityGroup group) {
        if (this.typeIndex == 0 && group == EntityGroup.DEFAULT){
            return 2;
        } else {
            return this.typeIndex == 1 && group == EntityGroup.UNDEAD ? (float)level * 0 : 0;
        }
    }

    @Override public boolean canAccept(Enchantment other) {
        return !(other instanceof DamageEnchantment);
    }
}
