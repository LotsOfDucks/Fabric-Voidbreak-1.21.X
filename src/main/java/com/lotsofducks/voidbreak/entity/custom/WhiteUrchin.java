package com.lotsofducks.voidbreak.entity.custom;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.block.ModTags;
import com.lotsofducks.voidbreak.entity.ai.goal.custom.EatLichenGoal;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class WhiteUrchin extends AnimalEntity {
    public WhiteUrchin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createUrchinAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.10);
    }

    public void tick() {
        super.tick();
    }


    public void onPlayerCollision(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity && player.canTakeDamage() && !player.isInSneakingPose()) {
            if (player.hurtTime == 0 && player.fallDistance < 1) {
                player.damage(this.getDamageSources().mobAttack(this), 1);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80, 0), this);
                this.playSound(SoundEvents.ENTITY_SHULKER_OPEN, 1.0F, 5.0F);
            }
        }
        if (player.fallDistance >= 1) {
            player.handleFallDamage(fallDistance + 4.0F, 2.0F, this.getDamageSources().sting(this));
            this.playSound(SoundEvents.ENTITY_SHULKER_OPEN, 1.0F, 5.0F);
        }
    }

    public boolean isCollidable() {
        return this.isAlive();
    }

    private static final int MAX_LICHEN_TIMER = 40;
    private int eatLichenTimer;
    private EatLichenGoal eatLichenGoal;

    protected void initGoals() {
        this.eatLichenGoal = new EatLichenGoal(this);
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.1, (stack) -> stack.isIn(ModTags.Items.URCHIN_FOOD), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(5, this.eatLichenGoal);
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    protected void mobTick() {
        this.eatLichenTimer = this.eatLichenGoal.getTimer();
        super.mobTick();
    }

    public void tickMovement() {
        if (this.getWorld().isClient) {
            this.eatLichenTimer = Math.max(0, this.eatLichenTimer - 1);
        }

        super.tickMovement();
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos.down()).isOf(ModBlocks.CHALKY_GRASS) ? 10.0F : world.getPhototaxisFavor(pos);
    }

    public void handleStatus(byte status) {
        if (status == 10) {
            this.eatLichenTimer = 40;
        } else {
            super.handleStatus(status);
        }

    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_PACKED_MUD_PLACE;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_PACKED_MUD_BREAK;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ModTags.Items.URCHIN_FOOD);
    }

    public void breed(ServerWorld world, AnimalEntity other) {
        PassiveEntity passiveEntity = this.createChild(world, other);
        if (passiveEntity != null) {
            passiveEntity.setBaby(true);
            passiveEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.breed(world, other, passiveEntity);
            world.spawnEntityAndPassengers(passiveEntity);
        }
    }

    public @Nullable WhiteUrchin createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return Voidbreak.WHITE_URCHIN.create(world);
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
