package com.atsuishio.superbwarfare.client.model.entity;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.ArtilleryEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ArtilleryModel extends GeoModel<ArtilleryEntity> {

    @Override
    public ResourceLocation getAnimationResource(ArtilleryEntity entity) {
        return Mod.loc("animations/artillery.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(ArtilleryEntity entity) {
        return Mod.loc("geo/artillery.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArtilleryEntity entity) {
        return Mod.loc("textures/entity/artillery.png");
    }

    @Override
    public void setCustomAnimations(ArtilleryEntity animatable, long instanceId, AnimationState<ArtilleryEntity> animationState) {
        CoreGeoBone barrel = getAnimationProcessor().getBone("barrel");
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            barrel.setRotX(((entityData.headPitch() - (10 - entityData.headPitch() * 0.1f)) * Mth.DEG_TO_RAD));
    }
}
