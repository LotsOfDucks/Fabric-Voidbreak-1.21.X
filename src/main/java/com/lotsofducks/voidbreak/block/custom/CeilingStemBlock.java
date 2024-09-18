package com.lotsofducks.voidbreak.block.custom;

import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class CeilingStemBlock extends CropBlock {

    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = Properties.AGE_4;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(7.0, 15.0, 7.0, 9.0, 16.0, 9.0),
            Block.createCuboidShape(7.0, 12.0, 7.0, 9.0, 16.0, 9.0),
            Block.createCuboidShape(7.0, 8.0, 7.0, 9.0, 16.0, 9.0),
            Block.createCuboidShape(6.0, 4.0, 6.0, 10.0, 16.0, 10.0),
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0)
    };

    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 10) {
            int i = this.getAge(state);
            if (i < MAX_AGE) {
                if (random.nextInt(7) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
            if (i == MAX_AGE) {
                BlockPos blockPos = pos.down();
                if (world.getBlockState(blockPos).isAir()) {
                    world.setBlockState(blockPos, ModBlocks.LUMEI_FRUIT.getDefaultState());
                }
            }
        }
    }

    @Override
    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 2, 3);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return hasEnoughLightAt(world, pos) && this.canPlantBelow(world.getBlockState(blockPos));
    }

    protected static boolean hasEnoughLightAt(WorldView world, BlockPos pos) {
        return world.getBaseLightLevel(pos, 0) >= 9;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.LUMEI_BUD;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected boolean canPlantBelow(BlockState floor) {
        return floor.isIn(BlockTags.LEAVES);
    }

    public CeilingStemBlock(Settings settings) {
        super(settings);
    }
}
