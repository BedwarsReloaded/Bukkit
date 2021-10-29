package com.exortions.bedwarsreloaded.bukkit.listeners;

import com.exortions.bedwarsreloaded.core.BedwarsReloadedPlugin;
import com.exortions.bedwarsreloaded.core.annotations.service.OnEnable;
import com.exortions.bedwarsreloaded.core.annotations.service.Service;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.logging.Level;

@Service
public class TeamShopListener implements Listener {

    @OnEnable
    public void onEnable(BedwarsReloadedPlugin plugin) {
        Bukkit.getLogger().log(Level.INFO, "Initialized Team Shop listener!");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTeamShopRightClick(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER && e.getRightClicked().hasMetadata("teamshop")) {
            Villager villager = (Villager) e.getRightClicked();
        }
    }

}
