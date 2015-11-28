package net.ilexiconn.chatalert.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.ilexiconn.chatalert.ChatAlert;
import net.ilexiconn.chatalert.server.config.ChatAlertConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {
    public Minecraft mc = Minecraft.getMinecraft();

    public int defaultUsernameIndex = 2;
    public Pattern defaultPattern = Pattern.compile("(<)((?:[a-zA-Z0-9_]+))(>)(.*)");

    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent event) {
        Matcher matcher = null;

        int usernameIndex = -1;
        for (String s : ChatAlertConfig.serverRegex) {
            String[] p = s.split(";");
            if (p.length != 3) {
                ChatAlert.logger.error("Found faulty config entry for 'Custom Chat Layouts': " + s);
                continue;
            }
            if (mc.func_147104_D() != null && mc.func_147104_D().serverIP.equals(p[0])) {
                matcher = Pattern.compile(p[1]).matcher(event.message.getUnformattedText());
                usernameIndex = Integer.parseInt(p[2]);
                break;
            }
        }
        if (matcher == null) {
            matcher = defaultPattern.matcher(event.message.getUnformattedText());
            usernameIndex = defaultUsernameIndex;
        }

        if (matcher.find()) {
            String username = matcher.group(usernameIndex);
            for (String s : ChatAlertConfig.ignoredPeople) {
                if (s.equals(username.trim())) {
                    event.setCanceled(true);
                }
            }

            String lastColor = EnumChatFormatting.WHITE.toString();
            int lastIndex = event.message.getFormattedText().substring(0, event.message.getFormattedText().length() - 8).lastIndexOf("§");
            if (lastIndex != -1) {
                lastColor = event.message.getFormattedText().substring(lastIndex, lastIndex + 2);
            }

            for (String s : ChatAlertConfig.tags) {
                event.message = new ChatComponentText(event.message.getFormattedText().replace(s, EnumChatFormatting.getValueByName(ChatAlertConfig.color) + s + lastColor));
            }

            mc.thePlayer.playSound(ChatAlertConfig.sound, 1f, 1f);
        }
    }
}
