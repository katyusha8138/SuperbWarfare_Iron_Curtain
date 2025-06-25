package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.entity.ArtilleryEntity;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.tools.SoundTool;
import com.atsuishio.superbwarfare.tools.TraceTool;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static com.atsuishio.superbwarfare.entity.ArtilleryEntity.PITCH;

public class AdjustArtilleryAngleMessage {

    private final double scroll;

    public AdjustArtilleryAngleMessage(double scroll) {
        this.scroll = scroll;
    }

    public static void encode(AdjustArtilleryAngleMessage message, FriendlyByteBuf byteBuf) {
        byteBuf.writeDouble(message.scroll);
    }

    public static AdjustArtilleryAngleMessage decode(FriendlyByteBuf byteBuf) {
        return new AdjustArtilleryAngleMessage(byteBuf.readDouble());
    }

    public static void handler(AdjustArtilleryAngleMessage message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player == null) {
                return;
            }

            Entity looking = TraceTool.findLookingEntity(player, 6);
            if (looking == null) return;

            if (looking instanceof ArtilleryEntity artilleryEntity) {
                artilleryEntity.getEntityData().set(PITCH, (float) Mth.clamp(artilleryEntity.getEntityData().get(PITCH) + 0.5 * message.scroll, -30, -10));
            }

            SoundTool.playLocalSound(player, ModSounds.ADJUST_FOV.get(), 1f, 0.7f);
        });
        context.get().setPacketHandled(true);
    }
}
