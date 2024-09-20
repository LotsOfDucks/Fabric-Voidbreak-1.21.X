package com.lotsofducks.voidbreak.entity.custom;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.VoidbreakClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class WhiteUrchinRenderer extends MobEntityRenderer<WhiteUrchin, WhiteUrchinModel> {

    public WhiteUrchinRenderer(EntityRendererFactory.Context context) {
        super(context, new WhiteUrchinModel(context.getPart(VoidbreakClient.WHITE_URCHIN_LAYER)), 1f);
    }

    @Override
    public Identifier getTexture(WhiteUrchin entity) {
        return Identifier.of(Voidbreak.MOD_ID, "textures/entity/white_urchin.png");
    }
}
