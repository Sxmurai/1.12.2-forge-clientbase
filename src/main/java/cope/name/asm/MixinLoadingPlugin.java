package cope.name.asm;

import cope.name.client.ClientBase;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.Name("MixinLoadingPlugin")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class MixinLoadingPlugin implements IFMLLoadingPlugin {
    public MixinLoadingPlugin() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.base.json");

        ClientBase.LOGGER.info("Loading {} mixin file(s)", Mixins.getConfigs().size());
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
