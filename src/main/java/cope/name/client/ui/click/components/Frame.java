package cope.name.client.ui.click.components;

import cope.name.client.features.module.Module;
import cope.name.client.features.module.ModuleCategory;
import cope.name.client.ui.click.components.impl.button.ModuleButton;
import cope.name.util.renderer.RenderUtil;
import cope.name.util.renderer.ScaleUtil;

import java.awt.*;
import java.util.List;

public class Frame extends Component {
    private boolean expanded = false;

    private boolean dragging = false;
    private double dragX, dragY;

    public Frame(ModuleCategory category, List<Module> modules, double x) {
        super(category.getDisplay());

        setX(x);
        setY(26.0);
        setWidth(108.0);
        setHeight(18.0);

        modules.forEach((module) -> children.add(new ModuleButton(module)));
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (dragging) {
            setX(dragX + mouseX);
            setY(dragY + mouseY);
        }

        RenderUtil.rect(x, y, width, height, new Color(31, 30, 30).getRGB());
        mc.fontRenderer.drawStringWithShadow(getName(), (float) ScaleUtil.alignV(getName(), x, width), (float) ScaleUtil.alignH(y, height), -1);

        if (expanded) {
            double startY = y + height;
            RenderUtil.rect(x, startY, width, totalHeight(), new Color(31, 30, 30, 200).getRGB());
            startY += 0.5;

            for (Component component : children) {
                component.setX(x + 2.0);
                component.setY(startY);
                component.setWidth(width - 4.0);

                component.render(mouseX, mouseY, partialTicks);

                startY += component.getHeight() + 0.5;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isHovered(mouseX, mouseY, this)) {
            switch (button) {
                case 0: {
                    dragX = x - mouseX;
                    dragY = y - mouseY;
                    dragging = true;
                    break;
                }

                case 1: {
                    expanded = !expanded;
                    break;
                }
            }
        }

        if (expanded) {
            children.forEach((child) -> child.mouseClicked(mouseX, mouseY, button));
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (dragging && state == 0) {
            dragging = false;
        }
    }

    @Override
    public void keyTyped(char charTyped, int keyCode) {
        if (expanded) {
            children.forEach((child) -> child.keyTyped(charTyped, keyCode));
        }
    }

    private double totalHeight() {
        double h = 0.0;
        if (expanded) {
            for (Component component : children) {
                h += component.getHeight() + 1.0;
            }

            h += 1.5;
        }

        return h;
    }
}
