package net.vdragondev.permanence.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.MagmaBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PropulsionGelBlock extends Block {
    public PropulsionGelBlock(Settings settings) {
        super(settings);
    }

    @Override public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        if (entity.velocityModified) {
            entity.horizontalSpeed = 0.0001f;
        }
        super.onSteppedOn(world, pos, entity);
    }
}
