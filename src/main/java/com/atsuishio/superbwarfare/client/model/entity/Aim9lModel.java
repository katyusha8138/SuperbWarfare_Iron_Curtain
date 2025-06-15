package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.projectile.Aim9lEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Aim9lModel extends GeoModel<Aim9lEntity> {

    @Override
    public ResourceLocation getAnimationResource(Aim9lEntity entity) {
        return Mod.loc("animations/javelin_missile.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(Aim9lEntity entity) {
        return Mod.loc("geo/aim9l.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Aim9lEntity entity) {
        return Mod.loc("textures/entity/aim9l.png");
    }
}
