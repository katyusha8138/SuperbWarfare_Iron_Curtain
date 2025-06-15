package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.F16aEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class F16aModel extends GeoModel<F16aEntity> {

    @Override
    public ResourceLocation getAnimationResource(F16aEntity entity) {
        return null;
//        return ModUtils.loc("animations/wheel_chair.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(F16aEntity entity) {
        return Mod.loc("geo/f16a.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(F16aEntity entity) {
        return Mod.loc("textures/entity/f16a.png");
    }
}
