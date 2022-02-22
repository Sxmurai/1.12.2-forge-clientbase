package cope.name.client.ui.click;

import cope.name.client.features.module.Module;
import cope.name.client.features.module.ModuleCategory;
import cope.name.client.ui.click.components.Frame;
import cope.name.util.internal.Wrapper;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClickGUIScreen extends GuiScreen implements Wrapper {
    private static ClickGUIScreen INSTANCE;
    private final ArrayList<Frame> frames = new ArrayList<>();

    public ClickGUIScreen() {
        double x = 4.0;

        for (ModuleCategory category : ModuleCategory.values()) {
            List<Module> modules = getClient().getModuleManager().getModules().stream()
                    .filter((module) -> module.getCategory().equals(category))
                    .collect(Collectors.toList());

            if (!modules.isEmpty()) {
                frames.add(new Frame(category, modules, x));
                x += 114.0;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawDefaultBackground();

        int scroll = Mouse.getDWheel();
        if (scroll > 0) {
            frames.forEach((frame) -> frame.setY(frame.getY() - 10.0));
        } else if (scroll < 0) {
            frames.forEach((frame) -> frame.setY(frame.getY() + 10.0));
        }

        frames.forEach((frame) -> frame.render(mouseX, mouseY, partialTicks));
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        frames.forEach((frame) -> frame.mouseClicked(mouseX, mouseY, mouseButton));
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        frames.forEach((frame) -> frame.mouseReleased(mouseX, mouseY, state));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        frames.forEach((frame) -> frame.keyTyped(typedChar, keyCode));
    }

    public static ClickGUIScreen getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClickGUIScreen();
        }

        return INSTANCE;
    }
}
