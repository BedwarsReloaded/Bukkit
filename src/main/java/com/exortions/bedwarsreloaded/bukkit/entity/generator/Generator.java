package com.exortions.bedwarsreloaded.bukkit.entity.generator;

import com.exortions.bedwarsreloaded.bukkit.BedwarsReloaded;
import com.exortions.bedwarsreloaded.core.util.Chat;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public abstract class Generator {

    private final GeneratorType type;
    private List<ArmorStand> text = new ArrayList<>();
    private ArmorStand stand;

    private final Location location;
    private Location same;

    private BukkitTask textUpdater;
    private BukkitTask updater;

    private boolean stage;
    private int seconds;
    private int tier;

    public Generator(BedwarsReloaded bedwars, GeneratorType type, Location location) {
        this.type = type;
        this.location = location;

        this.stage = false;
        this.tier = 1;

        this.spawn(ChatColor.YELLOW + "Tier " + ChatColor.RED + this.tier, this.type.getColor() + "" + ChatColor.BOLD + this.type.getName(), ChatColor.YELLOW + "Spawns in " + ChatColor.RED + "" + this.seconds + ChatColor.YELLOW + " seconds");
        this.establishUpdater(bedwars);
    }

    public void establishUpdater(BedwarsReloaded plugin) {
        System.out.println("SPEED: " + this.type.getSpeedByTier(this.tier));;
        this.textUpdater = new BukkitRunnable() {
            @Override
            public void run() {
                if (getStand() != null) {
                    if (seconds >= 1) seconds--;
                    if (seconds == 0) {
                        seconds = type.getSpeedByTier(tier);
                        generate();
                    }
                    updateText();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
        this.updater = new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    public void spawn(String... text) {
        this.stand = (ArmorStand) this.location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        this.stand.setGravity(false);
        this.stand.setHelmet(new ItemStack(type.getBlock()));
        this.stand.setVisible(false);

        this.same = stand.getLocation();

        addText(text);
    }

    public void generate() {
        this.location.getWorld().dropItemNaturally(this.location.add(0, 1, 0), new ItemStack(this.type.getItem()));
    }

    public void updateText() {
        var one = text.get(0);
        var two = text.get(1);
        var three = text.get(2);

        this.text.clear();

        three.setCustomName(ChatColor.YELLOW + "Tier " + ChatColor.RED + this.tier);
        two.setCustomName(this.type.getColor() + "" + ChatColor.BOLD + this.type.getName());
        one.setCustomName(ChatColor.YELLOW + "Spawns in " + ChatColor.RED + "" + this.seconds + ChatColor.YELLOW + " seconds");

        this.text.add(one);
        this.text.add(two);
        this.text.add(three);
    }

    public void update() {
        var location = stand.getLocation();

        if (this.stage) {
            location.add(0, 0.01, 0);
            location.setYaw((location.getYaw() + 7.5F));

            stand.teleport(location);

            if (stand.getLocation().getY() > (0.25 + same.getY())) this.stage = false;
        } else {
            location.subtract(0, 0.01, 0);
            location.setYaw((location.getYaw() - 7.5F));

            stand.teleport(location);

            if (stand.getLocation().getY() < (-0.25 + same.getY())) this.stage = true;
        }
    }

    public void addText(String... text) {
        double y = 0.10D;

        for (String line : Lists.reverse(Arrays.asList(text))) {
            var stand = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, y, 0), EntityType.ARMOR_STAND);
            stand.setGravity(false);
            stand.setCustomName(Chat.colorize(line));
            stand.setCustomNameVisible(true);
            stand.setVisible(false);
            y += 0.34D;

            this.text.add(stand);
        }
    }

    public void removeText() {
        for (ArmorStand stand : text) stand.remove();
    }

    public void remove() {
        removeText();
        textUpdater.cancel();
        updater.cancel();
        if (stand != null ) stand.remove();
    }

    @Getter
    @AllArgsConstructor
    public enum GeneratorType {

        DIAMOND("Diamond", ChatColor.AQUA, Material.DIAMOND_BLOCK, Material.DIAMOND, 3, 30, 20, 10),
        EMERALD("Emerald", ChatColor.DARK_GREEN, Material.EMERALD_BLOCK, Material.EMERALD, 3, 60, 50, 40);

        private final String name;
        private final ChatColor color;
        private final Material block;
        private final Material item;
        private final int tiers;

        private final int tierOneSpeed;
        private final int tierTwoSpeed;
        private final int tierThreeSpeed;

        public int getSpeedByTier(int tier) {
            switch (tier) {
                case 2:
                    return tierTwoSpeed;
                case 3:
                    return tierThreeSpeed;
                default:
                    return tierOneSpeed;
            }
        }
    }
}
