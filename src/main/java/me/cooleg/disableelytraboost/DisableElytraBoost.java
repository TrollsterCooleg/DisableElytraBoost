package me.cooleg.disableelytraboost;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableElytraBoost extends JavaPlugin {

    private ElytraBoostListener listener;
    private BukkitAudiences audiences;

    @Override
    public void onEnable() {
        audiences = BukkitAudiences.create(this);
        listener = new ElytraBoostListener(getConfig().getString("message"), audiences);

        getCommand("reloadboost").setExecutor(new ReloadCommand(this));

        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        ElytraBoostListener.loadConfig(getConfig().getString("message"));
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
