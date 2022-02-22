package cope.name.client.features.module;

public enum ModuleCategory {
    CLIENT("Client"),
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    WORLD("World");

    private final String display;
    ModuleCategory(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
