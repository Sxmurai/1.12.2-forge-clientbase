package cope.name.client.features.module.client;

import cope.name.client.features.module.Module;
import cope.name.client.features.module.ModuleCategory;
import cope.name.client.features.module.client.clickgui.GuiChangeListener;
import cope.name.client.ui.click.ClickGUIScreen;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", ModuleCategory.CLIENT, "Opens this GUI", Keyboard.KEY_P);
        addListeners(new GuiChangeListener(this));
    }

    @Override
    protected void onActivated() {
        if (nullCheck()) {
            disable();
            return;
        }

        mc.displayGuiScreen(ClickGUIScreen.getInstance());
    }

    @Override
    protected void onDeactivated() {
        if (!nullCheck()) {
            mc.displayGuiScreen(null);
        }
    }
}
