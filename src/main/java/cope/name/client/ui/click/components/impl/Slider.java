package cope.name.client.ui.click.components.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import cope.name.client.setting.Setting;
import cope.name.client.ui.click.components.Component;
import cope.name.util.renderer.RenderUtil;
import cope.name.util.renderer.ScaleUtil;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class Slider extends Component {
    private final Setting<Number> setting;
    private final float difference;

    public Slider(Setting<Number> setting) {
        super(setting.getName());

        this.setting = setting;
        this.difference = setting.getMax().floatValue() - setting.getMin().floatValue();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (isHovered(mouseX, mouseY, this) && Mouse.isButtonDown(0)) {
            set(mouseX);
        }

        Color color = new Color(245, 47, 47);
        if (isHovered(mouseX, mouseY, this)) {
            color = color.darker();
        }

        double w = setting.getValue().floatValue() <= setting.getMin().floatValue() ? 0.0 : width * partialMultiplier();
        RenderUtil.rect(x, y, w, height, color.getRGB());

        mc.fontRenderer.drawStringWithShadow(getName() + " " + ChatFormatting.GRAY + setting.getValue(), (float) (x + 2.3), (float) ScaleUtil.alignH(y, height), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isHovered(mouseX, mouseY, this) && button == 0) {
            set(mouseX);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char charTyped, int keyCode) {

    }

    private void set(int mouseX) {
        float percent = (float) (((double) (mouseX) - x) / (float) width);

        if (setting.getValue() instanceof Float) {
            float result = setting.getMin().floatValue() + difference * percent;
            setting.setValue(Math.round(10.0f * result) / 10.0f);
        } else if (setting.getValue() instanceof Double) {
            double result = setting.getMin().doubleValue() + difference * percent;
            setting.setValue(Math.round(10.0 * result) / 10.0);
        } else {
            setting.setValue(Math.round(setting.getMin().intValue() + difference * percent));
        }
    }

    private float part() {
        return setting.getValue().floatValue() - setting.getMin().floatValue();
    }

    private float partialMultiplier() {
        return this.part() / this.difference;
    }
}
