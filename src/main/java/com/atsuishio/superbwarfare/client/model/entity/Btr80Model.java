package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.Btr80Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Btr80Model extends GeoModel<Btr80Entity> {

    @Override
    public ResourceLocation getAnimationResource(Btr80Entity entity) {
        return Mod.loc("animations/btr80.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(Btr80Entity entity) {
        return Mod.loc("geo/btr80.geo.json");
//        Player player = Minecraft.getInstance().player;
//
//        int distance = 0;
//
//        if (player != null) {
//            distance = (int) player.position().distanceTo(entity.position());
//        }
//
//        if (distance < 32) {
//            return ModUtils.loc("geo/lav150.geo.json");
//        } else {
//            return ModUtils.loc("geo/speedboat.lod1.geo.json");
//        }
    }

    @Override
    public ResourceLocation getTextureResource(Btr80Entity entity) {
        return Mod.loc("textures/entity/btr80.png");
    }
}
