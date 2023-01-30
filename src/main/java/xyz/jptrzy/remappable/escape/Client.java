package xyz.jptrzy.remappable.escape;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Client implements ClientModInitializer {
    public static KeyBinding escapeKeyBinding;

    @Override
    public void onInitializeClient() {
        escapeKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key."+Main.MOD_ID+".escape",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_ESCAPE,
                "key.categories.misc"
        ));
    }
}
