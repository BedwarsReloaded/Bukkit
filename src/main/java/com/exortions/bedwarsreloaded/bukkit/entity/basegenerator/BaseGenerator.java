package com.exortions.bedwarsreloaded.bukkit.entity.basegenerator;

import com.exortions.bedwarsreloaded.bukkit.BedwarsReloaded;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BaseGenerator {

    private final BedwarsReloaded bedwars;
    private final Location location;

    private int maxIron;
    private int iron;

    public BaseGenerator(BedwarsReloaded bedwars, Location location) {
        this.bedwars = bedwars;
        this.location = location;

        this.maxIron = 4;
        this.iron = 0;

        establishTask();
    }

    public void establishTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (iron >= maxIron-1) {
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_INGOT));
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.GOLD_INGOT));
                    iron = 0;
                    return;
                }
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_INGOT));
                iron++;
            }
        }.runTaskTimer(bedwars, 0, 20);
    }

    public int getMaxIron() {
        return maxIron;
    }

    public void setMaxIron(int maxIron) {
        this.maxIron = maxIron;
    }
}
