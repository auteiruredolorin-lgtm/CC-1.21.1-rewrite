package com.lgmrszd.compressedcreativity.mixin.create;

import com.lgmrszd.compressedcreativity.content.airhandler_backtank.AirHandlerBacktankBlockEntity;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import me.desht.pneumaticcraft.api.PNCCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BacktankBlockEntity.class)
public abstract class BacktankBlockEntityMixin extends BlockEntity {

    @Unique
    private AirHandlerBacktankBlockEntity internalAirHandler = null;

    @Shadow(remap = false)
    private int capacityEnchantLevel;

    public BacktankBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "tick", at = @At("HEAD"), remap = false)
    public void inTick(CallbackInfo ci) {
        if (internalAirHandler == null) {
            // NeoForge 1.21.1: use BlockCapability lookup via level
            if (getLevel() != null) {
                PNCCapabilities.getAirHandler(this, null).ifPresent(cap -> {
                    if (cap instanceof AirHandlerBacktankBlockEntity backtankCap) {
                        internalAirHandler = backtankCap;
                        backtankCap.updateVolumeFromEnchant(capacityEnchantLevel);
                        backtankCap.tick(this);
                    }
                });
            }
        } else {
            internalAirHandler.tick(this);
        }
    }
}
