package com.lgmrszd.compressedcreativity.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModNetwork {

    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("compressedcreativity").versioned("1");

        registrar.playToServer(
                ForceUpdatePacket.TYPE,
                ForceUpdatePacket.STREAM_CODEC,
                ForceUpdatePacket::handle
        );

        registrar.playToServer(
                ObservePacket.TYPE,
                ObservePacket.STREAM_CODEC,
                ObservePacket::handle
        );
    }
}
