package cope.name.client.features.module.movement.sprint;

import cope.name.client.features.module.movement.Sprint;
import cope.name.client.listener.Listener;
import cope.name.client.listener.event.impl.ClientTickEvent;

public class ClientTickListener extends Listener<Sprint, ClientTickEvent> {
    public ClientTickListener(Sprint feature) {
        super(feature);
    }

    @Override
    public void call(ClientTickEvent event) {
        if (mc.gameSettings.keyBindForward.isPressed() &&
                mc.player.getFoodStats().getFoodLevel() > 6 &&
                !mc.player.collidedHorizontally &&
                !mc.player.isHandActive()) {

            mc.player.setSprinting(false);
        }
    }
}
