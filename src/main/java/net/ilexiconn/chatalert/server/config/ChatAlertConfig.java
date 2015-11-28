package net.ilexiconn.chatalert.server.config;

import net.ilexiconn.llibrary.common.config.IConfigHandler;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;

public class ChatAlertConfig implements IConfigHandler {
    public static String[] tags;

    public static String sound;
    public String[] sounds = new String[] {"fireworks.twinkle", "random.click", "gui.button.press", "random.chestopen",
            "random.eat", "random.break", "random.bowhit", "random.orb", "random.splash", "tile.piston.in"};

    public static String color;
    public static EnumChatFormatting chatFormatting;
    public String[] colors = new String[]{"Black", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple",
            "Gold", "Gray", "Dark Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White", "Bold",
            "Underline", "Italic"};

    public static String[] serverRegex;
    public static String[] ignoredPeople;

    public void loadConfig(Configuration config) {
        tags = config.getStringList("Tags", Configuration.CATEGORY_GENERAL, new String[]{}, "Set the tags to get notifications from. Your current Minecraft username is always marked as a tag.");
        sound = config.getString("Sound", Configuration.CATEGORY_GENERAL, "random.orb", "Set the alert sound. This sound is played when someone mentioned you in chat.", sounds);
        color = config.getString("Color", Configuration.CATEGORY_GENERAL, "Yellow", "Set the alert highlight color for in the chat.", colors);
        chatFormatting = EnumChatFormatting.getValueByName(color.replace(" ", "_"));
        serverRegex = config.getStringList("Custom Chat Layouts", "multiplayer", new String[]{}, "Set the regex for server chat layouts. Formatting: 'server-ip;layout-regex;username-index' (without the quotes). Example for Hypixel: 'play.hypixel.net;(\\[.*?])?( )?((?:[a-zA-Z0-9_]+))(:)?(.*);3'.");
        ignoredPeople = config.getStringList("Ignored People", "multiplayer", new String[]{}, "Ignore people in the minecraft chat. If the username is in the list, you won't receive messages from the user.");
    }
}
