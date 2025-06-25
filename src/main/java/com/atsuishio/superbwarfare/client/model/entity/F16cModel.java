package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.F16cEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class F16cModel extends GeoModel<F16cEntity> {

    @Override
    public ResourceLocation getAnimationResource(F16cEntity entity) {
        return null;
//        return ModUtils.loc("animations/wheel_chair.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(F16cEntity entity) {
        return Mod.loc("geo/f16c.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(F16cEntity entity) {
        return Mod.loc("textures/entity/f16c.png");
    }
}
