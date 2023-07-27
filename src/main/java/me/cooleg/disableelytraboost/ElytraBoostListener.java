package me.cooleg.disableelytraboost;

import me.cooleg.disableelytraboost.util.Config;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ElytraBoostListener implements Listener {


    private final Config config;

    public ElytraBoostListener(Config config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBoost(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) {return;}
        if (event.getItem() == null) {return;}
        if (event.getItem().getType() != Material.FIREWORK_ROCKET) {return;}
        if (!event.getPlayer().isGliding()) {return;}
        if (event.getPlayer().hasPermission("elytraboost.bypass")) {return;}
        switch (config.getListType()) {
            case WHITELIST -> {
                if (config.getWorldList().contains(event.getPlayer().getWorld())) {return;}
            }
            case BLACKLIST -> {
                if (!config.getWorldList().contains(event.getPlayer().getWorld())) {return;}
            }
        }
        event.setCancelled(true);
        if (!config.shouldSendMessage()) {return;}
        event.getPlayer().sendMessage(config.getMessage());
    }

}
