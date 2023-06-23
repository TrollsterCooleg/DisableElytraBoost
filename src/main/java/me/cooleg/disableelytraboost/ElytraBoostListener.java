package me.cooleg.disableelytraboost;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ElytraBoostListener implements Listener {


    private final BukkitAudiences audiences;
    private static final MiniMessage mini = MiniMessage.miniMessage();
    private static Component message;
    private static boolean sendMessage;

    public ElytraBoostListener(String main, BukkitAudiences audiences) {
        loadConfig(main);
        this.audiences = audiences;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBoost(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) {return;}
        if (event.getItem() == null) {return;}
        if (event.getItem().getType() != Material.FIREWORK_ROCKET) {return;}
        if (!event.getPlayer().isGliding()) {return;}
        if (event.getPlayer().hasPermission("elytraboost.bypass")) {return;}
        event.setCancelled(true);
        if (!sendMessage) {return;}
        audiences.player(event.getPlayer()).sendMessage(message);
    }

    public static void loadConfig(String s) {
        if (s == null || s.isEmpty()) {sendMessage = false; return;}
        message = mini.deserialize(s);
        sendMessage = true;
    }

}
