package net.coolsimulations.FloatingItems;

import net.minecraft.client.gui.GuiScreen;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.SimpleGuiConfig;
import com.gtnewhorizon.gtnhlib.config.SimpleGuiFactory;

public class FIConfigGUI implements SimpleGuiFactory {

    public class FIConfigGUIInner extends SimpleGuiConfig {

        public FIConfigGUIInner(GuiScreen parent) throws ConfigException {
            super(parent, FIReference.MOD_ID, FIReference.MOD_NAME, FIConfig.class);
        }
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return FIConfigGUIInner.class;
    }
}
