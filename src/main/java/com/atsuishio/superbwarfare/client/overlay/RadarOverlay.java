package com.atsuishio.superbwarfare.client.overlay;

import com.atsuishio.superbwarfare.AirRadarSystem;
import com.atsuishio.superbwarfare.RadarTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;

public class RadarOverlay {
    private static final int RADIUS = 60;
    private static final ResourceLocation DOT_TEXTURE = new ResourceLocation("modid", "textures/gui/dot.png");

    public static void renderRadar(GuiGraphics graphics, AirRadarSystem radar) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        int cx = 80;
        int cy = 80;
        double scale = RADIUS / 1000.0;

        Vec3 radarPos = mc.player.position();
        Vec3 lookVec = mc.player.getLookAngle();

        Collection<RadarTarget> targets = radar.getTrackedTargets();
        RadarTarget selected = radar.getSelectedTarget();

        for (RadarTarget target : targets) {
            Entity e = mc.level.getEntity(target.getUUID());
            if (e == null) continue;

            Vec3 relative = e.position().subtract(radarPos);
            double distance = relative.length();
            double angle = Math.atan2(relative.z, relative.x) - Math.atan2(lookVec.z, lookVec.x);

            int px = cx + (int)(Math.cos(angle) * distance * scale);
            int py = cy + (int)(Math.sin(angle) * distance * scale);

            boolean isSelected = selected != null && selected.getUUID().equals(target.getUUID());

            if (isSelected) {
                graphics.fill(px - 3, py - 3, px + 5, py + 5, 0xFFFF0000); // 赤
                graphics.drawString(mc.font, "T" + target.getTrackNumber(), px + 8, py - 6, 0xFFFFFF, false); // 白文字
            } else {
                graphics.fill(px, py, px + 3, py + 3, 0xFF00FF00); // 緑
                graphics.drawString(mc.font, String.valueOf(target.getTrackNumber()), px + 5, py - 5, 0x00FF00, false); // 緑文字
            }
        }
