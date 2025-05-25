package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.M1Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class M1Model extends GeoModel<M1Entity> {

    @Override
    public ResourceLocation getAnimationResource(M1Entity entity) {
        return Mod.loc("animations/m1.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(M1Entity entity) {
        return Mod.loc("geo/m1.geo.json");
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
    public ResourceLocation getTextureResource(M1Entity entity) {
        return Mod.loc("textures/entity/m1.png");
    }
}
