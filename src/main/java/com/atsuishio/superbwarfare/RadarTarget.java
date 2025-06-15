package com.atsuishio.superbwarfare;

import net.minecraft.world.entity.Entity;
import java.util.UUID;

public class RadarTarget {
    private final UUID entityUUID;
    private int trackNumber;

    public RadarTarget(Entity entity, int trackNumber) {
        this.entityUUID = entity.getUUID();
        this.trackNumber = trackNumber;
    }

    public UUID getUUID() {
        return entityUUID;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
}
