package me.cooleg.disableelytraboost;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableElytraBoost extends JavaPlugin {

    private ElytraBoostListener listener;

    @Override
    public void onEnable() {
        getCommand("reloadboost").setExecutor(new ReloadCommand(this));

        listener = new ElytraBoostListener(getConfig().getString("message"));
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        listener.loadConfig(getConfig().getString("message"));
    }

}
