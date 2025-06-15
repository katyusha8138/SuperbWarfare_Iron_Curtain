package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.T54Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class T54Model extends GeoModel<T54Entity> {

    @Override
    public ResourceLocation getAnimationResource(T54Entity entity) {
        return Mod.loc("animations/t54.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T54Entity entity) {
        return Mod.loc("geo/t54.geo.json");
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
    public ResourceLocation getTextureResource(T54Entity entity) {
        return Mod.loc("textures/entity/t54.png");
    }
}
