package com.lgmrszd.compressedcreativity.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ForceUpdatePacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ForceUpdatePacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("compressedcreativity", "force_update_packet"));

    public static final StreamCodec<FriendlyByteBuf, ForceUpdatePacket> STREAM_CODEC =
            StreamCodec.of(ForceUpdatePacket::encode, ForceUpdatePacket::decode);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private final BlockPos pos;

    public ForceUpdatePacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void encode(FriendlyByteBuf buf, ForceUpdatePacket packet) {
        buf.writeBlockPos(packet.pos);
    }

    public static ForceUpdatePacket decode(FriendlyByteBuf buf) {
        return new ForceUpdatePacket(buf.readBlockPos());
    }

    public static void handle(ForceUpdatePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> handlePacket(packet));
    }

    private static void handlePacket(ForceUpdatePacket packet) {
        BlockPos pos = packet.pos;
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null || !level.isLoaded(pos)) return;
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof IUpdateBlockEntity ube)) return;
        ube.forceUpdate();
    }

    public static void send(Level world, BlockPos pos) {
        if (!(world instanceof ServerLevel serverLevel)) return;
        PacketDistributor.sendToPlayersNear(
                serverLevel,
                null,
                pos.getX(), pos.getY(), pos.getZ(),
                16,
                new ForceUpdatePacket(pos)
        );
    }
}
