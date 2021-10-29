package com.exortions.bedwarsreloaded.bukkit.commands.miscellaneous;

import com.exortions.bedwarsreloaded.bukkit.BedwarsReloaded;
import com.exortions.bedwarsreloaded.bukkit.executors.BaseCommand;
import com.exortions.bedwarsreloaded.core.annotations.command.Description;
import com.exortions.bedwarsreloaded.core.annotations.command.Usage;
import com.exortions.bedwarsreloaded.core.commands.miscellaneous.IReloadCommand;
import org.bukkit.command.CommandSender;

@Usage(usage = "reload")
@Description(description = "Reloads the plugin.")
public class ReloadCommand extends BaseCommand implements IReloadCommand {
    public ReloadCommand(BedwarsReloaded plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(run(getPlugin(), getPlugin().getPrefix()));
    }
}
