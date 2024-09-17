package com.lotsofducks.voidbreak.block;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.custom.*;
import com.lotsofducks.voidbreak.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.apache.http.impl.client.NullBackoffStrategy;

import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final Block GNEISS = registerBlock("gneiss",
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.0F, 6.5F)));

    public static final Block CHALK = registerBlock("chalk",
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.CALCITE)
                    .mapColor(MapColor.OFF_WHITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(1.0F, 2.5F)));

    public static final Block BLEEDING_STONE = registerBlock("bleeding_stone",
            new BleedingBlock(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(1.0F, 3.0F)));

    public static final Block BLEEDING_DEEPSLATE = registerBlock("bleeding_deepslate",
            new BleedingDeepslateBlock(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.5F, 3.0F)));

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
                    .mapColor(MapColor.PALE_YELLOW)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XYZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static Block createModLogBlock(MapColor topMapColor, MapColor sideMapColor, Integer integer) {
        return new PillarBlock(AbstractBlock.Settings.create().mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
                .luminance((state) -> integer));
    }

    public static Block createModLeavesBlock(BlockSoundGroup soundGroup,MapColor mapColor, Integer integer) {
        return new LeavesBlock(AbstractBlock.Settings.create()
                .strength(0.2F)
                .ticksRandomly()
                .sounds(soundGroup)
                .nonOpaque()
                .allowsSpawning(Blocks::canSpawnOnLeaves)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never)
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(Blocks::never)
                .luminance((state) -> integer));
    }

    public static final Block LUMEN_LOG_BLUE = registerBlock("lumen_log_blue",
            ModBlocks.createModLogBlock(MapColor.BLUE, MapColor.LIGHT_GRAY, 3));

    public static final Block STRIPPED_LUMEN_LOG_BLUE = registerBlock("stripped_lumen_log_blue",
            ModBlocks.createModLogBlock(MapColor.BLUE, MapColor.LIGHT_BLUE, 10));

    public static final Block LUMEN_WOOD_BLUE = registerBlock("lumen_wood_blue",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLUE)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .luminance((state) -> 3)));

    public static final Block STRIPPED_LUMEN_WOOD_BLUE = registerBlock("stripped_lumen_wood_blue",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIGHT_BLUE)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .luminance((state) -> 10)));

    public static final Block LUMEN_LEAVES_BLUE = registerBlock("lumen_leaves_blue",
            ModBlocks.createModLeavesBlock(BlockSoundGroup.GRASS,MapColor.LIGHT_BLUE, 13));

    public static final Block LUMEN_SAPLING_BLUE = registerBlock("lumen_sapling_blue",
            new SaplingBlock(SaplingGenerator.OAK, AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLUE)
                    .ticksRandomly()
                    .strength(0.0F)
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
                    .allowsSpawning(Blocks::canSpawnOnLeaves)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .solidBlock(Blocks::never)
                    .luminance((state) -> 6)));

    public static final Block LUMEN_PLANKS_BLUE = registerBlock("lumen_planks_blue",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.BLUE)
                    .luminance((state) -> 10)));

    public static final Block LUMEN_DOOR_BLUE = registerBlock("lumen_door_blue",
            new DoorBlock(ModBlockSetTypes.LUMEN_BLUE, AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .strength(3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .nonOpaque()
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .luminance((state) -> 10)));

    public static final Block LUMEN_FENCE_BLUE = registerBlock("lumen_fence_blue",
            new FenceBlock(AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .strength(3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .luminance((state) -> 10)));

    public static final Block LUMEN_FENCE_GATE_BLUE = registerBlock("lumen_fence_gate_blue",
            new FenceGateBlock(ModWoodTypes.LUMEN_BLUE, AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .strength(3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .burnable()
                    .luminance((state) -> 10)));

    public static final Block LUMEN_STAIRS_BLUE = registerBlock("lumen_stairs_blue",
            new StairsBlock(LUMEN_PLANKS_BLUE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .luminance((state) -> {
                        return 10;
                    })));

    public static final Block LUMEN_SLAB_BLUE = registerBlock("lumen_slab_blue",
            new SlabBlock(AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .strength(2.0F,3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .luminance((state) -> 10)));

    public static final Block LUMEN_PRESSURE_PLATE_BLUE = registerBlock("lumen_pressure_plate_blue",
            new PressurePlateBlock(ModBlockSetTypes.LUMEN_BLUE, AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .strength(0.5F)
                    .solid()
                    .noCollision()
                    .instrument(NoteBlockInstrument.BASS)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .luminance((state) -> 10)));

    public static final Block LUMEN_BUTTON_BLUE = registerBlock("lumen_pressure_plate_blue",
            Blocks.createWoodenButtonBlock(ModBlockSetTypes.LUMEN_BLUE));

    public static final Block LUMEN_TRAPDOOR_BLUE = registerBlock("lumen_trapdoor_blue",
            new TrapdoorBlock(ModBlockSetTypes.LUMEN_BLUE, AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .strength(3.0F)
                    .nonOpaque()
                    .instrument(NoteBlockInstrument.BASS)
                    .burnable()
                    .allowsSpawning(Blocks::never)
                    .luminance((state) -> 10)));

    public static final Block LUMEN_SIGN_BLUE = registerBlock("lumen_sign_blue",
            new SignBlock(WoodType.OAK, AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .burnable()
                    .luminance((state) -> 10)));

    public static final Block LUMEN_WALL_SIGN_BLUE = registerBlock("lumen_wall_sign_blue",
            new WallSignBlock(ModWoodTypes.LUMEN_BLUE, AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .dropsLike(LUMEN_SIGN_BLUE)
                    .burnable()
                    .luminance((state) -> 10)));

    public static final Block LUMEN_HANGING_SIGN_BLUE = registerBlock("lumen_hanging_sign_blue",
            new HangingSignBlock(ModWoodTypes.LUMEN_BLUE, AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .dropsLike(LUMEN_SIGN_BLUE)
                    .burnable()
                    .luminance((state) -> 10)));

    public static final Block LUMEN_WALL_HANGING_SIGN_BLUE = registerBlock("lumen_wall_hanging_sign_blue",
            new WallHangingSignBlock(ModWoodTypes.LUMEN_BLUE, AbstractBlock.Settings.create()
                    .mapColor(LUMEN_PLANKS_BLUE.getDefaultMapColor())
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .dropsLike(LUMEN_SIGN_BLUE)
                    .burnable()
                    .luminance((state) -> 10)));

    public static final Block VOIDROOT = registerBlock("voidroot",
            new CeilingCropBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .nonOpaque()
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    ));

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
