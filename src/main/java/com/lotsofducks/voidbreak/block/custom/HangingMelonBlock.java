package com.lotsofducks.voidbreak.block.custom;


import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.entity.damage.ModDamageSources;
import com.lotsofducks.voidbreak.entity.damage.ModDamageTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LandingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;

import java.util.function.Predicate;

import static net.minecraft.block.FallingBlock.canFallThrough;

public class HangingMelonBlock extends CeilingStemBlock {

    public static final int MAX_AGE = 3;
    public static final IntProperty AGE;
    private static final VoxelShape[] AGE_TO_SHAPE;
    private static final float FALLING_BLOCK_ENTITY_DAMAGE_MULTIPLIER = 1.0F;
    private static final int FALLING_BLOCK_ENTITY_MAX_DAMAGE = 10;
    static {
        AGE = Properties.AGE_3;
        AGE_TO_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(7.0, 14.0, 7.0, 9.0, 16.0, 9.0),
                Block.createCuboidShape(5.0, 10.0, 5.0, 11.0, 16.0, 11.0),
                Block.createCuboidShape(3.0, 6.0, 3.0, 13.0, 16.0, 13.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        };
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected IntProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 3;
    }

    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    protected boolean hasRandomTicks(BlockState state) {
        return !this.isMature(state);
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 0) {
            int i = this.getAge(state);
            if (i < MAX_AGE) {
                if (random.nextInt(7) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
            if (i == MAX_AGE) {
                BlockPos blockPos = pos.up();
                world.setBlockState(blockPos, ModBlocks.LUMEI_STEM.getDefaultState());
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

    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        world.scheduleBlockTick(pos, this, this.getFallDelay());
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected boolean canPlantBelow(BlockState floor) {
        return floor.isOf(ModBlocks.LUMEI_STEM);
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
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
        entity.setHurtEntities(1.0F, 20);
        entity.handleFallDamage(1.0F,20F, );
    }



    protected int getFallDelay() {
        return 2;
    }

    public HangingMelonBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(this.getAgeProperty(), 0));
    }
}
