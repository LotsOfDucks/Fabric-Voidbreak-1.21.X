package com.lotsofducks.voidbreak.datagen;

import com.lotsofducks.voidbreak.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.LUMEN_FENCE_GATE_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(ModBlocks.LUMEN_LEAVES_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.LUMEN_LOG_BLUE.asItem())
                .add(ModBlocks.STRIPPED_LUMEN_LOG_BLUE.asItem())
                .add(ModBlocks.LUMEN_WOOD_BLUE.asItem())
                .add(ModBlocks.STRIPPED_LUMEN_WOOD_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.LUMEN_PLANKS_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(ModBlocks.LUMEN_SAPLING_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.LUMEN_BUTTON_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.LUMEN_DOOR_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.LUMEN_FENCE_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.LUMEN_PRESSURE_PLATE_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.LUMEN_SLAB_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.LUMEN_STAIRS_BLUE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.LUMEN_TRAPDOOR_BLUE.asItem());
    }
}
