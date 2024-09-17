package com.lotsofducks.voidbreak.block;

import com.lotsofducks.voidbreak.Voidbreak;
import net.minecraft.block.BlockSetType;

public class ModBlockSetTypes {
    public static final BlockSetType LUMEN_BLUE = new BlockSetType("voidbreak:lumen_blue");



    public static void registerModBlockSetTypes() {
        Voidbreak.LOGGER.info("Registering Mod Block Set Types for " + Voidbreak.MOD_ID);
    }
}

