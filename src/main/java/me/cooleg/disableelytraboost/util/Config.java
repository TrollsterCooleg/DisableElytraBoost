package me.cooleg.disableelytraboost.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;

import java.util.Collection;
import java.util.HashSet;

public class Config {

    private String message;
    private boolean sendMessage;
    private final Collection<World> worldList = new HashSet<>();
    private WorldListType listType;

    public Config(String message, Collection<String> worldStringList, String worldListType) {
        reloadConfig(message, worldStringList, worldListType);
    }

    public void reloadConfig(String msg, Collection<String> worldStringList, String worldListType) {
        worldList.clear();

        if (msg == null || msg.isEmpty()) {
            sendMessage = false;
        } else {
            message = ChatColor.translateAlternateColorCodes('&', msg);
            sendMessage = true;
        }

        for (String worldName : worldStringList) {
            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                Bukkit.getLogger().severe("Couldn't find world named %s!".formatted(worldName));
                continue;
            }
            worldList.add(world);
        }

        try {
            listType = WorldListType.valueOf(worldListType.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            Bukkit.getLogger().severe("No world list type called \"%s\"! It should be \"Whitelist\", \"Blacklist\", or \"Disabled\". Defaulting to disabled...".formatted(worldListType));
            listType = WorldListType.DISABLED;
        }

        if (worldList.isEmpty() && listType != WorldListType.DISABLED) {
            Bukkit.getLogger().severe("The world list type is not set to disabled, but your worlds list is empty! Setting to disabled...");
            listType = WorldListType.DISABLED;
        }
    }

    public String getMessage() {
        return message;
    }

    public boolean shouldSendMessage() {
        return sendMessage;
    }

    public Collection<World> getWorldList() {
        return worldList;
    }

    public WorldListType getListType() {
        return listType;
    }

}
