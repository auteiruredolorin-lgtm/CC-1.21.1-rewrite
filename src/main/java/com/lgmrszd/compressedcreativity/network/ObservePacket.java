package com.lgmrszd.compressedcreativity.network;

/*
    Code is taken from https://github.com/mrh0/createaddition, made by MRH,
    taken with the permission of the original creator
 */

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ObservePacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ObservePacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("compressedcreativity", "observe_packet"));

    public static final StreamCodec<FriendlyByteBuf, ObservePacket> STREAM_CODEC =
            StreamCodec.of(ObservePacket::encode, ObservePacket::decode);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private final BlockPos pos;
    private final int node;
    private static int cooldown = 0;

    public ObservePacket(BlockPos pos, int node) {
        this.pos = pos;
        this.node = node;
    }

    public static void encode(FriendlyByteBuf buf, ObservePacket packet) {
        buf.writeBlockPos(packet.pos);
        buf.writeInt(packet.node);
    }

    public static ObservePacket decode(FriendlyByteBuf buf) {
        return new ObservePacket(buf.readBlockPos(), buf.readInt());
    }

    public static void handle(ObservePacket pkt, IPayloadContext context) {
        context.enqueueWork(() -> {
            try {
                if (context.player() instanceof ServerPlayer player) {
                    sendUpdate(pkt, player);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // setPacketHandled() removed — automatic in NeoForge 1.21.1
    }

    private static void sendUpdate(ObservePacket pkt, ServerPlayer player) {
        BlockEntity te = player.level().getBlockEntity(pkt.pos);
        if (te instanceof IObserveTileEntity) {
            ((IObserveTileEntity) te).onObserved(player, pkt);
            Packet<ClientGamePacketListener> updatePacket = te.getUpdatePacket();
            if (updatePacket != null) {
                player.connection.send(updatePacket);
            }
        }
    }

    public static void tick() {
        --cooldown;
        if (cooldown < 0) {
            cooldown = 0;
        }
    }

    public static void send(BlockPos pos, int node) {
        if (cooldown <= 0) {
            cooldown = 10;
            PacketDistributor.sendToServer(new ObservePacket(pos, node));
        }
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public int getNode() {
        return this.node;
    }
}
