package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.T62Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class T62Model extends GeoModel<T62Entity> {

    @Override
    public ResourceLocation getAnimationResource(T62Entity entity) {
        return Mod.loc("animations/t62.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T62Entity entity) {
        return Mod.loc("geo/t62.geo.json");
//        Player player = Minecraft.getInstance().player;
//
//        int distance = 0;
//
//        if (player != null) {
//            distance = (int) player.position().distanceTo(entity.position());
//        }
//
//        if (distance < 32) {
//            return ModUtils.loc("geo/Yx100.geo.json");
//        } else {
//            return ModUtils.loc("geo/speedboat.lod1.geo.json");
//        }
    }

    @Override
    public ResourceLocation getTextureResource(T62Entity entity) {
        return Mod.loc("textures/entity/t62.png");
    }
}
