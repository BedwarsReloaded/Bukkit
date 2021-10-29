package com.exortions.bedwarsreloaded.bukkit.executors;

import com.exortions.bedwarsreloaded.bukkit.BedwarsReloaded;
import com.exortions.bedwarsreloaded.core.commands.AbstractBaseCommand;

public abstract class BaseCommand extends AbstractBaseCommand {

    protected final BedwarsReloaded plugin;

    public BaseCommand(BedwarsReloaded plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public BedwarsReloaded getPlugin() {
        return plugin;
    }
}
