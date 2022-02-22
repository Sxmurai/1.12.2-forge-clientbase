package cope.name.client.listener.event.impl;

import cope.name.client.listener.event.Event;
import net.minecraft.client.gui.GuiScreen;

public class GuiChangeEvent extends Event {
    private final GuiScreen oldScreen, newScreen;

    public GuiChangeEvent(GuiScreen oldScreen, GuiScreen newScreen) {
        this.oldScreen = oldScreen;
        this.newScreen = newScreen;
    }

    public GuiScreen getOldScreen() {
        return oldScreen;
    }

    public GuiScreen getNewScreen() {
        return newScreen;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }
}
