package com.exortions.bedwarsreloaded.bukkit.objects;

import com.exortions.bedwarsreloaded.api.team.TeamColor;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Team implements com.exortions.bedwarsreloaded.api.team.Team {

    private final TeamColor color;
    private final String name;

    private List<Player> players;
    private List<Player> activePlayers;
    private List<Player> deadPlayers;

    private boolean alive;
    private boolean bedAlive;

    public Team(TeamColor color, String name) {
        this.color = color;
        this.name = name;

        this.players = new ArrayList<>();
        this.activePlayers = new ArrayList<>();
        this.deadPlayers = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TeamColor getTeamColor() {
        return color;
    }

    @Override
    public int getWoolType() {
        return color.getWool();
    }

    @Override
    public ItemStack getWool() {
        return new ItemStack(Material.WOOL, 1, (short) getWoolType());
    }

    @Override
    public ChatColor getColor() {
        return color.getColor();
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    @Override
    public List<Player> getDeadPlayers() {
        return deadPlayers;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean isBedAlive() {
        return bedAlive;
    }
}
