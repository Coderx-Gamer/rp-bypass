package net.rpbypass.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.play.ResourcePackStatusC2SPacket;
import net.minecraft.network.packet.s2c.play.ResourcePackSendS2CPacket;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.rpbypass.Util.resourcePackBypassEnabled;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    @Inject(at = @At("HEAD"), method = "onResourcePackSend", cancellable = true)
    public void onResourcePackSend(ResourcePackSendS2CPacket packet, CallbackInfo ci) {
        if (resourcePackBypassEnabled && packet.isRequired()) {
            CLIENT.getNetworkHandler().sendPacket(new ResourcePackStatusC2SPacket(ResourcePackStatusC2SPacket.Status.ACCEPTED));
            CLIENT.player.sendMessage(new LiteralText("[RP Bypass]: This server has a required resource pack."), false);
            ci.cancel();
        }
    }
}
