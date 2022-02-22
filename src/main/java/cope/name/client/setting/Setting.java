package cope.name.client.setting;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Setting<T> {
    private final String name;
    private T value;

    private final Number min, max;

    private final Setting parent;
    private final Supplier<Boolean> visibility;
    
    public final ArrayList<Setting<?>> children = new ArrayList<>();

    public Setting(String name, T value) {
        this(null, name, value);
    }

    public Setting(Setting setting, String name, T value) {
        this(setting, name, value, null, null, null);
    }

    public Setting(Setting setting, String name, T value, Supplier<Boolean> visibility) {
        this(setting, name, value, null, null, visibility);
    }

    public Setting(String name, T value, Number min, Number max) {
        this(name, value, min, max, null);
    }

    public Setting(String name, T value, Number min, Number max, Supplier<Boolean> visibility) {
        this(null, name, value, min, max, visibility);
    }
    
    public Setting(Setting parent, String name, T value, Number min, Number max) {
        this(parent, name, value, min, max, null);
    }

    public Setting(Setting parent, String name, T value, Number min, Number max, Supplier<Boolean> visibility) {
        this.parent = parent;
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.visibility = visibility;
        
        if (parent != null) {
        	parent.children.add(this);
        }
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }

    public Setting getParent() {
        return parent;
    }

    public boolean isVisible() {
        return visibility == null || visibility.get();
    }
    
    public static int current(Enum clazz) {
        for (int i = 0; i < clazz.getClass().getEnumConstants().length; ++i) {
            Enum e = ((Enum[]) clazz.getClass().getEnumConstants())[i];
            if (e.name().equalsIgnoreCase(clazz.name())) {
                return i;
            }
        }

        return -1;
    }

    public static Enum increase(Enum clazz) {
        int index = current(clazz);

        for (int i = 0; i < clazz.getClass().getEnumConstants().length; ++i) {
            Enum e = ((Enum[]) clazz.getClass().getEnumConstants())[i];
            if (i == index + 1) {
                return e;
            }
        }

        return ((Enum[]) clazz.getClass().getEnumConstants())[0];
    }
}
