package com.atsuishio.superbwarfare.client.renderer.entity;

import com.atsuishio.superbwarfare.client.model.entity.M113Model;
import com.atsuishio.superbwarfare.entity.vehicle.M113Entity;
import com.atsuishio.superbwarfare.event.ClientEventHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class M113Renderer extends GeoEntityRenderer<M113Entity> {

    public M113Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new M113Model());
    }

    @Override
    public RenderType getRenderType(M113Entity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, M113Entity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
                          float blue, float alpha) {
        float scale = 1f;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void render(M113Entity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.prevRoll, entityIn.getRoll())));
        super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
        poseStack.popPose();
    }

    @Override
    public void renderRecursively(PoseStack poseStack, M113Entity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        String name = bone.getName();
        for (int i = 0; i < 9; i++) {
            if (name.equals("wheel_left" + i)) {
                bone.setRotX(1.5f * Mth.lerp(partialTick, animatable.leftWheelRotO, animatable.getLeftWheelRot()));
            }
            if (name.equals("wheel_right" + i)) {
                bone.setRotX(1.5f * Mth.lerp(partialTick, animatable.rightWheelRotO, animatable.getRightWheelRot()));
            }
        }
        if (name.equals("turret")) {
            bone.setRotY(Mth.lerp(partialTick, animatable.turretYRotO, animatable.getTurretYRot()) * Mth.DEG_TO_RAD);
        }

        if (name.equals("barrel")) {
            float a = animatable.getTurretYaw(partialTick);
            float r = (Mth.abs(a) - 90f) / 90f;

            float r2;

            if (Mth.abs(a) <= 90f) {
                r2 = a / 90f;
            } else {
                if (a < 0) {
                    r2 = - (180f + a) / 90f;
                } else {
                    r2 = (180f - a) / 90f;
                }
            }

            bone.setRotX(
                    - Mth.lerp(partialTick, animatable.turretXRotO, animatable.getTurretXRot()) * Mth.DEG_TO_RAD
                            - r * animatable.getPitch(partialTick) * Mth.DEG_TO_RAD
                            - r2 * animatable.getRoll(partialTick) * Mth.DEG_TO_RAD
            );
        }
        if (name.equals("root")) {
            Player player = Minecraft.getInstance().player;
            bone.setHidden(ClientEventHandler.zoomVehicle && animatable.getFirstPassenger() == player);
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public float getBoneMoveY(float t) {
        if (t <= 35.1667) return 0F;
        if (t <= 36.1667) return Mth.lerp(t - 35.1667F, 0F, -2.91F);
        if (t <= 37) return Mth.lerp((t - 36.1667F) / (37F - 36.1667F), -2.91F, -6.79F);
        if (t <= 37.8333) return Mth.lerp((t - 37F) / (37.8333F - 37F), -6.79F, -10.005F);
        if (t <= 42.1667) return Mth.lerp((t - 37.8333F) / (42.1667F - 37.8333F), -10.005F, -22.38F);
        if (t <= 43.4167) return Mth.lerp((t - 42.1667F) / (43.4167F - 42.1667F), -22.38F, -24.14F);
        if (t <= 68.25) return -24.14F;
        if (t <= 69.5) return Mth.lerp((t - 68.25F) / (69.5F - 68.25F), -24.14F, -22.45F);
        if (t <= 73.8333) return Mth.lerp((t - 69.5F) / (73.8333F - 69.5F), -22.45F, -11.12F);
        if (t <= 75.9167) return Mth.lerp((t - 73.8333F) / (75.9167F - 73.8333F), -11.12F, -4.155F);
        if (t <= 76.9167) return Mth.lerp(t - 75.9167F, -4.155F, -0.855F);
        if (t <= 78.0833) return Mth.lerp((t - 76.9167F) / (78.0833F - 76.9167F), -0.855F, 0F);

        return Mth.lerp((t - 79.25F) / (80F - 79.25F), -0.025F, 0F);
    }

    public float getBoneMoveZ(float t) {
        if (t <= 35.1667) return Mth.lerp(t / (35.1667F - 0F), 0F, 121.385F);
        if (t <= 36.1667) return Mth.lerp(t - 35.1667F, 121.385F, 124.37F);
        if (t <= 37) return 124.37F;
        if (t <= 37.8333) return Mth.lerp((t - 37F) / (37.8333F - 37F), 124.37F, 122.73F);
        if (t <= 42.1667) return Mth.lerp((t - 37.8333F) / (42.1667F - 37.8333F), 122.73F, 110.455F);
        if (t <= 43.4167) return Mth.lerp((t - 42.1667F) / (43.4167F - 42.1667F), 110.455F, 105.805F);
        if (t <= 68.25) return Mth.lerp((t - 43.4167F) / (68.25F - 43.4167F), 105.805F, 10.09F);
        if (t <= 69.5) return Mth.lerp((t - 68.25F) / (69.5F - 68.25F), 10.09F, 5.625F);
        if (t <= 73.8333) return Mth.lerp((t - 69.5F) / (73.8333F - 69.5F), 5.625F, -8.025F);
        if (t <= 75.9167) return Mth.lerp((t - 73.8333F) / (75.9167F - 73.8333F), -8.025F, -11.175F);
        if (t <= 76.9167) return Mth.lerp(t - 75.9167F, -11.175F, -9.35F);
        if (t <= 78.0833) return Mth.lerp((t - 76.9167F) / (78.0833F - 76.9167F), -9.35F, -5.38F);

        return Mth.lerp((t - 79.25F) / (80F - 79.25F), -4.12F, 0F);
    }
}
