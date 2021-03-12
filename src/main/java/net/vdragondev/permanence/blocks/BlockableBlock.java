package net.vdragondev.permanence.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.piston.PistonBehavior;

public class BlockableBlock extends GlassBlock {
    public BlockableBlock(Settings settings) {
        super(settings);
    }
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }
}
