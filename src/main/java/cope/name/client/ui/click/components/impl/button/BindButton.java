package cope.name.client.ui.click.components.impl.button;

import com.mojang.realmsclient.gui.ChatFormatting;
import cope.name.client.setting.Bind;
import cope.name.util.renderer.RenderUtil;
import cope.name.util.renderer.ScaleUtil;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class BindButton extends Button {
    private final Bind bind;

    private boolean listening = false;

    public BindButton(Bind bind) {
        super(bind.getName());
        this.bind = bind;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (isHovered(mouseX, mouseY, this)) {
            RenderUtil.rect(x, y, width, height, new Color(31, 30, 30).darker().getRGB());
        }

        mc.fontRenderer.drawStringWithShadow(
                listening ? "Listening..." : getName() + " " + ChatFormatting.GRAY + Keyboard.getKeyName(bind.getValue()),
                (float) (x + 2.3), (float) ScaleUtil.alignH(y, height), -1);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char charTyped, int keyCode) {
        if (listening) {
            listening = false;

            if (keyCode == Keyboard.KEY_NONE || keyCode == Keyboard.KEY_DELETE) {
                bind.setValue(Keyboard.KEY_NONE);
                return;
            }

            bind.setValue(keyCode);
        }
    }

    @Override
    public void onClick(int button) {
        listening = !listening;
    }
}
