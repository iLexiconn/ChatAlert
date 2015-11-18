package net.ilexiconn.chatalert.client;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.ilexiconn.chatalert.server.config.ChatAlertConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.util.List;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {
    public Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent event) {
        if (event.message.getUnformattedText().contains("> ")) {
            List<String> tags = Lists.newArrayList(ChatAlertConfig.tags);
            tags.add(mc.thePlayer.getDisplayName());
            String message = event.message.getUnformattedText().split("> ")[1];
            boolean flag = false;
            for (String tag : tags) {
                if (message.contains(tag)) {
                    flag = true;
                    message = message.replace(tag, ChatAlertConfig.chatFormatting + tag + EnumChatFormatting.RESET);
                }
            }
            if (flag) {
                event.message = new ChatComponentText(event.message.getUnformattedText().split("> ")[0] + "> " + message);
                mc.thePlayer.playSound(ChatAlertConfig.sound, 1f, 1f);
            }
        }
    }
}
