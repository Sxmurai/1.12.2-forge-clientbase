package cope.name.client;

import cope.name.client.listener.ListenerHandler;
import cope.name.client.manager.FMLEventWrapperManager;
import cope.name.client.manager.ModuleManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(
        name = ClientBase.NAME,
        modid = ClientBase.MOD_ID,
        version = ClientBase.VERSION,
        acceptedMinecraftVersions = "1.12.2"
)
public class ClientBase {
    public static final String NAME = "ClientBase";
    public static final String MOD_ID = "clientbase";
    public static final String VERSION = "1.0.0";

    @Mod.Instance
    public static ClientBase INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final ListenerHandler EVENT_BUS = new ListenerHandler();

    private ModuleManager moduleManager;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Loading {} v{}", NAME, VERSION);

        moduleManager = new ModuleManager();
        new FMLEventWrapperManager();

        Display.setTitle(NAME + " v" + VERSION);
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static ClientBase getInstance() {
        return INSTANCE;
    }
}
