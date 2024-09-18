package com.lotsofducks.voidbreak.datagen;

import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.block.ModTags;
import com.lotsofducks.voidbreak.item.ModItems;
import com.mojang.datafixers.types.templates.Tag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureSet;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.CHALK_DUST, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHALK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMEN_PLANKS_BLUE, 4)
                .input(Ingredient.fromTag(ModTags.Items.LUMEN_LOGS_BLUE))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_LOG_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_LOG_BLUE))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.STRIPPED_LUMEN_LOG_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.STRIPPED_LUMEN_LOG_BLUE))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_WOOD_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_WOOD_BLUE))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.STRIPPED_LUMEN_WOOD_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.STRIPPED_LUMEN_WOOD_BLUE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMEN_SLAB_BLUE, 6)
                .input('T', ModBlocks.LUMEN_PLANKS_BLUE)
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_PLANKS_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_PLANKS_BLUE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMEN_STAIRS_BLUE, 4)
                .input('T', ModBlocks.LUMEN_PLANKS_BLUE)
                .pattern("T  ")
                .pattern("TT ")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_PLANKS_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_PLANKS_BLUE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMEN_FENCE_BLUE, 3)
                .input('T', ModBlocks.LUMEN_PLANKS_BLUE)
                .input('S', Items.STICK)
                .pattern("TST")
                .pattern("TST")
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_PLANKS_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_PLANKS_BLUE))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK), FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMEN_FENCE_GATE_BLUE, 1)
                .input('T', ModBlocks.LUMEN_PLANKS_BLUE)
                .input('S', Items.STICK)
                .pattern("STS")
                .pattern("STS")
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_PLANKS_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_PLANKS_BLUE))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK), FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMEN_DOOR_BLUE, 3)
                .input('T', ModBlocks.LUMEN_PLANKS_BLUE)
                .pattern("TT ")
                .pattern("TT ")
                .pattern("TT ")
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_PLANKS_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_PLANKS_BLUE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMEN_TRAPDOOR_BLUE, 3)
                .input('T', ModBlocks.LUMEN_PLANKS_BLUE)
                .pattern("TTT")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_PLANKS_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_PLANKS_BLUE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.LUMEN_BUTTON_BLUE)
                .input(ModBlocks.LUMEN_PLANKS_BLUE)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_PLANKS_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_PLANKS_BLUE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.LUMEN_PRESSURE_PLATE_BLUE, 1)
                .input('T', ModBlocks.LUMEN_PLANKS_BLUE)
                .pattern("TT")
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LUMEN_PLANKS_BLUE), FabricRecipeProvider.conditionsFromItem(ModBlocks.LUMEN_PLANKS_BLUE))
                .offerTo(exporter);

        var lumenFamilyBlue = new BlockFamily.Builder(ModBlocks.LUMEN_PLANKS_BLUE)
                .button(ModBlocks.LUMEN_BUTTON_BLUE)
                .fence(ModBlocks.LUMEN_FENCE_BLUE)
                .fenceGate(ModBlocks.LUMEN_FENCE_GATE_BLUE)
                .slab(ModBlocks.LUMEN_SLAB_BLUE)
                .stairs(ModBlocks.LUMEN_STAIRS_BLUE)
                .door(ModBlocks.LUMEN_DOOR_BLUE)
                .trapdoor(ModBlocks.LUMEN_TRAPDOOR_BLUE)
                .group("wooden")
                .unlockCriterionName("has_planks")
                .build();

        generateFamily(exporter, lumenFamilyBlue, FeatureSet.empty());
    }
}
