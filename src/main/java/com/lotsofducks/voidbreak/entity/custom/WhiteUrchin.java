package com.lotsofducks.voidbreak.entity.custom;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.block.ModTags;
import com.lotsofducks.voidbreak.entity.ai.goal.custom.EatLichenGoal;
import com.lotsofducks.voidbreak.entity.damage.ModDamageTypes;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
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

import java.util.List;
import java.util.function.Predicate;

public class WhiteUrchin extends AnimalEntity {
    public WhiteUrchin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createUrchinAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.10)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0);
    }

    private static final int MAX_LICHEN_TIMER = 40;
    private int eatLichenTimer;
    private EatLichenGoal eatLichenGoal;
    private static final Predicate<LivingEntity> PRICK_FILTER;
    private static final Predicate<LivingEntity> RIDE_FILTER;
    static final TargetPredicate PRICK_TARGET_PREDICATE;
    static final TargetPredicate RIDE_PREDICATE;

    protected void initGoals() {
        this.eatLichenGoal = new EatLichenGoal(this);
        this.goalSelector.add(0, new FleeEntityGoal(this, IronGolemEntity.class, 4.0F, 1.0, 1.2));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.1, (stack) -> stack.isIn(ModTags.Items.URCHIN_FOOD), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(5, this.eatLichenGoal);
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos.down()).isOf(ModBlocks.CHALKY_GRASS) ? 10.0F : world.getPhototaxisFavor(pos);
    }

    public boolean isCollidable() {
        return this.isAlive();
    }

    protected int getNextAirUnderwater(int air) {
        return air;
    }

    public void tick() {
        super.tick();
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
        if (this.isAlive()) {
            List<MobEntity> list = this.getWorld().getEntitiesByClass(MobEntity.class, this.getBoundingBox().expand(0.2), (entity) -> PRICK_TARGET_PREDICATE.test(this, entity));
            List<MobEntity> list2 = this.getWorld().getEntitiesByClass(MobEntity.class, this.getBoundingBox().expand(0.4), (entity) -> RIDE_PREDICATE.test(this, entity));
            for (MobEntity mobEntity : list) {
                if (mobEntity.isAlive()) {
                    this.prick(mobEntity);
                }
            }
            if (this.isBaby() && this.isOnGround()) {
                for (MobEntity mobEntity : list2) {
                    if (mobEntity.isAlive() && !mobEntity.isBaby()) {
                        this.startRiding(mobEntity);
                    }
                }
            }
            if (!this.isBaby()) {
                for (MobEntity mobEntity : list2) {
                    if (this.hasPassenger(mobEntity) && !mobEntity.isBaby()) {
                        mobEntity.stopRiding();
                    }
                }
            }
        }
    }

    private void prick(MobEntity mob) {
        DamageSource damageSource = new DamageSource(
                mob.getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(ModDamageTypes.FALL_ON_URCHIN)
        );
        DamageSource damageSource2 = new DamageSource(
                mob.getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(ModDamageTypes.URCHIN_PRICK)
        );
        if (mob.hurtTime == 0 && mob.fallDistance < 1) {
            mob.damage(damageSource2, 1);
            mob.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80, 0), this);
            this.playSound(SoundEvents.ENTITY_SHULKER_OPEN, 1.0F, 5.0F);
        }
        if (mob.hurtTime == 0 && mob.fallDistance >= 1) {
            mob.handleFallDamage(mob.fallDistance + 4.0F, 2.0F, damageSource);
            this.playSound(SoundEvents.ENTITY_SHULKER_OPEN, 1.0F, 5.0F);
        }

    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        DamageSource damageSource = new DamageSource(
                player.getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(ModDamageTypes.FALL_ON_URCHIN)
        );
        DamageSource damageSource2 = new DamageSource(
                player.getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(ModDamageTypes.URCHIN_PRICK)
        );
        if (player instanceof ServerPlayerEntity && player.canTakeDamage() && !player.isInSneakingPose()) {
            if (player.hurtTime == 0 && player.fallDistance < 1) {
                player.damage(damageSource2, 1);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80, 0), this);
                this.playSound(SoundEvents.ENTITY_SHULKER_OPEN, 1.0F, 5.0F);
            }
        }
        if (player.hurtTime == 0 && player.fallDistance >= 1) {
            player.handleFallDamage(player.fallDistance + 4.0F, 2.0F, damageSource);
            this.playSound(SoundEvents.ENTITY_SHULKER_OPEN, 1.0F, 5.0F);
        }
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

    public void breed(ServerWorld world, AnimalEntity other) {
        PassiveEntity passiveEntity = this.createChild(world, other);
        if (passiveEntity != null) {
            passiveEntity.setBaby(true);
            passiveEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.breed(world, other, passiveEntity);
            world.spawnEntityAndPassengers(passiveEntity);
        }
    }

    static {
        PRICK_FILTER = (entity) -> !(entity instanceof WhiteUrchin) && !(entity instanceof PlayerEntity);
        RIDE_FILTER = (entity) -> (entity instanceof WhiteUrchin);
        PRICK_TARGET_PREDICATE = TargetPredicate.createNonAttackable().ignoreDistanceScalingFactor().ignoreVisibility().setPredicate(PRICK_FILTER);
        RIDE_PREDICATE = TargetPredicate.createNonAttackable().ignoreDistanceScalingFactor().ignoreVisibility().setPredicate(RIDE_FILTER);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ModTags.Items.URCHIN_FOOD);
    }

    public @Nullable WhiteUrchin createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return Voidbreak.WHITE_URCHIN.create(world);
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
