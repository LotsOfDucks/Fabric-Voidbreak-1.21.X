package com.lotsofducks.voidbreak.entity.custom;

import com.lotsofducks.voidbreak.Voidbreak;
import com.lotsofducks.voidbreak.VoidbreakClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WhiteUrchinRenderer extends MobEntityRenderer<WhiteUrchin, WhiteUrchinModel> {

    private static final float BABY_SHADOW_RADIUS_SCALE = 0.5F;


    protected float getShadowRadius(WhiteUrchin whiteUrchin) {
        float f = super.getShadowRadius(whiteUrchin);
        return whiteUrchin.isBaby() ? f * BABY_SHADOW_RADIUS_SCALE : f;
    }

    protected void scale(WhiteUrchin whiteUrchin, MatrixStack matrixStack, float f) {
        float g = whiteUrchin.getScaleFactor();
        matrixStack.scale(g, g, g);
    }

    public WhiteUrchinRenderer(EntityRendererFactory.Context context) {
        super(context, new WhiteUrchinModel(context.getPart(VoidbreakClient.WHITE_URCHIN_LAYER)), 1f);
    }

    @Override
    public Identifier getTexture(WhiteUrchin entity) {
        return Identifier.of(Voidbreak.MOD_ID, "textures/entity/white_urchin.png");
    }
}
