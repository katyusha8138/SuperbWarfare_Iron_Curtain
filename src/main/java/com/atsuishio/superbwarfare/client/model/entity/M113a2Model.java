package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.M113a2Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class M113a2Model extends GeoModel<M113a2Entity> {

    @Override
    public ResourceLocation getAnimationResource(M113a2Entity entity) {
        return Mod.loc("animations/m113a2.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(M113a2Entity entity) {
        return Mod.loc("geo/m113a2.geo.json");
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
    public ResourceLocation getTextureResource(M113a2Entity entity) {
        return Mod.loc("textures/entity/m113a2.png");
    }
}
