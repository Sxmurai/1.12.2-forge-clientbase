package cope.name.client.ui.click.components.impl.button;

import cope.name.client.features.module.Module;
import cope.name.client.setting.Bind;
import cope.name.client.setting.Setting;
import cope.name.client.ui.click.components.Component;
import cope.name.client.ui.click.components.impl.Slider;
import cope.name.util.renderer.RenderUtil;
import cope.name.util.renderer.ScaleUtil;

import java.awt.*;

public class ModuleButton extends Button {
    private final Module module;

    private boolean expanded = false;

    public ModuleButton(Module module) {
        super(module.getName());
        this.module = module;

        setHeight(14.0);

        for (Setting setting : module.getSettings()) {
            if (setting instanceof Bind) {
                children.add(new BindButton((Bind) setting));
                continue;
            }

            if (setting.getValue() instanceof Boolean) {
                children.add(new BooleanButton(setting));
            } else if (setting.getValue() instanceof Number) {
                children.add(new Slider(setting));
            }
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (module.isToggled()) {
            Color color = new Color(245, 47, 47);
            if (isHovered(mouseX, mouseY, this)) {
                color = color.darker();
            }

            RenderUtil.rect(x, y, width, height, color.getRGB());
        } else {
            if (isHovered(mouseX, mouseY, this)) {
                RenderUtil.rect(x, y, width, height, new Color(31, 30, 30).darker().getRGB());
            }
        }

        mc.fontRenderer.drawStringWithShadow(getName(), (float) (x + 2.3), (float) ScaleUtil.alignH(y, height), -1);

        if (expanded) {
            double startY = y + height + 0.5;

            for (Component component : children) {
                component.setX(x + 2.0);
                component.setY(startY);
                component.setHeight(14.0);
                component.setWidth(width - 4.0);

                component.render(mouseX, mouseY, partialTicks);

                startY += component.getHeight() + 0.5;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);

        if (expanded) {
            children.forEach((child) -> child.mouseClicked(mouseX, mouseY, button));
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char charTyped, int keyCode) {
        if (expanded) {
            children.forEach((child) -> child.keyTyped(charTyped, keyCode));
        }
    }

    @Override
    public void onClick(int button) {
        switch (button) {
            case 0: module.toggle(); break;
            case 1: expanded = !expanded; break;
        }
    }

    @Override
    public double getHeight() {
        double h = height;
        if (expanded) {
            for (Component component : children) {
                h += component.getHeight() + 1.0;
            }

            h += 1.5;
        }

        return h;
    }
}
