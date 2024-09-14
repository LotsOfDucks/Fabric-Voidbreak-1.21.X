package com.lotsofducks.voidbreak.item;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));
    public static final Item VOID_ROOT_SPROUT = registerItem("void_root_sprout", new AliasedBlockItem(ModBlocks.VOID_ROOT, new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Voidbreak.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Voidbreak.LOGGER.info("Registering Mod Items for " + Voidbreak.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
           entries.add(TEST_ITEM);
           entries.add(VOID_ROOT_SPROUT);
        });
    }
}
