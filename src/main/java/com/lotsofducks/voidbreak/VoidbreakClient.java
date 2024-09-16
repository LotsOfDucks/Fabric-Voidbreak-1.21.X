package com.lotsofducks.voidbreak;

import com.lotsofducks.voidbreak.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.impl.registry.sync.trackers.vanilla.BlockInitTracker;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registry;

public class VoidbreakClient implements ClientModInitializer {
    @Override
    //Blocks
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.CHALKY_BRUSH, ModBlocks.VOIDROOT,
                ModBlocks.LUMEN_DOOR_BLUE, ModBlocks.LUMEN_SAPLING_BLUE, ModBlocks.LUMEN_LEAVES_BLUE, ModBlocks.LUMEN_TRAPDOOR_BLUE);
    }
}
