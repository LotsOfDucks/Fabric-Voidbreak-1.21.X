package com.lotsofducks.voidbreak.datagen;

import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.block.ModTags;
import com.lotsofducks.voidbreak.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

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
    }
}
