package com.lotsofducks.voidbreak.block;

import com.lotsofducks.voidbreak.Voidbreak;
import net.minecraft.block.WoodType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModWoodTypes {
    public static final WoodType LUMEN_BLUE = new WoodType("voidbreak:lumen_blue",
            ModBlockSetTypes.LUMEN_BLUE,
            BlockSoundGroup.WOOD,
            BlockSoundGroup.HANGING_SIGN,
            SoundEvents.BLOCK_FENCE_GATE_CLOSE,
            SoundEvents.BLOCK_FENCE_GATE_OPEN);


    public static void registerModWoodTypes() {
        Voidbreak.LOGGER.info("Registering Mod Wood Types for " + Voidbreak.MOD_ID);
    }
}

