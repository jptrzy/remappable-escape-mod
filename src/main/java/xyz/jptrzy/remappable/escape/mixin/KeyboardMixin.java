package xyz.jptrzy.remappable.escape.mixin;

import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.jptrzy.remappable.escape.Client;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Unique
    public int lastKeyCode;
    @Unique public int lastScanCode;

    @Inject(at = @At("HEAD"), method = "onKey")
    public void onKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        lastKeyCode = keyCode;
        lastScanCode = scanCode;
    }

    @ModifyConstant(method = "onKey", constant = @Constant(intValue = 256))
    private int injected(int value) {
        return Client.escapeKeyBinding.matchesKey(lastKeyCode, lastScanCode) ? lastKeyCode : GLFW.GLFW_KEY_UNKNOWN;
    }
}
