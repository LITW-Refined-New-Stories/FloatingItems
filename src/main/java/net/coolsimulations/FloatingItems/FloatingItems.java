package net.coolsimulations.FloatingItems;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = FIReference.MOD_ID,
    name = FIReference.MOD_NAME,
    version = FIReference.VERSION,
    acceptedMinecraftVersions = FIReference.ACCEPTED_VERSIONS,
    dependencies = FIReference.DEPENDENCIES,
    acceptableRemoteVersions = "*",
    guiFactory = "net.coolsimulations.FloatingItems.FIConfigGUI")
public class FloatingItems {

    @Mod.Instance(FIReference.MOD_ID)
    public static FloatingItems instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        FIConfig.init();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
