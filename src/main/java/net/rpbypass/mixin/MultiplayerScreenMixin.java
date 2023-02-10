package net.rpbypass.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.rpbypass.SharedVariables.*;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin extends Screen {
    protected MultiplayerScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "init")
    public void init(CallbackInfo ci) {
        ButtonWidget resourcePackBypassButton = ButtonWidget.builder(Text.of("RP Bypass: " + (resourcePackBypass ? "ON" : "OFF")), (button) -> {
            resourcePackBypass = !resourcePackBypass;
            button.setMessage(Text.of("RP Bypass: " + (resourcePackBypass ? "ON" : "OFF")));
        }).position(width - 165, height - 55).build();

        ButtonWidget forceDenyButton = ButtonWidget.builder(Text.of("Force Deny: " + (forceDeny ? "ON" : "OFF")), (button) -> {
            forceDeny = !forceDeny;
            button.setMessage(Text.of("Force Deny: " + (forceDeny ? "ON" : "OFF")));
        }).position(width - 165, height - 25).build();

        this.addDrawableChild(resourcePackBypassButton);
        this.addDrawableChild(forceDenyButton);
    }
}
