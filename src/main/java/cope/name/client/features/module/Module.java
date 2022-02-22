package cope.name.client.features.module;

import cope.name.client.ClientBase;
import cope.name.client.features.ToggleableFeature;
import cope.name.client.listener.Listener;
import cope.name.client.setting.Bind;
import cope.name.client.setting.Setting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;

public class Module extends ToggleableFeature {
    private final ModuleCategory category;
    private final String description;

    private final ArrayList<Setting> settings = new ArrayList<>();

    private final Setting<Boolean> drawn = new Setting<>("Drawn", true);
    private final Bind bind = new Bind("Bind", Keyboard.KEY_NONE);

    public Module(String name, ModuleCategory category, String description) {
        this(name, category, description, Keyboard.KEY_NONE);
    }

    public Module(String name, ModuleCategory category, String description, int keyCode) {
        super(name);

        this.category = category;
        this.description = description;

        bind.setValue(keyCode);

        settings.add(drawn);
        settings.add(bind);
    }

    protected void addListeners(Listener... listeners) {
        ClientBase.EVENT_BUS.register(listeners);
    }

    public void registerAllSettings() {
        Arrays.stream(getClass().getFields())
                .filter((field) -> Setting.class.isAssignableFrom(field.getType()))
                .spliterator()
                .forEachRemaining((field) -> {
                    field.setAccessible(true);

                    try {
                        settings.add((Setting) field.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public void setBind(int in) {
        bind.setValue(in);
    }

    public int getKeyBind() {
        return bind.getValue();
    }

    public void setDrawn(boolean in) {
        drawn.setValue(in);
    }

    public boolean shouldDraw() {
        return drawn.getValue();
    }
}
