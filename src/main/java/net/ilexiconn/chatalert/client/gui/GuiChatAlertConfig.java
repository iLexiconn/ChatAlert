package net.ilexiconn.chatalert.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.ilexiconn.llibrary.common.config.ConfigHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

@SideOnly(Side.CLIENT)
public class GuiChatAlertConfig extends GuiConfig {
    public GuiChatAlertConfig(GuiScreen parent) {
        super(parent, new ConfigElement(ConfigHelper.getConfigContainer("chatalert").getConfiguration().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), "chatalert", false, false, "ChatAlert");
    }
}
