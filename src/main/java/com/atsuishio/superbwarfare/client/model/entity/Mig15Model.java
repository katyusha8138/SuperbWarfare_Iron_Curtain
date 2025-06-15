package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.Mig15Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Mig15Model extends GeoModel<Mig15Entity> {

    @Override
    public ResourceLocation getAnimationResource(Mig15Entity entity) {
        return null;
//        return ModUtils.loc("animations/wheel_chair.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(Mig15Entity entity) {
        return Mod.loc("geo/mig15.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mig15Entity entity) {
        return Mod.loc("textures/entity/mig15.png");
    }
}
