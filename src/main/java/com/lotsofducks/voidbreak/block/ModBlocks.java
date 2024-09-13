package com.lotsofducks.voidbreak.block;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.custom.ChalkyGrassBlock;
import com.lotsofducks.voidbreak.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block GNEISS = registerBlock("gneiss",
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.0F, 7.0F)));

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

    public static final Block CHALKY_DIRT = registerBlock("chalky_dirt",
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.GRAVEL)
                    .mapColor(MapColor.WHITE_GRAY)
                    .strength(0.5F)));

    public static final Block CHALKY_GRASS = registerBlock("chalky_grass",
            new ChalkyGrassBlock(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.GRASS)
                    .mapColor(MapColor.PALE_YELLOW)
                    .strength(0.6F)
                    .ticksRandomly()));

    public static final Block CHALKY_BRUSH = registerBlock("chalky_brush",
            new ShortPlantBlock(AbstractBlock.Settings.create()
                    .nonOpaque()
                    .mapColor(MapColor.PALE_YELLOW)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XYZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

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
            entries.add(ModItems.TEST_ITEM);
        });
    }
}
