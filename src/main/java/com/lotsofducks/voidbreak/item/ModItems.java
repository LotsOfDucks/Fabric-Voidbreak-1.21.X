package com.lotsofducks.voidbreak.item;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));
    public static final Item VOIDROOT_SPROUT = registerItem("voidroot_sprout", new AliasedBlockItem(ModBlocks.VOIDROOT, new Item.Settings()));
    public static final Item VOID_BOTTLE = registerItem("void_bottle", new Item(new Item.Settings().maxCount(16)));
    public static final Item CHALK_DUST = registerItem("chalk_dust", new Item(new Item.Settings()));
    public static final Item LUMEN_SIGN_BLUE = registerItem("lumen_sign_blue", new SignItem(new Item.Settings().maxCount(16),ModBlocks.LUMEN_SIGN_BLUE,ModBlocks.LUMEN_WALL_SIGN_BLUE));
    public static final Item LUMEN_HANGING_SIGN_BLUE = registerItem("lumen_hanging_sign_blue", new HangingSignItem(ModBlocks.LUMEN_HANGING_SIGN_BLUE,ModBlocks.LUMEN_WALL_HANGING_SIGN_BLUE,new Item.Settings().maxCount(16)));

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
