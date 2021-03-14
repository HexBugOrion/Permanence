package net.vdragondev.permanence.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.piston.PistonBehavior;

public class ImmobleDoor extends DoorBlock {
    public ImmobleDoor(Settings settings) {
        super(settings);
    }
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }
}
