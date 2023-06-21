package me.cooleg.disableelytraboost;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ElytraBoostListener implements Listener {

    private Component message;
    private final MiniMessage mini = MiniMessage.miniMessage();
    private boolean sendMessage;

    public ElytraBoostListener(String s) {
        loadConfig(s);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    private void onBoost(PlayerElytraBoostEvent event) {
        if (event.getPlayer().hasPermission("elytraboost.bypass")) {return;}
        event.setCancelled(true);
        if (!sendMessage) {return;}
        event.getPlayer().sendMessage(message);
    }

    public void loadConfig(String s) {
        if (s == null || s.isEmpty()) {sendMessage = false; return;}
        message = mini.deserialize(s);
        sendMessage = true;
    }

}
