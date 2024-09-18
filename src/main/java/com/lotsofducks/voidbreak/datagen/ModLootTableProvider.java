package com.lotsofducks.voidbreak.datagen;

import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CHALKY_DIRT);
        addDrop(ModBlocks.CHALKY_GRASS, drops(ModBlocks.CHALKY_GRASS, ModBlocks.CHALKY_DIRT));
        addDrop(ModBlocks.GNEISS);
        addDrop(ModBlocks.BLEEDING_STONE, drops(ModBlocks.BLEEDING_STONE, Blocks.COBBLESTONE));
        addDrop(ModBlocks.BLEEDING_DEEPSLATE, drops(ModBlocks.BLEEDING_DEEPSLATE, Blocks.COBBLED_DEEPSLATE));
        addDrop(ModBlocks.LUMEN_LOG_BLUE);
        addDrop(ModBlocks.STRIPPED_LUMEN_LOG_BLUE);
        addDrop(ModBlocks.LUMEN_WOOD_BLUE);
        addDrop(ModBlocks.STRIPPED_LUMEN_WOOD_BLUE);
        addDrop(ModBlocks.LUMEN_BUTTON_BLUE);
        addDrop(ModBlocks.LUMEN_DOOR_BLUE);
        addDrop(ModBlocks.LUMEN_FENCE_BLUE);
        addDrop(ModBlocks.LUMEN_FENCE_GATE_BLUE);
        addDrop(ModBlocks.LUMEN_HANGING_SIGN_BLUE, drops(ModItems.LUMEN_HANGING_SIGN_BLUE));
        addDrop(ModBlocks.LUMEN_WALL_HANGING_SIGN_BLUE, drops(ModItems.LUMEN_HANGING_SIGN_BLUE));
        addDrop(ModBlocks.LUMEN_SIGN_BLUE, drops(ModItems.LUMEN_SIGN_BLUE));
        addDrop(ModBlocks.LUMEN_WALL_SIGN_BLUE, drops(ModItems.LUMEN_SIGN_BLUE));
        addDrop(ModBlocks.LUMEN_PRESSURE_PLATE_BLUE);
        addDrop(ModBlocks.LUMEN_SLAB_BLUE);
        addDrop(ModBlocks.LUMEN_STAIRS_BLUE);
        addDrop(ModBlocks.LUMEN_TRAPDOOR_BLUE);
        addDrop(ModBlocks.CHALK, multipleOreDrops(ModBlocks.CHALK, ModItems.CHALK_DUST, 3, 9));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
