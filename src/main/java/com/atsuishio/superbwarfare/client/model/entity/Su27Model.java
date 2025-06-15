package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.Su27Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Su27Model extends GeoModel<Su27Entity> {

    @Override
    public ResourceLocation getAnimationResource(Su27Entity entity) {
        return null;
//        return ModUtils.loc("animations/wheel_chair.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(Su27Entity entity) {
        return Mod.loc("geo/a10.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Su27Entity entity) {
        return Mod.loc("textures/entity/a10.png");
    }
}
