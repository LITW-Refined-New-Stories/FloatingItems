package net.coolsimulations.FloatingItems;

import com.gtnewhorizon.gtnhlib.config.Config;
import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;

@Config(modid = FIReference.MOD_ID)
public class FIConfig {

    @Config.Comment("Blacklist of items that should not float."
        + "\nFormat: {modid}:{itemname}:(metadata). Example of sticks: minecraft:stick"
        + "\nYou can also exclude ore dictionary entries. Example: ore:coal")
    @Config.DefaultStringList({})
    public static String[] blacklistItems;

    public static void init() {
        try {
            ConfigurationManager.registerConfig(FIConfig.class);
        } catch (ConfigException e) {
            throw new RuntimeException(e);
        }
    }
}
