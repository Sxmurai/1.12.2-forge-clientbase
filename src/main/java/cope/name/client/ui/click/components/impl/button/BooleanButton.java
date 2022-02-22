package cope.name.client.ui.click.components.impl.button;

import cope.name.client.setting.Setting;
import cope.name.util.renderer.RenderUtil;
import cope.name.util.renderer.ScaleUtil;

import java.awt.*;

public class BooleanButton extends Button {
    private final Setting<Boolean> setting;

    public BooleanButton(Setting<Boolean> setting) {
        super(setting.getName());
        this.setting = setting;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (setting.getValue()) {
            Color color = new Color(245, 47, 47);
            if (isHovered(mouseX, mouseY, this)) {
                color = color.darker();
            }

            RenderUtil.rect(x, y, width, height, color.getRGB());
        }

        mc.fontRenderer.drawStringWithShadow(getName(), (float) (x + 2.3), (float) ScaleUtil.alignH(y, height), -1);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char charTyped, int keyCode) {

    }

    @Override
    public void onClick(int button) {
        setting.setValue(!setting.getValue());
    }
}
