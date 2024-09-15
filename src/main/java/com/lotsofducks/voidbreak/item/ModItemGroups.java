package com.lotsofducks.voidbreak.item;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup VOIDBREAK_MISC_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Voidbreak.MOD_ID, "voidbreak_misc"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.TEST_ITEM))
                    .displayName(Text.translatable("itemgroup.voidbreak.misc"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TEST_ITEM);
                    }).build());

    public static final ItemGroup VOIDBREAK_NATURAL_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Voidbreak.MOD_ID, "voidbreak_natural_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.CHALKY_GRASS))
                    .displayName(Text.translatable("itemgroup.voidbreak.natural_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.GNEISS);
                        entries.add(ModBlocks.CHALK);
                        entries.add(ModBlocks.BLEEDING_STONE);
                        entries.add(ModBlocks.BLEEDING_DEEPSLATE);
                        entries.add(ModBlocks.CHALKY_DIRT);
                        entries.add(ModBlocks.CHALKY_GRASS);
                        entries.add(ModBlocks.CHALKY_BRUSH);
                        entries.add(ModItems.VOIDROOT_SPROUT);
                    }).build());



    public static void registeritemGroups() {
        Voidbreak.LOGGER.info("Registering Item Groups for " + Voidbreak.MOD_ID);
    }
}
