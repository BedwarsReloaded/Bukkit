package com.exortions.bedwarsreloaded.bukkit.commands.miscellaneous;

import com.exortions.bedwarsreloaded.bukkit.BedwarsReloaded;
import com.exortions.bedwarsreloaded.bukkit.entity.basegenerator.BaseGenerator;
import com.exortions.bedwarsreloaded.bukkit.entity.generator.DiamondGenerator;
import com.exortions.bedwarsreloaded.bukkit.entity.generator.EmeraldGenerator;
import com.exortions.bedwarsreloaded.bukkit.executors.BaseCommand;
import com.exortions.bedwarsreloaded.core.annotations.command.Description;
import com.exortions.bedwarsreloaded.core.annotations.command.RequiresPlayer;
import com.exortions.bedwarsreloaded.core.annotations.command.Usage;
import org.bukkit.entity.Player;

@RequiresPlayer
@Usage(usage = "<generator>")
@Description(description = "This test command spawns a diamond or emerald generator at the player's location.")
public class SpawnGeneratorCommand extends BaseCommand {

    public SpawnGeneratorCommand(BedwarsReloaded plugin) {
        super(plugin);
    }

    @Override
    public void execute(Player player, String[] args) {
        switch (args[0].toUpperCase()) {
            case "DIAMOND":
                new DiamondGenerator(plugin, player.getLocation());
                break;
            case "EMERALD":
                new EmeraldGenerator(plugin, player.getLocation());
                break;
            case "BASE":
                new BaseGenerator(plugin, player.getLocation());
        }
    }
}
