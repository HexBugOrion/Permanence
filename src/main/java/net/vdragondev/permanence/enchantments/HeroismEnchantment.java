package net.vdragondev.permanence.enchantments;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.VindicatorEntity;

public class HeroismEnchantment extends Enchantment{
    private static final int[] field1 = new int[]{1, 5, 5};
    private static final int[] field2 = new int[]{11, 8, 8};
    private static final int[] field3 = new int[]{20, 20, 20};
    public final int typeIndex;

    public HeroismEnchantment(int typeIndex){
        super(Enchantment.Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
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
        if (this.typeIndex == 0 && group == EntityGroup.ILLAGER){
            return 2;
        } else {
            return this.typeIndex == 1 && group == EntityGroup.DEFAULT ? (float)level * 0 : 0;
        }
    }

    @Override public boolean canAccept(Enchantment other) {
        return !(other instanceof DamageEnchantment);
    }
}
