package net.rpbypass.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
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
        ButtonWidget resourcePackBypassButton = new ButtonWidget(535, 310, 100, 20, new LiteralText("RP Bypass: "), (button) -> {
            if (!resourcePackBypass) {
                resourcePackBypass = true;
                button.setMessage(new LiteralText("RP Bypass: ON"));
            } else {
                resourcePackBypass = false;
                button.setMessage(new LiteralText("RP Bypass: OFF"));
            }
        });

        ButtonWidget forceDenyButton = new ButtonWidget(535, 285, 100, 20, new LiteralText("Force Deny: "), (button) -> {
            if (!forceDeny) {
                forceDeny = true;
                button.setMessage(new LiteralText("Force Deny: ON"));
            } else {
                forceDeny = false;
                button.setMessage(new LiteralText("Force Deny: OFF"));
            }
        });

        if (!resourcePackBypass) {
            resourcePackBypassButton.setMessage(new LiteralText(resourcePackBypassButton.getMessage().asString() + "OFF"));
        } else {
            resourcePackBypassButton.setMessage(new LiteralText(resourcePackBypassButton.getMessage().asString() + "ON"));
        }

        if (!forceDeny) {
            forceDenyButton.setMessage(new LiteralText(forceDenyButton.getMessage().asString() + "OFF"));
        } else {
            forceDenyButton.setMessage(new LiteralText(forceDenyButton.getMessage().asString() + "ON"));
        }

        this.addDrawableChild(resourcePackBypassButton);
        this.addDrawableChild(forceDenyButton);
    }
}
