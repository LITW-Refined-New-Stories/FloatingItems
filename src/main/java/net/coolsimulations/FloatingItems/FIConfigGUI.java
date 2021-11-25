package net.coolsimulations.FloatingItems;

import java.util.Set;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class FIConfigGUI implements IModGuiFactory {
	
	public static class FIConfigGUIInner extends GuiConfig
    {
        public FIConfigGUIInner(GuiScreen parent)
        {
            super(parent, FIConfig.getConfigElements(), FIReference.MOD_ID, false, false, FILang.getTranslations("fi.configgui.title"));
        }
    }

    @Override
    public void initialize(Minecraft minecraftInstance)
    {

    }
    
    @Override
	public Class<? extends GuiScreen> mainConfigGuiClass()
    {
    	return FIConfigGUIInner.class;
	}

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }
    
    @Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
		return null;
	}

	public GuiScreen createConfigGui(GuiScreen arg0)
	{
		return new FIConfigGUIInner(arg0);
	}

	public boolean hasConfigGui()
	{
		return true;
	}

}
