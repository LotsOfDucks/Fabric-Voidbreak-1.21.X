package com.lotsofducks.voidbreak.entity.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;

public class ModDamageSources extends DamageSources {
    public final Registry<DamageType> registry;

    public ModDamageSources(DynamicRegistryManager registryManager) {
        super(registryManager);
        this.registry = registryManager.get(RegistryKeys.DAMAGE_TYPE);
    }

    public DamageSource fallingMelon(Entity attacker) {
        return this.create(ModDamageTypes.FALLING_MELON, attacker);
    }
}
