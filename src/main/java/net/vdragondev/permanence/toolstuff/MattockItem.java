package net.vdragondev.permanence.toolstuff;

import net.minecraft.block.BlockState;
import net.minecraft.item.*;

import java.util.HashSet;

public class MattockItem extends MiningToolItem {

    public MattockItem(float attackDamage, float attackSpeed, ToolMaterial material, Settings settings) {
        super(attackDamage, attackSpeed, material, new HashSet<>(), settings);
    }

    @Override
    public boolean isEffectiveOn(BlockState state) {
        return true;
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return miningSpeed;
    }
}
