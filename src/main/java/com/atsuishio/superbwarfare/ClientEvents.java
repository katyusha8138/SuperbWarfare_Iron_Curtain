package com.atsuishio.superbwarfare;

import com.atsuishio.superbwarfare.AirRadarSystem;
import com.atsuishio.superbwarfare.entity.vehicle.F16cEntity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "modid", value = LogicalSide.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.player.getVehicle() == null) return;
        if (!(mc.player.getVehicle() instanceof F16cEntity plane)) return;

        AirRadarSystem radar = plane.getRadar();

        if (KeyBindings.RADAR_NEXT_TARGET.consumeClick()) {
            radar.selectNextTarget();
        } else if (KeyBindings.RADAR_PREV_TARGET.consumeClick()) {
            radar.selectPreviousTarget();
        }
    }
}
