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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {
    public Minecraft mc = Minecraft.getMinecraft();

    public int messageIndex = 4;
    public Pattern defaultPattern = Pattern.compile("(<)((?:[a-z][a-z]*[0-9]+[a-z0-9]*))(>)(.*)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent event) {
        List<String> tags = Lists.newArrayList(ChatAlertConfig.tags);
        tags.add(mc.thePlayer.getCommandSenderName());

        Matcher matcher = defaultPattern.matcher(event.message.getUnformattedText());

        if (matcher.find()) {
            String message = matcher.group(messageIndex);

            boolean flag = false;
            for (String tag : tags) {
                if (message.contains(tag)) {
                    flag = true;
                    message = message.replace(tag, ChatAlertConfig.chatFormatting + tag + EnumChatFormatting.RESET);
                }
            }

            if (flag) {
                event.message = new ChatComponentText(matcher.group(1) + matcher.group(2) + matcher.group(3) + message);
                mc.thePlayer.playSound(ChatAlertConfig.sound, 1f, 1f);
            }
        }
    }
}
