package com.lotsofducks.voidbreak.block;

import com.lotsofducks.voidbreak.Voidbreak;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> LUMEN_LOGS_BLUE = TagKey.of(RegistryKeys.BLOCK, Identifier.of("voidbreak:lumen_logs_blue"));

    }

    public static class Items {

        public static final TagKey<Item> LUMEN_LOGS_BLUE = TagKey.of(RegistryKeys.ITEM, Identifier.of("voidbreak:lumen_logs_blue"));

    }

    public static void registerModTags() {
        Voidbreak.LOGGER.info("Registering Mod Tags for " + Voidbreak.MOD_ID);
    }
}
