package com.lgmrszd.compressedcreativity.index;

import com.lgmrszd.compressedcreativity.config.ClientConfig;
import me.desht.pneumaticcraft.client.pneumatic_armor.ClientArmorRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;

public class CCConfigHelper {

    public static void init() {
        // FMLJavaModLoadingContext.get() removed in NeoForge — config registration still works via ModLoadingContext
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CLIENT_SPEC);
        // Config change listener is registered by the mod container automatically via ModConfigEvent;
        // wire it up by calling registerConfigListener from the mod constructor where we have the eventBus
    }

    // Called from CompressedCreativity constructor with the mod event bus
    public static void registerConfigListener(IEventBus eventBus) {
        eventBus.addListener(CCConfigHelper::onConfigChanged);
    }

    private static void onConfigChanged(final ModConfigEvent event) {
        ModConfig config = event.getConfig();
        if (config.getSpec() == ClientConfig.CLIENT_SPEC) {
            refreshClient();
        }
    }

    static void refreshClient() {
        ClientArmorRegistry.getInstance().refreshConfig();
    }
}
