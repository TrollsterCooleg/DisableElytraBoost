package me.cooleg.disableelytraboost;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final DisableElytraBoost main;


    public ReloadCommand(DisableElytraBoost main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        main.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Reloaded sucessfully!");
        return true;
    }

}
