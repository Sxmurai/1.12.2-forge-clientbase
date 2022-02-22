package cope.name.client.features.module.movement;

import cope.name.client.features.module.Module;
import cope.name.client.features.module.ModuleCategory;
import cope.name.client.features.module.movement.sprint.ClientTickListener;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", ModuleCategory.MOVEMENT, "Makes you automatically sprint");
        addListeners(new ClientTickListener(this));
    }

    @Override
    protected void onDeactivated() {
        if (!nullCheck()) {
            mc.player.setSprinting(false);
        }
    }
}
