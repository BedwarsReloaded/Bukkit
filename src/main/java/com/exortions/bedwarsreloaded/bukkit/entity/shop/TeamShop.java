package com.exortions.bedwarsreloaded.bukkit.entity.shop;

import com.exortions.bedwarsreloaded.bukkit.BedwarsReloaded;
import com.exortions.bedwarsreloaded.bukkit.objects.Team;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamShop {

    private List<ArmorStand> text = new ArrayList<>();
    private Villager shop;

    private final BedwarsReloaded bedwars;
    private final Location location;
    private final ItemStack wool;
    private final Team team;

    public TeamShop(BedwarsReloaded bedwars, Location location, Team team) {
        this.bedwars = bedwars;
        this.location = location;
        this.wool = team.getWool();
        this.team = team;

        spawn();
    }

    public void spawn() {
        this.shop = (Villager) this.location.getWorld().spawnEntity(this.location, EntityType.VILLAGER);
        this.shop.setMetadata("teamshop", new FixedMetadataValue(bedwars, true));
        new BukkitRunnable() {
            @Override
            public void run() {
                shop.teleport(location);
            }
        }.runTaskTimer(bedwars, 0, 1);
    }

}
