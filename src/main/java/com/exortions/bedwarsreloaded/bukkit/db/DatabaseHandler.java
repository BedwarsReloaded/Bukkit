package com.exortions.bedwarsreloaded.bukkit.db;

import com.exortions.bedwarsreloaded.core.db.AbstractDatabaseHandler;

public class DatabaseHandler extends AbstractDatabaseHandler {
    public DatabaseHandler(String type, String path, String database, String host, String port, String username, String password) {
        super(type, path, database, host, port, username, password);
    }
}
