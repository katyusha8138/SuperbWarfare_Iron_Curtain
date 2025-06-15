package com.atsuishio.superbwarfare;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyMapping RADAR_NEXT_TARGET = new KeyMapping(
        "key.superbwarfare.radar_next", GLFW.GLFW_KEY_RIGHT_BRACKET, "key.categories.superbwarfare.radar"
    );

    public static final KeyMapping RADAR_PREV_TARGET = new KeyMapping(
        "key.superbwarfare.radar_prev", GLFW.GLFW_KEY_LEFT_BRACKET, "key.categories.superbwarfare.radar"
    );

    public static void register() {
        net.minecraft.client.Minecraft.getInstance().options.keyMappings = 
            net.minecraft.client.Minecraft.getInstance().options.keyMappings.clone();
    }
}
