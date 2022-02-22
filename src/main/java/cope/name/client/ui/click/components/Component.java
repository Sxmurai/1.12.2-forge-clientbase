package cope.name.client.ui.click.components;

import cope.name.util.internal.Wrapper;

import java.util.ArrayList;

public abstract class Component implements Wrapper {
    private final String name;

    protected double x, y;
    protected double width, height;

    protected final ArrayList<Component> children = new ArrayList<>();

    public Component(String name) {
        this.name = name;
    }

    public abstract void render(int mouseX, int mouseY, float partialTicks);
    public abstract void mouseClicked(int mouseX, int mouseY, int button);
    public abstract void mouseReleased(int mouseX, int mouseY, int state);
    public abstract void keyTyped(char charTyped, int keyCode);

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public static boolean isHovered(int mouseX, int mouseY, Component component) {
        return isHovered(mouseX, mouseY, component.x, component.y, component.width, component.height);
    }

    public static boolean isHovered(int mouseX, int mouseY, double x, double y, double width, double height) {
        return x <= mouseX && x + width >= mouseX && y <= mouseY && y + height >= mouseY;
    }
}
