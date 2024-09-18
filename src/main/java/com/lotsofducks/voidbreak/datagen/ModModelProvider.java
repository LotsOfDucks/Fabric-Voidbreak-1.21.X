package com.lotsofducks.voidbreak.datagen;

import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.family.BlockFamily;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GNEISS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHALK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHALKY_DIRT);
        blockStateModelGenerator.registerLog(ModBlocks.LUMEN_LOG_BLUE)
                .log(ModBlocks.LUMEN_LOG_BLUE)
                .wood(ModBlocks.LUMEN_WOOD_BLUE);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_LUMEN_LOG_BLUE)
                .log(ModBlocks.STRIPPED_LUMEN_LOG_BLUE)
                .wood(ModBlocks.STRIPPED_LUMEN_WOOD_BLUE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUMEN_LEAVES_BLUE);
        blockStateModelGenerator.registerTintableCross(ModBlocks.LUMEN_SAPLING_BLUE, BlockStateModelGenerator.TintType.NOT_TINTED);

        var lumenFamilyBlue = new BlockFamily.Builder(ModBlocks.LUMEN_PLANKS_BLUE)
                .button(ModBlocks.LUMEN_BUTTON_BLUE)
                .pressurePlate(ModBlocks.LUMEN_PRESSURE_PLATE_BLUE)
                .fence(ModBlocks.LUMEN_FENCE_BLUE)
                .fenceGate(ModBlocks.LUMEN_FENCE_GATE_BLUE)
                .slab(ModBlocks.LUMEN_SLAB_BLUE)
                .stairs(ModBlocks.LUMEN_STAIRS_BLUE)
                .door(ModBlocks.LUMEN_DOOR_BLUE)
                .trapdoor(ModBlocks.LUMEN_TRAPDOOR_BLUE)
                .group("wooden")
                .unlockCriterionName("has_planks")
                .build();
        blockStateModelGenerator.registerCubeAllModelTexturePool(lumenFamilyBlue.getBaseBlock())
                .family(lumenFamilyBlue);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TEST_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHALK_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOID_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOIDROOT_SPROUT, Models.GENERATED);
    }
}
