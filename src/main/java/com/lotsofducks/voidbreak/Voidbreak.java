package com.lotsofducks.voidbreak;

import com.lotsofducks.voidbreak.block.ModBlockSetTypes;
import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.block.ModTags;
import com.lotsofducks.voidbreak.block.ModWoodTypes;
import com.lotsofducks.voidbreak.item.ModItemGroups;
import com.lotsofducks.voidbreak.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Voidbreak implements ModInitializer {
	public static final String MOD_ID = "voidbreak";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registeritemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockSetTypes.registerModBlockSetTypes();
		ModWoodTypes.registerModWoodTypes();
		ModTags.registerModTags();
	}
}