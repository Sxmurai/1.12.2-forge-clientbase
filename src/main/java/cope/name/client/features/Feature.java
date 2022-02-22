package cope.name.client.features;

import cope.name.util.internal.Wrapper;

public class Feature implements Wrapper {
    private final String name;

    public Feature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
