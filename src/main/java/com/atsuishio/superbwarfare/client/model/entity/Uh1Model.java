package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.Uh1Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Uh1Model extends GeoModel<Uh1Entity> {

    @Override
    public ResourceLocation getAnimationResource(Uh1Entity entity) {
        return null;
//        return ModUtils.loc("animations/wheel_chair.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(Uh1Entity entity) {
        return Mod.loc("geo/uh_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Uh1Entity entity) {
        return Mod.loc("textures/entity/uh_1.png");
    }
}
