package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.T80bvEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class T80bvModel extends GeoModel<T80bvEntity> {

    @Override
    public ResourceLocation getAnimationResource(T80bvEntity entity) {
        return Mod.loc("animations/t80bv.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T80bvEntity entity) {
        return Mod.loc("geo/t80bv.geo.json");
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
    public ResourceLocation getTextureResource(T80bvEntity entity) {
        return Mod.loc("textures/entity/t80bv.png");
    }
}
