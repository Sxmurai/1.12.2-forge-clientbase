package cope.name.client.features.module.client.clickgui;

import cope.name.client.features.module.client.ClickGUI;
import cope.name.client.listener.Listener;
import cope.name.client.listener.event.impl.GuiChangeEvent;

public class GuiChangeListener extends Listener<ClickGUI, GuiChangeEvent> {
    public GuiChangeListener(ClickGUI feature) {
        super(feature);
    }

    @Override
    public void call(GuiChangeEvent event) {
        if (event.getNewScreen() == null) {
            feature.disable();
        }
    }
}
