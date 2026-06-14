package com.lgmrszd.compressedcreativity.content.airhandler_backtank;

import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import me.desht.pneumaticcraft.api.PNCCapabilities;
import me.desht.pneumaticcraft.api.tileentity.IAirHandlerMachine;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

import static com.lgmrszd.compressedcreativity.config.CommonConfig.BACKTANK_COMPAT_BLOCK;

public class AirHandlerBacktankBEProvider implements ICapabilityProvider<BacktankBlockEntity, Direction, IAirHandlerMachine> {

    private final AirHandlerBacktankBlockEntity handler;

    public AirHandlerBacktankBEProvider(BacktankBlockEntity bbe) {
        this.handler = new AirHandlerBacktankBlockEntity(bbe);
    }

    @Override
    public @Nullable IAirHandlerMachine getCapability(BacktankBlockEntity be, @Nullable Direction side) {
        if (BACKTANK_COMPAT_BLOCK.get() && (side == null || side == Direction.DOWN)) {
            return handler;
        }
        return null;
    }
}
