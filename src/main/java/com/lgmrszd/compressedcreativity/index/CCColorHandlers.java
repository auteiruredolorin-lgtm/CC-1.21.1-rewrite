package com.lgmrszd.compressedcreativity.index;

import com.lgmrszd.compressedcreativity.blocks.ITintedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import javax.annotation.Nullable;

@EventBusSubscriber(modid = "compressedcreativity", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CCColorHandlers {
    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        // CCBlocks re-enable when Create dep is added:
        // event.register(CCColorHandlers::getTintColor, CCBlocks.INDUSTRIAL_AIR_BLOWER.get());
        // event.register(CCColorHandlers::getTintColor, CCBlocks.HEATER.get());
    }

    public static int getTintColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
        if (world != null && pos != null) {
            BlockEntity te = world.getBlockEntity(pos);
            return te instanceof ITintedBlockEntity ? ((ITintedBlockEntity) te).getTintColor(tintIndex) : 0xFFFFFF;
        } else {
            return -1;
        }
    }
}
