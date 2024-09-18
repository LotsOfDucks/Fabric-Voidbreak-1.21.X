package com.lotsofducks.voidbreak.block.custom;

import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;

public class CeilingStemBlock extends PlantBlock {

    public static final int MAX_AGE = 4;
    public static final IntProperty AGE;
    private static final VoxelShape[] AGE_TO_SHAPE;
    static {
        AGE = Properties.AGE_4;
        AGE_TO_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(7.0, 15.0, 7.0, 9.0, 16.0, 9.0),
                Block.createCuboidShape(7.0, 12.0, 7.0, 9.0, 16.0, 9.0),
                Block.createCuboidShape(7.0, 8.0, 7.0, 9.0, 16.0, 9.0),
                Block.createCuboidShape(6.0, 4.0, 6.0, 10.0, 16.0, 10.0),
                Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0)
        };
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected IntProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 4;
    }

    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    public final boolean isMature(BlockState state) {
        return this.getAge(state) >= this.getMaxAge();
    }

    protected boolean hasRandomTicks(BlockState state) {
        return !this.isMature(state);
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 3) {
            int i = this.getAge(state);
            if (i < (MAX_AGE-1)) {
                if (random.nextInt(7) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
            if (i == (MAX_AGE-1)) {
                BlockPos blockPos = pos.down();
                if (random.nextInt(7) == 0 && world.getBlockState(blockPos).isAir()) {
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                    world.setBlockState(blockPos, ModBlocks.LUMEI_FRUIT.getDefaultState());
                }
            }
        }
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos above = pos.up();
        return hasEnoughLightAt(world, pos) && this.canPlantBelow(world.getBlockState(above));
    }

    protected static boolean hasEnoughLightAt(WorldView world, BlockPos pos) {
        return world.getBaseLightLevel(pos, 0) >= 0;
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof RavagerEntity && world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            world.breakBlock(pos, true, entity);
        }

        super.onEntityCollision(state, world, pos, entity);
    }

    protected ItemConvertible getSeedsItem() {
        return ModItems.LUMEI_BUD;
    }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected boolean canPlantBelow(BlockState floor) {
        return floor.isIn(BlockTags.LEAVES);
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    public CeilingStemBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(this.getAgeProperty(), 0));
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return null;
    }
}
