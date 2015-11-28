package net.ilexiconn.chatalert;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.ilexiconn.chatalert.server.ServerProxy;
import net.ilexiconn.chatalert.server.config.ChatAlertConfig;
import net.ilexiconn.llibrary.common.config.ConfigHelper;
import net.ilexiconn.llibrary.common.log.LoggerHelper;

@Mod(modid = "chatalert", name = "ChatAlert", version = ChatAlert.VERSION, dependencies = "required-after:llibrary@[0.6.0,)", guiFactory = "net.ilexiconn.chatalert.client.gui.GuiConfigFactory")
public class ChatAlert {
    @SidedProxy(serverSide = "net.ilexiconn.chatalert.server.ServerProxy", clientSide = "net.ilexiconn.chatalert.client.ClientProxy")
    public static ServerProxy proxy;
    public static LoggerHelper logger = new LoggerHelper("ChatAlert");

    public static final String VERSION = "0.2.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            return;
        }

        ConfigHelper.registerConfigHandler("chatalert", event.getSuggestedConfigurationFile(), new ChatAlertConfig());

        proxy.preInit();
    }
}