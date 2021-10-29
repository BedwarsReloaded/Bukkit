package com.exortions.bedwarsreloaded.bukkit.entity.generator;

import com.exortions.bedwarsreloaded.bukkit.BedwarsReloaded;
import org.bukkit.Location;

public class DiamondGenerator extends Generator {

    public DiamondGenerator(BedwarsReloaded bedwars, Location location) {
        super(bedwars, GeneratorType.DIAMOND, location);
    }

}
