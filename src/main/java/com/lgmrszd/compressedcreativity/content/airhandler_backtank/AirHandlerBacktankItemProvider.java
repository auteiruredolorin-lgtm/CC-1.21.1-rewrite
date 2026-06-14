package com.lgmrszd.compressedcreativity.content.airhandler_backtank;

import me.desht.pneumaticcraft.api.tileentity.IAirHandlerItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class AirHandlerBacktankItemProvider implements ICapabilityProvider<ItemStack, Void, IAirHandlerItem> {

    private final AirHandlerBacktankItem handler;

    public AirHandlerBacktankItemProvider(ItemStack stack) {
        this.handler = new AirHandlerBacktankItem(stack);
    }

    @Override
    public @Nullable IAirHandlerItem getCapability(ItemStack stack, @Nullable Void ctx) {
        return handler;
    }
}
