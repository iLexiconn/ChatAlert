package net.ilexiconn.chatalert.client.gui;

import net.ilexiconn.llibrary.common.config.ConfigHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiChatAlertConfig extends GuiConfig {
    public GuiChatAlertConfig(GuiScreen parent) {
        super(parent, new ConfigElement(ConfigHelper.getConfigContainer("chatalert").getConfiguration().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), "chatalert", false, false, "ChatAlert");
    }
}
