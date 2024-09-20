package com.lotsofducks.voidbreak;

import com.lotsofducks.voidbreak.block.ModBlocks;
import com.lotsofducks.voidbreak.entity.custom.WhiteUrchinModel;
import com.lotsofducks.voidbreak.entity.custom.WhiteUrchinRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.registry.sync.trackers.vanilla.BlockInitTracker;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VoidbreakClient implements ClientModInitializer {
    public static final EntityModelLayer WHITE_URCHIN_LAYER = new EntityModelLayer(Identifier.of(Voidbreak.MOD_ID, "white_urchin"), "main");
    @Override
    //Blocks
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.CHALKY_BRUSH, ModBlocks.VOIDROOT,
                ModBlocks.LUMEN_DOOR_BLUE, ModBlocks.LUMEN_SAPLING_BLUE, ModBlocks.LUMEN_LEAVES_BLUE, ModBlocks.LUMEN_TRAPDOOR_BLUE,
                ModBlocks.LUMEI_STEM, ModBlocks.LUMEI_FRUIT);
        EntityRendererRegistry.register(Voidbreak.WHITE_URCHIN, WhiteUrchinRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(WHITE_URCHIN_LAYER, WhiteUrchinModel::getTexturedModelData);
    }
}
