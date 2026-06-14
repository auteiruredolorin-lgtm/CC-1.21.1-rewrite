package com.lgmrszd.compressedcreativity.index;

import com.lgmrszd.compressedcreativity.blocks.air_blower.AirBlowerBlockEntity;
import com.lgmrszd.compressedcreativity.blocks.advanced_air_blower.AdvancedAirBlowerBlockEntity;
import com.lgmrszd.compressedcreativity.blocks.bracketed_pressure_tube.BracketedPressureTubeBlockEntity;
import com.lgmrszd.compressedcreativity.blocks.compressed_air_engine.CompressedAirEngineBlockEntity;
import com.lgmrszd.compressedcreativity.blocks.advanced_air_blower.AdvancedAirBlowerBlockEntity;
import com.lgmrszd.compressedcreativity.blocks.heater.HeaterBlockEntity;
import com.lgmrszd.compressedcreativity.blocks.rotational_compressor.RotationalCompressorBlockEntity;
import me.desht.pneumaticcraft.api.heat.IHeatExchangerLogic;
import me.desht.pneumaticcraft.api.PNCCapabilities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = "compressedcreativity", bus = EventBusSubscriber.Bus.MOD)
public class CCCapabilities {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                PNCCapabilities.AIR_HANDLER_MACHINE,
                CCBlockEntities.AIR_BLOWER.get(),
                (be, side) -> be.canConnectPneumatic(side) ? be.airHandler : null
        );
        event.registerBlockEntity(
                PNCCapabilities.AIR_HANDLER_MACHINE,
                CCBlockEntities.INDUSTRIAL_AIR_BLOWER.get(),
                (be, side) -> be.canConnectPneumatic(side) ? be.airHandler : null
        );
        event.registerBlockEntity(
                PNCCapabilities.AIR_HANDLER_MACHINE,
                CCBlockEntities.ROTATIONAL_COMPRESSOR.get(),
                (be, side) -> be.canConnectPneumatic(side) ? be.airHandler : null
        );
        event.registerBlockEntity(
                PNCCapabilities.AIR_HANDLER_MACHINE,
                CCBlockEntities.COMPRESSED_AIR_ENGINE.get(),
                (be, side) -> be.canConnectPneumatic(side) ? be.airHandler : null
        );
        event.registerBlockEntity(
                PNCCapabilities.AIR_HANDLER_MACHINE,
                CCBlockEntities.BRACKETED_PRESSURE_TUBE.get(),
                (be, side) -> be.canConnectPneumatic(side) ? be.airHandler : null
        );
        event.registerBlockEntity(
                PNCCapabilities.AIR_HANDLER_MACHINE,
                CCBlockEntities.BRACKETED_ADVANCED_PRESSURE_TUBE.get(),
                (be, side) -> be.canConnectPneumatic(side) ? be.airHandler : null
        );
        event.registerBlockEntity(
                PNCCapabilities.AIR_HANDLER_MACHINE,
                CCBlockEntities.BRACKETED_REINFORCED_PRESSURE_TUBE.get(),
                (be, side) -> be.canConnectPneumatic(side) ? be.airHandler : null
        );

        // Heat exchanger capabilities
        event.registerBlockEntity(
                PNCCapabilities.HEAT_EXCHANGER_BLOCK,
                CCBlockEntities.INDUSTRIAL_AIR_BLOWER.get(),
                (be, side) -> be.canConnectPneumatic(side) ? be.heatExchanger : null
        );
        event.registerBlockEntity(
                PNCCapabilities.HEAT_EXCHANGER_BLOCK,
                CCBlockEntities.HEATER.get(),
                (be, side) -> side != net.minecraft.core.Direction.UP ? be.heatExchanger : null
        );
    }
}
