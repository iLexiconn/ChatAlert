package net.ilexiconn.chatalert;

import net.ilexiconn.chatalert.server.ServerProxy;
import net.ilexiconn.chatalert.server.config.ChatAlertConfig;
import net.ilexiconn.llibrary.common.config.ConfigHelper;
import net.ilexiconn.llibrary.common.log.LoggerHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "chatalert", name = "ChatAlert", version = ChatAlert.VERSION, dependencies = "required-after:llibrary@[0.5.5,)", guiFactory = "net.ilexiconn.chatalert.client.gui.GuiConfigFactory")
public class ChatAlert {
    @SidedProxy(serverSide = "net.ilexiconn.chatalert.server.ServerProxy", clientSide = "net.ilexiconn.chatalert.client.ClientProxy")
    public static ServerProxy proxy;
    public static LoggerHelper logger = new LoggerHelper("ChatAlert");

    public static final String VERSION = "0.1.1";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            logger.info("This mod only works on clients. Aborting initialization...");
            return;
        }

        ConfigHelper.registerConfigHandler("chatalert", event.getSuggestedConfigurationFile(), new ChatAlertConfig());

        proxy.preInit();
    }
}