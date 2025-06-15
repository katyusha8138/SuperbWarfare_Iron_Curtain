package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.M2Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class M2Model extends GeoModel<M2Entity> {

    @Override
    public ResourceLocation getAnimationResource(M2Entity entity) {
        return Mod.loc("animations/m2.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(M2Entity entity) {
        return Mod.loc("geo/m2.geo.json");
//        Player player = Minecraft.getInstance().player;
//
//        int distance = 0;
//
//        if (player != null) {
//            distance = (int) player.position().distanceTo(entity.position());
//        }
//
//        if (distance < 32) {
//            return ModUtils.loc("geo/Bmp2.geo.json");
//        } else {
//            return ModUtils.loc("geo/speedboat.lod1.geo.json");
//        }
    }

    @Override
    public ResourceLocation getTextureResource(M2Entity entity) {
        return Mod.loc("textures/entity/m2.png");
    }
}
