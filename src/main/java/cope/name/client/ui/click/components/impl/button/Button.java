package cope.name.client.ui.click.components.impl.button;

import cope.name.client.ui.click.components.Component;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public abstract class Button extends Component {
    public Button(String name) {
        super(name);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isHovered(mouseX, mouseY, this)) {
            playClickSound();
            onClick(button);
        }
    }

    public abstract void onClick(int button);

    public static void playClickSound() {
        mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
}
