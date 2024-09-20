package com.lotsofducks.voidbreak.entity.damage;

import com.lotsofducks.voidbreak.Voidbreak;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public interface ModDamageTypes  {
        RegistryKey<DamageType> FALLING_MELON = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Voidbreak.MOD_ID, "falling_melon"));


        static void bootstrap(Registerable<DamageType> damageTypeRegisterable) {
            damageTypeRegisterable.register(FALLING_MELON, new DamageType("fallingMelon", 0.1F, DamageEffects.FREEZING));
        }
}
