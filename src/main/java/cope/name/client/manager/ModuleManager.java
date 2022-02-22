package cope.name.client.manager;

import cope.name.client.ClientBase;
import cope.name.client.features.module.Module;
import cope.name.client.features.module.client.ClickGUI;
import cope.name.client.features.module.movement.Sprint;
import cope.name.client.features.module.world.TestModule;
import cope.name.util.internal.Wrapper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class ModuleManager implements Wrapper {
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        MinecraftForge.EVENT_BUS.register(this);

        modules.add(new ClickGUI());
        modules.add(new Sprint());
        modules.add(new TestModule());

        modules.forEach(Module::registerAllSettings);

        ClientBase.LOGGER.info("Loaded {} modules.", modules.size());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        if (mc.currentScreen == null && key != Keyboard.KEY_NONE && !Keyboard.getEventKeyState()) {
            modules.forEach((module) -> {
                if (module.getKeyBind() == key) {
                    module.toggle();
                }
            });
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
}
