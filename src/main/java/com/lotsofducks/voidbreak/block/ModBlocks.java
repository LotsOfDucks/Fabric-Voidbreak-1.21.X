package com.lotsofducks.voidbreak.block;

import com.lotsofducks.voidbreak.Voidbreak;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BLEEDING_STONE = registerBlock("bleeding_stone",
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(1.0F, 4.0F)));

    public static final Block BLEEDING_DEEPSLATE = registerBlock("bleeding_deepslate",
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(2.5F, 4.0F)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Voidbreak.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Voidbreak.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Voidbreak.LOGGER.info("Registering Mod Blocks for " + Voidbreak.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.BLEEDING_STONE);
            entries.add(ModBlocks.BLEEDING_DEEPSLATE);
        });
    }
}
