package com.lotsofducks.voidbreak.datagen;

import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.block.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.BLEEDING_STONE)
                .add(ModBlocks.BLEEDING_DEEPSLATE)
                .add(ModBlocks.GNEISS)
                .add(ModBlocks.CHALK);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.CHALKY_DIRT)
                .add(ModBlocks.CHALKY_GRASS);

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.LUMEN_HANGING_SIGN_BLUE);

        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(ModBlocks.VOIDROOT);

        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(ModBlocks.CHALKY_DIRT)
                .add(ModBlocks.CHALKY_GRASS);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.LUMEN_LEAVES_BLUE);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.LUMEN_LOG_BLUE)
                .add(ModBlocks.STRIPPED_LUMEN_LOG_BLUE)
                .add(ModBlocks.LUMEN_WOOD_BLUE)
                .add(ModBlocks.STRIPPED_LUMEN_WOOD_BLUE);

        getOrCreateTagBuilder(ModTags.Blocks.LUMEN_LOGS_BLUE)
                .add(ModBlocks.LUMEN_LOG_BLUE)
                .add(ModBlocks.STRIPPED_LUMEN_LOG_BLUE)
                .add(ModBlocks.LUMEN_WOOD_BLUE)
                .add(ModBlocks.STRIPPED_LUMEN_WOOD_BLUE);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.LUMEN_SAPLING_BLUE);

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.LUMEN_SIGN_BLUE);

        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.LUMEN_WALL_HANGING_SIGN_BLUE);

        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(ModBlocks.LUMEN_WALL_SIGN_BLUE);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.LUMEN_BUTTON_BLUE);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.LUMEN_DOOR_BLUE);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.LUMEN_FENCE_BLUE);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.LUMEN_PRESSURE_PLATE_BLUE);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.LUMEN_SLAB_BLUE);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.LUMEN_STAIRS_BLUE);
    }
}
