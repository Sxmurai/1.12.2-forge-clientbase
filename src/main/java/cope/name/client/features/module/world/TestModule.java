package cope.name.client.features.module.world;

import cope.name.client.features.module.Module;
import cope.name.client.features.module.ModuleCategory;
import cope.name.client.setting.Setting;

public class TestModule extends Module {
    public TestModule() {
        super("TestModule", ModuleCategory.WORLD, "Test module");
    }

    public static final Setting<Boolean> aBoolSetting = new Setting<>("BoolSetting", false);
    public static final Setting<Integer> intSetting = new Setting<>("IntSetting", 20, 0, 50);
    public static final Setting<Double> doubleSetting = new Setting<>("DoubleSetting", 20.0, 0.0, 50.0);
}
