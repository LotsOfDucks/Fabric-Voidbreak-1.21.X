package com.lotsofducks.voidbreak.entity.custom;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class WhiteUrchinModel extends EntityModel<WhiteUrchin> {
    private final ModelPart root;
    private final ModelPart base;
    public WhiteUrchinModel(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");

    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -10.0F, 0.0F, 16.0F, 14.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, -8.0F));

        ModelPartData base1 = base.addChild("base1", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, -2.0F, 2.0F));

        ModelPartData Spine1_r1 = base1.addChild("Spine1_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.6545F, 0.0F));

        ModelPartData base2 = base.addChild("base2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine2_r1 = base2.addChild("Spine2_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, -2.0F, 5.0F, 0.0F, 0.3491F, 0.0F));

        ModelPartData base3 = base.addChild("base3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine3_r1 = base3.addChild("Spine3_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, -2.0F, 14.0F, 0.0F, -0.6545F, 0.0F));

        ModelPartData base4 = base.addChild("base4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine4_r1 = base4.addChild("Spine4_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, -2.0F, 11.0F, 0.0F, -0.1745F, 0.0F));

        ModelPartData base5 = base.addChild("base5", ModelPartBuilder.create().uv(8, 0).cuboid(8.0F, -10.0F, 8.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData base6 = base.addChild("base6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine6_r1 = base6.addChild("Spine6_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -2.0F, 14.0F, 3.1416F, -0.6545F, 3.1416F));

        ModelPartData base7 = base.addChild("base7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine7_r1 = base7.addChild("Spine7_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -2.0F, 11.0F, 3.1416F, -0.1745F, 3.1416F));

        ModelPartData base8 = base.addChild("base8", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine8_r1 = base8.addChild("Spine8_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -2.0F, 2.0F, 3.1416F, 0.6109F, 3.1416F));

        ModelPartData base9 = base.addChild("base9", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine9_r1 = base9.addChild("Spine9_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -2.0F, 5.0F, 3.1416F, 0.3491F, 3.1416F));

        ModelPartData base10 = base.addChild("base10", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine10_r1 = base10.addChild("Spine10_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -2.0F, 8.0F, 3.1416F, 0.0F, 3.1416F));

        ModelPartData base11 = base.addChild("base11", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine11_r1 = base11.addChild("Spine11_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -2.0F, 0.0F, -3.1416F, 0.9163F, 3.1416F));

        ModelPartData base12 = base.addChild("base12", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine12_r1 = base12.addChild("Spine12_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -2.0F, 0.0F, -3.1416F, 1.2217F, 3.1416F));

        ModelPartData base13 = base.addChild("base13", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine13_r1 = base13.addChild("Spine13_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -2.0F, 0.0F, 0.0F, 0.9163F, 0.0F));

        ModelPartData base14 = base.addChild("base14", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine14_r1 = base14.addChild("Spine14_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -2.0F, 0.0F, 0.0F, 1.2217F, 0.0F));

        ModelPartData base15 = base.addChild("base15", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Spine15_r1 = base15.addChild("Spine15_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData base16 = base.addChild("base16", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine16_r1 = base16.addChild("Spine16_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -6.0F, 8.0F, 0.0F, -0.9163F, 0.0F));

        ModelPartData base17 = base.addChild("base17", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine17_r1 = base17.addChild("Spine17_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -6.0F, 8.0F, 0.0F, -1.2217F, 0.0F));

        ModelPartData base18 = base.addChild("base18", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine18_r1 = base18.addChild("Spine18_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -6.0F, 8.0F, -3.1416F, -0.9163F, 3.1416F));

        ModelPartData base19 = base.addChild("base19", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine19_r1 = base19.addChild("Spine19_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -6.0F, 8.0F, -3.1416F, -1.2217F, 3.1416F));

        ModelPartData base20 = base.addChild("base20", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine20_r1 = base20.addChild("Spine20_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, -8.0F, 0.0F, 4.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 8.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData base21 = base.addChild("base21", ModelPartBuilder.create().uv(48, 1).cuboid(-2.0F, -19.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData base22 = base.addChild("base22", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine22_r1 = base22.addChild("Spine22_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -14.0F, 6.0F, 3.1416F, -0.5236F, -1.5708F));

        ModelPartData base23 = base.addChild("base23", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine23_r1 = base23.addChild("Spine23_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -14.0F, 0.0F, -1.5708F, 0.0F, -1.0472F));

        ModelPartData base24 = base.addChild("base24", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine24_r1 = base24.addChild("Spine24_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -14.0F, -6.0F, 3.1416F, 0.5236F, -1.5708F));

        ModelPartData base25 = base.addChild("base25", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

        ModelPartData Spine25_r1 = base25.addChild("Spine25_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -14.0F, 0.0F, -1.5708F, 0.0F, -2.0944F));
        return TexturedModelData.of(modelData, 64, 32);
    }
    @Override
    public void setAngles(WhiteUrchin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertex, int light, int overlay, int color) {
        this.getPart().render(matrices, vertex, light, overlay, color);
    }
    public ModelPart getPart() {
        return this.root;
    }
}