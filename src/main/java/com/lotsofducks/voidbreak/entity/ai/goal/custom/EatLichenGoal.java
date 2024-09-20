package com.lotsofducks.voidbreak.entity.ai.goal.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.EnumSet;
import java.util.function.Predicate;

public class EatLichenGoal extends Goal {
    private static final int MAX_TIMER = 40;
    private static final Predicate<BlockState> LICHEN_PREDICATE;
    private final MobEntity mob;
    private final World world;
    private int timer;

    public EatLichenGoal(MobEntity mob) {
        this.mob = mob;
        this.world = mob.getWorld();
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
    }

    public boolean canStart() {
        BlockPos blockPos = this.mob.getBlockPos();
        return LICHEN_PREDICATE.test(this.world.getBlockState(blockPos));
    }

    public void start() {
        this.timer = this.getTickCount(40);
        this.world.sendEntityStatus(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    public void stop() {
        this.timer = 0;
    }

    public boolean shouldContinue() {
        return this.timer > 0;
    }

    public int getTimer() {
        return this.timer;
    }

    public void tick() {
        this.timer = Math.max(0, this.timer - 1);
        if (this.timer == this.getTickCount(4)) {
            BlockPos blockPos = this.mob.getBlockPos();
            if (LICHEN_PREDICATE.test(this.world.getBlockState(blockPos))) {
                if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    this.world.breakBlock(blockPos, false);
                }

                this.onEatingLichen();
            }
        }
    }

    public void onEatingLichen() {
        this.mob.emitGameEvent(GameEvent.EAT);
    }

    static {
        LICHEN_PREDICATE = BlockStatePredicate.forBlock(Blocks.GLOW_LICHEN);
    }
}
