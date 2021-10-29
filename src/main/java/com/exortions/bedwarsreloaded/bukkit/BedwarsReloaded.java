package com.exortions.bedwarsreloaded.bukkit;

import com.exortions.bedwarsreloaded.bukkit.commands.miscellaneous.HelpCommand;
import com.exortions.bedwarsreloaded.bukkit.commands.miscellaneous.ReloadCommand;
import com.exortions.bedwarsreloaded.bukkit.commands.miscellaneous.SpawnGeneratorCommand;
import com.exortions.bedwarsreloaded.bukkit.db.DatabaseHandler;
import com.exortions.bedwarsreloaded.bukkit.executors.CommandExecutor;
import com.exortions.bedwarsreloaded.bukkit.listeners.TeamShopListener;
import com.exortions.bedwarsreloaded.core.BedwarsReloadedPlugin;
import com.exortions.bedwarsreloaded.core.annotations.service.Services;
import com.exortions.bedwarsreloaded.core.commands.SubCommandExecutor;
import com.exortions.bedwarsreloaded.core.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.HashMap;

@Services(services = {
        TeamShopListener.class
})
@SuppressWarnings({"UnusedAssignment", "ConstantConditions"})
public class BedwarsReloaded extends BedwarsReloadedPlugin {

    private DatabaseHandler databaseHandler;
    private CommandExecutor executor;

    private Config configuration;

    @Override
    public void onEnable() {
        long ms = System.currentTimeMillis();
        boolean loaded;

        this.sendStartupMessage();

        this.registerListeners();
        this.registerCommands();

        if (!loadConfiguration()) return; else loaded = true;
        if (!loadMessages()) return; else loaded = true;
        if (!loadStorage()) return; else loaded = true;
        if (!loadData()) return; else loaded = true;

        while (loaded) {
            this.sendMessage(getPrefix() + "Successfully enabled %s in %sms.", getFullName(), (System.currentTimeMillis() - ms));
            loaded = false;
        }
    }

    @Override
    public void registerListeners() {
        this.sendMessage(getPrefix() + "Registering listeners...");
    }

    @Override
    public void registerCommands() {
        this.sendMessage(getPrefix() + "Registering commands...");
        this.executor = new CommandExecutor(this);

        this.registerMainCommand(null);

        new SpawnGeneratorCommand(this);
        new ReloadCommand(this);
        new HelpCommand(this);
    }

    @Override
    public boolean loadConfiguration() {
        this.sendMessage(getPrefix() + "Loading configuration...");

        this.saveDefaultConfig();

        this.configuration = new Config(this, "config.yml");

        return true;
    }

    @Override
    public boolean loadMessages() {
        this.sendMessage(getPrefix() + "Loading messages...");

        return true;
    }

    @Override
    public boolean loadStorage() {
        this.sendMessage(getPrefix() + "Loading storage provider...");

        this.sendMessage(getPrefix() + " - Loading database information...");
        var dbinfo = new HashMap<String, String>();
        dbinfo.put("type", configuration.getString("database.type"));
        dbinfo.put("path", configuration.getString("database.path"));

        dbinfo.put("database", configuration.getString("database.database"));
        dbinfo.put("host", configuration.getString("database.host"));
        dbinfo.put("port", configuration.getString("database.port"));

        dbinfo.put("username", configuration.getString("database.username"));
        dbinfo.put("password", configuration.getString("database.password"));

        databaseHandler = new DatabaseHandler(dbinfo.get("type"), dbinfo.get("path"), dbinfo.get("database"), dbinfo.get("host"), dbinfo.get("port"), dbinfo.get("username"), dbinfo.get("password"));

        this.sendMessage(getPrefix() + " - Attempting to create database connection...");
        // databaseHandler.createConnection();
        return true;
    }

    @Override
    public boolean loadData() {
        this.sendMessage(getPrefix() + "Loading data...");

        return true;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void reload() {
        this.onDisable();
        this.onEnable();
    }

    @Override
    public SubCommandExecutor<? extends BedwarsReloadedPlugin> getExecutor() {
        return this.executor;
    }

    @Override
    public void setExecutor(SubCommandExecutor<? extends BedwarsReloadedPlugin> executor) {
        this.executor = (CommandExecutor) executor;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    @Override
    public Server getRunningServer() {
        return Bukkit.getServer();
    }
}
