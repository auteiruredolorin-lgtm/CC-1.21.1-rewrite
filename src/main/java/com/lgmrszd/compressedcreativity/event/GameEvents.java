package com.lgmrszd.compressedcreativity.event;

import com.lgmrszd.compressedcreativity.network.ObservePacket;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class GameEvents {
    @SubscribeEvent
    public static void clientTickEvent(ClientTickEvent.Post evt) {
        // ClientTickEvent.Post fires once per tick (end of tick), no phase check needed
        ObservePacket.tick();
    }
}
