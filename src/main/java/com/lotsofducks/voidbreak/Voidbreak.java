package com.lotsofducks.voidbreak;

import com.lotsofducks.voidbreak.block.ModBlockSetTypes;
import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.block.ModTags;
import com.lotsofducks.voidbreak.block.ModWoodTypes;
import com.lotsofducks.voidbreak.entity.custom.WhiteUrchin;
import com.lotsofducks.voidbreak.item.ModItemGroups;
import com.lotsofducks.voidbreak.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Voidbreak implements ModInitializer {
	public static final String MOD_ID = "voidbreak";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final EntityType<WhiteUrchin> WHITE_URCHIN = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(Voidbreak.MOD_ID, "white_urchin"),
			EntityType.Builder.create(WhiteUrchin::new, SpawnGroup.CREATURE).dimensions(1.0f, 0.85f).build("white_urchin")
	);

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(WHITE_URCHIN, WhiteUrchin.createUrchinAttributes());
		ModItemGroups.registeritemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockSetTypes.registerModBlockSetTypes();
		ModWoodTypes.registerModWoodTypes();
		ModTags.registerModTags();
	}
}