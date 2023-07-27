package me.cooleg.disableelytraboost;

import me.cooleg.disableelytraboost.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableElytraBoost extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        config = new Config(getConfig().getString("message"),
                getConfig().getStringList("world-list"),
                getConfig().getString("world-list-type"));
        ElytraBoostListener listener = new ElytraBoostListener(config);

        getCommand("reloadboost").setExecutor(new ReloadCommand(this));

        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        if (config == null) {return;}
        config.reloadConfig(getConfig().getString("message"),
                getConfig().getStringList("world-list"),
                getConfig().getString("world-list-type"));
    }

}
