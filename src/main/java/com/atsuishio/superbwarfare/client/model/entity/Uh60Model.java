package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.Uh60Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Uh60Model extends GeoModel<Uh60Entity> {

    @Override
    public ResourceLocation getAnimationResource(Uh60Entity entity) {
        return null;
//        return ModUtils.loc("animations/wheel_chair.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(Uh60Entity entity) {
        return Mod.loc("geo/uh60.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Uh60Entity entity) {
        return Mod.loc("textures/entity/uh60.png");
    }
}
