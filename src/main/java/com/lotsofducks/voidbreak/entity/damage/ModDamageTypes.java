package com.lotsofducks.voidbreak.entity.damage;

import com.lotsofducks.voidbreak.Voidbreak;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;


public class ModDamageTypes  {

        public static final RegistryKey<DamageType> FALLING_MELON = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Voidbreak.MOD_ID, "falling_melon"));
        public static final RegistryKey<DamageType> URCHIN_PRICK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Voidbreak.MOD_ID, "urchin_prick"));
    public static final RegistryKey<DamageType> FALL_ON_URCHIN = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Voidbreak.MOD_ID, "fall_on_urchin"));

        public static DamageSource of(Entity entity, RegistryKey<DamageType> key) {
            return new DamageSource(entity.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
        }
}
