package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.M48Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class M48Model extends GeoModel<M48Entity> {

    @Override
    public ResourceLocation getAnimationResource(M48Entity entity) {
        return Mod.loc("animations/m48.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(M48Entity entity) {
        return Mod.loc("geo/m48.geo.json");
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
    public ResourceLocation getTextureResource(M48Entity entity) {
        return Mod.loc("textures/entity/m48.png");
    }
}
