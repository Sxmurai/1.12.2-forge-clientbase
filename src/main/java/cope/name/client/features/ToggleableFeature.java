package cope.name.client.features;

public class ToggleableFeature extends Feature {
    private boolean toggled = false;

    public ToggleableFeature(String name) {
        super(name);
    }

    protected void onActivated() {

    }

    protected void onDeactivated() {

    }

    public void enable() {
        toggled = true;
        onActivated();
    }

    public void disable() {
        toggled = false;
        onDeactivated();
    }

    public void toggle() {
        if (toggled) {
            disable();
        } else {
            enable();
        }
    }

    public boolean isToggled() {
        return toggled;
    }
}
