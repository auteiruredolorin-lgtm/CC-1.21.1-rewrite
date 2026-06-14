package com.lgmrszd.compressedcreativity.network;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/**
 * [forge2neo] Replaced SimpleChannel registration class.
 * Packet registration is now handled by ModNetwork via RegisterPayloadHandlersEvent.
 * Helper methods preserved for compatibility.
 */
public class CCNetwork {

    // Registration is now handled by ModNetwork.register()
    public static void registerPackets() {
        // No-op: packets are registered via RegisterPayloadHandlersEvent in ModNetwork
    }

    public static void register() {
        registerPackets();
    }

}
