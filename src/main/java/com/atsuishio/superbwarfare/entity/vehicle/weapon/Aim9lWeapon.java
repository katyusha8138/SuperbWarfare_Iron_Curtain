package com.atsuishio.superbwarfare.entity.vehicle.weapon;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.projectile.Aim9lEntity;
import net.minecraft.world.entity.LivingEntity;

public class Aim9lWeapon extends VehicleWeapon {
    public Aim9lWeapon() {
        this.icon = Mod.loc("textures/screens/vehicle_weapon/agm_65.png");
    }

    public Aim9lEntity create(LivingEntity entity) {
        return new Aim9lEntity(entity, entity.level());
    }
}
