package com.lotsofducks.voidbreak.item;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));
    public static final Item VOIDROOT_SPROUT = registerItem("voidroot_sprout", new AliasedBlockItem(ModBlocks.VOIDROOT, new Item.Settings()));
    public static final Item LUMEI_BUD = registerItem("lumei_bud", new AliasedBlockItem(ModBlocks.LUMEI_STEM, new Item.Settings()));
    public static final Item VOID_BOTTLE = registerItem("void_bottle", new Item(new Item.Settings().maxCount(16)));
    public static final Item CHALK_DUST = registerItem("chalk_dust", new Item(new Item.Settings()));
    public static final Item WHITE_URCHIN_SPAWN_EGG = registerItem("white_urchin_spawn_egg", new SpawnEggItem(Voidbreak.WHITE_URCHIN, 4130306, 16777215, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Voidbreak.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Voidbreak.LOGGER.info("Registering Mod Items for " + Voidbreak.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
           entries.add(TEST_ITEM);
           entries.add(VOIDROOT_SPROUT);
           entries.add(CHALK_DUST);
        });
    }
}
