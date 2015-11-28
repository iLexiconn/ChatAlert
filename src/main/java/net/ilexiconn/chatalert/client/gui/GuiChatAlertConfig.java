package net.ilexiconn.chatalert.client.gui;

import com.google.common.collect.Lists;
import net.ilexiconn.llibrary.common.config.ConfigHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiChatAlertConfig extends GuiConfig {
    public GuiChatAlertConfig(GuiScreen parent) {
        super(parent, getConfigElements(), "chatalert", false, false, "ChatAlert Config");
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = Lists.newArrayList();
        list.add(new DummyConfigElement.DummyCategoryElement("General", "General", ConfigChatAlertGeneral.class));
        list.add(new DummyConfigElement.DummyCategoryElement("Multiplayer", "Multiplayer", ConfigChatAlertMultiplayer.class));
        return list;
    }

    public static class ConfigChatAlertGeneral extends GuiConfigEntries.CategoryEntry {
        public ConfigChatAlertGeneral(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
            super(owningScreen, owningEntryList, configElement);
        }

        public GuiScreen buildChildScreen() {
            return new GuiConfig(owningScreen, new ConfigElement(ConfigHelper.getConfigContainer("chatalert").getConfiguration().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), "chatalert", false, false, "ChatAlert Config", "General");
        }
    }

    public static class ConfigChatAlertMultiplayer extends GuiConfigEntries.CategoryEntry {
        public ConfigChatAlertMultiplayer(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
            super(owningScreen, owningEntryList, configElement);
        }

        public GuiScreen buildChildScreen() {
            return new GuiConfig(owningScreen, new ConfigElement(ConfigHelper.getConfigContainer("chatalert").getConfiguration().getCategory("multiplayer")).getChildElements(), "chatalert", false, false, "ChatAlert Config", "Multiplayer");
        }
    }
}
