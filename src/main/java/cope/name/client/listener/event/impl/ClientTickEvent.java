package cope.name.client.listener.event.impl;

import cope.name.client.listener.event.Event;

public class ClientTickEvent extends Event {
    @Override
    public boolean isCancellable() {
        return false;
    }
}
