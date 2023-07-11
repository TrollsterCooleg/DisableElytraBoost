package me.cooleg.disableelytraboost;

import me.cooleg.disableelytraboost.util.Config;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableElytraBoost extends JavaPlugin {

    private ElytraBoostListener listener;
    private Config config;
    private BukkitAudiences audiences;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        audiences = BukkitAudiences.create(this);
        config = new Config(getConfig().getString("message"),
                getConfig().getStringList("world-list"),
                getConfig().getString("world-list-type"));
        listener = new ElytraBoostListener(config, audiences);

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

    public BukkitAudiences getAudiences() {
        return audiences;
    }

    @Override
    public void onDisable() {
        audiences.close();
        this.audiences = null;
    }
}
