package cope.name.util.internal;

import cope.name.client.ClientBase;
import net.minecraft.client.Minecraft;

public interface Wrapper {
    Minecraft mc = Minecraft.getMinecraft();

    default boolean nullCheck() {
        return mc.world == null || mc.player == null;
    }

    default ClientBase getClient() {
        return ClientBase.getInstance();
    }
}
