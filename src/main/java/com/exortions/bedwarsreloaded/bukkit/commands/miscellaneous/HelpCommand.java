package com.exortions.bedwarsreloaded.bukkit.commands.miscellaneous;

import com.exortions.bedwarsreloaded.bukkit.BedwarsReloaded;
import com.exortions.bedwarsreloaded.bukkit.executors.BaseCommand;
import com.exortions.bedwarsreloaded.core.annotations.command.Description;
import com.exortions.bedwarsreloaded.core.annotations.command.Usage;
import com.exortions.bedwarsreloaded.core.commands.miscellaneous.IHelpCommand;
import org.bukkit.command.CommandSender;

@Usage(usage = "help OR help <command>")
@Description(description = "Displays all commands and their usages.")
public class HelpCommand extends BaseCommand implements IHelpCommand {

    public HelpCommand(BedwarsReloaded plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(run(getPlugin(), getPlugin().getPrefix(), getPlugin().getExecutor().getCommands(), args));
    }

}
