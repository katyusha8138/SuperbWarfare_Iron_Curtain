package com.atsuishio.superbwarfare;

import com.atsuishio.superbwarfare.entity.vehicle.F16aEntity;
import com.atsuishio.superbwarfare.entity.vehicle.F16cEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import com.atsuishio.superbwarfare.init.ModSounds;


import java.util.*;

public class AirRadarSystem {
    private final int maxRange = 1000;
    private final double fov = Math.toRadians(60); // ±60°
    private final int scanInterval = 20; // ticks

    private final Set<Class<? extends Entity>> targetTypes = new HashSet<>();
    private final Map<UUID, RadarTarget> trackedEntities = new HashMap<>();
    private int nextTrackNumber = 1;
    private UUID selectedTarget = null;

    private int tickCounter = 0;

    public AirRadarSystem() {}

    public void registerTargetType(Class<? extends Entity> type) {
        targetTypes.add(type);
    }

    public void update(Level level, Vec3 radarPos, Vec3 lookVec, Player player) {
        if (++tickCounter % scanInterval != 0) return;

        List<Entity> found = new ArrayList<>();
        for (Entity entity : level.getEntities(player, player.getBoundingBox().inflate(maxRange))) {
            if (!targetTypes.contains(entity.getClass())) continue;
            Vec3 dir = entity.position().subtract(radarPos).normalize();
            double angle = Math.acos(lookVec.dot(dir));
            double dist = radarPos.distanceTo(entity.position());
            if (angle <= fov && dist <= maxRange) {
                found.add(entity);
            }
        }

        // update
        Map<UUID, RadarTarget> newTracked = new HashMap<>();
        for (Entity entity : found) {
            RadarTarget old = trackedEntities.get(entity.getUUID());
            if (old != null) {
                newTracked.put(entity.getUUID(), old);
            } else {
                RadarTarget rt = new RadarTarget(entity, nextTrackNumber++);
                newTracked.put(entity.getUUID(), rt);
            }
        }

        trackedEntities.clear();
        trackedEntities.putAll(newTracked);

        if (selectedTarget == null || !trackedEntities.containsKey(selectedTarget)) {
            if (!trackedEntities.isEmpty()) {
                selectedTarget = trackedEntities.values().iterator().next().getUUID();
            }
        }
    }

    private void playLockSound(Player player) {
        if (player.level().isClientSide) {
            player.playSound(ModSounds.RADAR_LOCK.get(), 1.0f, 1.0f);
        }
    }

    public void selectNextTarget() {
        List<UUID> keys = new ArrayList<>(trackedEntities.keySet());
        if (keys.isEmpty()) return;

        int idx = selectedTarget != null ? keys.indexOf(selectedTarget) : -1;
        idx = (idx + 1) % keys.size();
        selectedTarget = keys.get(idx);
                playLockSound(Player);
        }

    public void selectPreviousTarget() {
        List<UUID> keys = new ArrayList<>(trackedEntities.keySet());
        if (keys.isEmpty()) return;

        int idx = selectedTarget != null ? keys.indexOf(selectedTarget) : 0;
        idx = (idx - 1 + keys.size()) % keys.size();
        selectedTarget = keys.get(idx);
        playLockSound(Player);
    }

    public Collection<RadarTarget> getTrackedTargets() {
        return trackedEntities.values();
    }

    public RadarTarget getSelectedTarget() {
        return selectedTarget != null ? trackedEntities.get(selectedTarget) : null;
    }

    public boolean isRadarLocked(UUID entityUUID) {
        return selectedTarget != null && selectedTarget.equals(entityUUID);
    }
}
