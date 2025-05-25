package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.M4a3e8Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class M4a3e8Model extends GeoModel<M4a3e8Entity> {

    @Override
    public ResourceLocation getAnimationResource(M4a3e8Entity entity) {
        return Mod.loc("animations/m4a3e8.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(M4a3e8Entity entity) {
        return Mod.loc("geo/m4a3e8.geo.json");
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
    public ResourceLocation getTextureResource(M4a3e8Entity entity) {
        return Mod.loc("textures/entity/m4a3e8.png");
    }
}
