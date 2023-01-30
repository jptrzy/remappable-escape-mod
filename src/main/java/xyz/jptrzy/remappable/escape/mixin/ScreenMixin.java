package xyz.jptrzy.remappable.escape.mixin;

import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.jptrzy.remappable.escape.Client;

@Mixin(Screen.class)
public class ScreenMixin {

    @Unique public int lastKeyCode;
    @Unique public int lastScanCode;

    @Inject(at = @At("HEAD"), method = "keyPressed(III)V")
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        lastKeyCode = keyCode;
        lastScanCode = scanCode;
    }

    @ModifyConstant(method = "keyPressed(III)V", constant = @Constant(intValue = 256))
    private int injected(int value) {
        return Client.escapeKeyBinding.matchesKey(lastKeyCode, lastScanCode) ? lastKeyCode : GLFW.GLFW_KEY_UNKNOWN;
    }
}
