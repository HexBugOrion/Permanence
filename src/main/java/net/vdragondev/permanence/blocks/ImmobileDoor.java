package net.vdragondev.permanence.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.piston.PistonBehavior;

public class ImmobileDoor extends DoorBlock {
    public ImmobileDoor(Settings settings) {
        super(settings);
    }
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }
}
