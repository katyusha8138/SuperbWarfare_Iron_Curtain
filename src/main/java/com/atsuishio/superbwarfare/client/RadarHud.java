package com.atsuishio.superbwarfare.client;

import com.atsuishio.superbwarfare.entity.vehicle.F16aEntity;
import com.atsuishio.superbwarfare.entity.vehicle.F16cEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.AirEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.AircraftEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import com.atsuishio.superbwarfare.Mod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import java.util.*;

public class RadarHud {
    public static List<Vec3> radarTargets = new ArrayList<>();

    private static final ResourceLocation RADAR_BACKGROUND = Mod.loc("textures/gui/radar_bg.png");
    private static final ResourceLocation RADAR_TARGET = Mod.loc("textures/gui/radar_target.png");
    private static final ResourceLocation RADAR_SWEEP = Mod.loc("textures/gui/radar_sweep.png"); // 1x47テクスチャ

    private static final List<String> AIRPLANES = Arrays.asList(
        "F16cEntity"
    );

    // レーダー捜索変数
    private static float sweepAngle = 0.0f;
    private static final float SWEEP_SPEED = 1.3f; // 走査速度
    private static final int TRAIL_LENGTH = 3; // 3つのループ・セグメントのみ

    // 検知継続時間
    private static final Map<Vec3, Long> targetDetectionTime = new HashMap<>();
    private static final long TARGET_VISIBILITY_TIME = 1000;
    private static final long FADE_TIME = 1000;

    public static final IGuiOverlay HUD_RADAR = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || !player.isAlive() || mc.level == null) {
            return;
        }

        Entity vehicle = player.getVehicle();

        if (vehicle != null) {
            System.out.println("[DEBUG] vehicle = " + vehicle);
            System.out.println("[DEBUG] vehicle class = " + vehicle.getClass().getName());
        } else {
            System.out.println("[DEBUG] vehicle = null");
        }

        if (vehicle != null && isRadarAirplane(vehicle)) {
            guiGraphics.drawString(Minecraft.getInstance().font, "Radar Active", 10, 100, 0xFFFFFF);
            int radarSize = 96;
            int radarX = 10;
            int radarY = 10;
            int radarCenterX = radarX + radarSize / 2;
            int radarCenterY = radarY + radarSize / 2;
            // 変更：レーダーの表示範囲を2倍に（150fだった）
            float radarDisplayRange = 300f;

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            // レーダー背景描画
            guiGraphics.blit(RADAR_BACKGROUND, radarX, radarY, 0, 0, radarSize, radarSize, radarSize, radarSize);

            // 走査角の更新
            sweepAngle = (sweepAngle + SWEEP_SPEED * partialTick) % 360;

            // 現在の行列を保存
            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();

            // レーダー画面の中心
            poseStack.translate(radarCenterX, radarCenterY, 0);

            // 走査線を描画（透明度の異なる3つのセグメント）
            for (int i = TRAIL_LENGTH - 1; i >= 0; i--) {
                float trailAngle = sweepAngle - (i * 20); // 各セグメントは20°遅れる
                float alpha = 0.15f - (i * 0.05f); // 透明度の低下：0.15、0.10、0.05

                poseStack.pushPose();
                poseStack.mulPose(Axis.ZP.rotationDegrees(trailAngle));

                RenderSystem.setShaderColor(0.0f, 1.0f, 0.0f, alpha);
                guiGraphics.blit(RADAR_SWEEP, -1, -47, 0, 0, 1, 47, 1, 47);

                poseStack.popPose();
            }

            // 主走査線を描画
            poseStack.pushPose();
            poseStack.mulPose(Axis.ZP.rotationDegrees(sweepAngle));

            RenderSystem.setShaderColor(0.0f, 1.0f, 0.0f, 0.7f);
            guiGraphics.blit(RADAR_SWEEP, -1, -47, 0, 0, 1, 47, 1, 47);

            poseStack.popPose();

            // マトリックスの修復
            poseStack.popPose();

            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

            // 現在時刻
            long currentTime = System.currentTimeMillis();

            // 古いターゲットをクリア
            targetDetectionTime.entrySet().removeIf(entry ->
                currentTime - entry.getValue() > TARGET_VISIBILITY_TIME + FADE_TIME);

            // ターゲットを描画
            for (Vec3 targetPos : radarTargets) {
                Vec3 relativePos = targetPos.subtract(player.position());
                double distance = Math.sqrt(relativePos.x * relativePos.x + relativePos.z * relativePos.z);

                // 注：実際の検出範囲はEntity.RADAR_RANGEに依存
                // たとえこちらで300mが検知距離とされていても、Entity.RADAR_RANGEで150mと定義されていれば実際に表示されない
                if (distance > F16cEntity.RADAR_RANGE) continue;

                float playerYaw = (player.getViewYRot(1.0f) % 360 + 360) % 360;
                double angleToTarget = Math.toDegrees(Math.atan2(relativePos.z, relativePos.x)) - 90;
                double rotatedAngle = Math.toRadians(angleToTarget - playerYaw - 90);

                double displayDist = (distance / radarDisplayRange) * (radarSize / 2.0 - 4);
                displayDist = Math.min(displayDist, radarSize / 2.0 - 4);

                int targetX = radarCenterX + (int) (Math.cos(rotatedAngle) * displayDist);
                int targetY = radarCenterY + (int) (Math.sin(rotatedAngle) * displayDist);

                // リーマーとターゲットの角度をチェックする
                float targetAngle = (float) (Math.toDegrees(rotatedAngle) + 90) % 360;
                float angleDiff = Math.abs(normalizeAngle(targetAngle - sweepAngle));

                // 走査がターゲットを通過した場合、検出時間を参照する
                if (angleDiff < 5 && !targetDetectionTime.containsKey(targetPos)) {
                    targetDetectionTime.put(targetPos, currentTime);
                }

                // ターゲットが検出された場合のみ表示
                if (targetDetectionTime.containsKey(targetPos)) {
                    long detectionTime = targetDetectionTime.get(targetPos);
                    long timeSinceDetection = currentTime - detectionTime;

                    float alpha = 1.0f;

                    if (timeSinceDetection < TARGET_VISIBILITY_TIME) {
                        // ターゲットが完全に検知されている
                        alpha = 1.0f;

                        // 検出後1秒間のリップル効果
                        if (timeSinceDetection < 1000) {
                            float pulse = (float) (Math.sin(timeSinceDetection * 0.01) * 0.3 + 0.7);
                            alpha *= pulse;
                        }
                    } else if (timeSinceDetection < TARGET_VISIBILITY_TIME + FADE_TIME) {
                        // ターゲットが薄れていく
                        alpha = 1.0f - ((float)(timeSinceDetection - TARGET_VISIBILITY_TIME) / FADE_TIME);
                    } else {
                        // ターゲットが見えなくなる
                        continue;
                    }

                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
                    // 変更：ターゲットサイズを1.5倍縮小（4x4から3x3へ）
                    int targetSize = 3;
                    guiGraphics.blit(RADAR_TARGET, targetX - (targetSize / 2), targetY - (targetSize / 2), 0, 0, targetSize, targetSize, targetSize, targetSize);
                }
            }

            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.disableBlend();
        }
    };

    // 角度を0～180°の範囲に正規化
    private static float normalizeAngle(float angle) {
        angle = angle % 360;
        if (angle < 0) angle += 360;
        if (angle > 180) angle = 360 - angle;
        return angle;
    }

    private static boolean isRadarAirplane(Entity vehicle) {
        return vehicle instanceof F16cEntity;
    }
}