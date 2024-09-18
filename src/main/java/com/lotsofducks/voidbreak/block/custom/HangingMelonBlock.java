package com.lotsofducks.voidbreak.block.custom;

import com.lotsofducks.voidbreak.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.FallingBlockEntity;
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

public class HangingMelonBlock extends CeilingStemBlock{
    public HangingMelonBlock(Settings settings) {
        super(settings);
    }

    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(7.0, 14.0, 7.0, 9.0, 16.0, 9.0),
            Block.createCuboidShape(5.0, 10.0, 5.0, 11.0, 16.0, 11.0),
            Block.createCuboidShape(3.0, 6.0, 3.0, 13.0, 16.0, 13.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(7.0, 14.0, 7.0, 9.0, 16.0, 9.0),
            Block.createCuboidShape(5.0, 10.0, 5.0, 11.0, 16.0, 11.0),
            Block.createCuboidShape(3.0, 6.0, 3.0, 13.0, 16.0, 13.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(7.0, 14.0, 7.0, 9.0, 16.0, 9.0)
    };

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 10) {
            int i = this.getAge(state);
            if (i < MAX_AGE) {
                if (random.nextInt(4) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
            if (i == MAX_AGE) {
                BlockPos blockPos = pos.up();
                world.setBlockState(blockPos, ModBlocks.LUMEI_STEM.getDefaultState());
            }
        }
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModBlocks.LUMEI_MELON.asItem();
    }

    @Override
    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 1, 2);
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
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
            world.scheduleBlockTick(pos, this, this.getFallDelay());
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected boolean canPlantBelow(BlockState floor) {
        return floor.isOf(ModBlocks.LUMEI_STEM);
    }

    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, this.getFallDelay());
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = this.getAge(state);
        if (canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY() && i == MAX_AGE) {
            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, ModBlocks.LUMEI_MELON.getDefaultState());
            world.setBlockState(pos.up(), ModBlocks.LUMEI_STEM.getDefaultState());
            this.configureFallingBlockEntity(fallingBlockEntity);
        }
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
    }

    protected int getFallDelay() {
        return 5;
    }

    public static boolean canFallThrough(BlockState state) {
        return state.isAir() || state.isIn(BlockTags.FIRE) || state.isReplaceable();
    }
}
