package me.cooleg.disableelytraboost;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final DisableElytraBoost main;
    private final Component message = Component.text("Reloaded successfully!").color(NamedTextColor.GREEN);

    public ReloadCommand(DisableElytraBoost main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        main.reloadConfig();
        main.getAudiences().players().sendMessage(message);
        return true;
    }

}
