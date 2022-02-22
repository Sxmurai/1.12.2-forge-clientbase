package cope.name.client.manager;

import cope.name.client.ClientBase;
import cope.name.client.listener.event.impl.ClientTickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FMLEventWrapperManager {
    public FMLEventWrapperManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        ClientBase.EVENT_BUS.dispatch(new ClientTickEvent());
    }
}
