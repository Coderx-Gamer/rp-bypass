package net.rpbypass.mixin;

import net.minecraft.client.gui.screen.DirectConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.Option;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.rpbypass.Util.resourcePackBypassEnabled;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin extends Screen {
    protected MultiplayerScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "init")
    public void init(CallbackInfo ci) {
        ButtonWidget resourcePackBypassButton = new ButtonWidget(495, 310, 145, 20, new LiteralText("Resource Pack Bypass: "), (button) -> {
            if (!resourcePackBypassEnabled) {
                resourcePackBypassEnabled = true;
                button.setMessage(new LiteralText("Resource Pack Bypass: ON"));
            } else {
                resourcePackBypassEnabled = false;
                button.setMessage(new LiteralText("Resource Pack Bypass: OFF"));
            }
        });

        if (!resourcePackBypassEnabled) {
            resourcePackBypassButton.setMessage(new LiteralText(resourcePackBypassButton.getMessage().asString() + "OFF"));
        } else {
            resourcePackBypassButton.setMessage(new LiteralText(resourcePackBypassButton.getMessage().asString() + "ON"));
        }

        this.addDrawableChild(resourcePackBypassButton);
    }
}
