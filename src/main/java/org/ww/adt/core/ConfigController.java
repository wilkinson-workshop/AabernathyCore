package org.ww.adt.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.ww.adt.comp.ComponentI;

/**
 * More or less a singleton class acting as static access
 * and preprocessing for values that are set by the server
 * admin.
 */
public class ConfigController implements ComponentI
{

    /**
     * Sets the default values for configuration. Requires
     * the initial configuration object from this plugin.
     */
    public static void init(final JavaPlugin parent)
    {
        sa_parent = (AabernathyPlugin)parent;
        sa_config = sa_parent.getConfig();

        sa_config.addDefault("debugMode", false);

        sa_config.createSection("messaging.on");
        sa_config.createSection("messaging.on.playerJoin");
        sa_config.createSection("messaging.on.playerExit");

        sa_config.addDefault("messaging.on.playerJoin.isActive", false);
        sa_config.addDefault("messaging.on.playerJoin.messages", new ArrayList<String>(Arrays.asList("{player:GREEN} has made an entrance!,".split(","))));
        sa_config.addDefault("messaging.on.playerExit.isActive", false);

        sa_isInit = true;
        sa_parent.getLogger().info(ConfigController.class.getName() + " initialized.");
    }

    public static boolean isInit()
    {
        return sa_isInit;
    }

    /**
     * Whether or not config.yml has been created.
     */
    public static boolean fileExists()
    {
        return new File(sa_parent.getDataFolder() + "config.yml").exists();
    }

    /**
     * Create a save state of the configuration.
     */
    public static void save()
    {
        // We want to only copy defaults if the config.yml
        // file has not been created yet.
        sa_config.options().copyDefaults(!fileExists());
        sa_parent.saveConfig();
        sa_parent.getLogger().info("Saved config successfully.");
    }

    public static boolean debugMode()
    {
        return sa_config.getBoolean("debugMode");
    }

    private static boolean sa_isInit = false;

    private static FileConfiguration sa_config;
    private static AabernathyPlugin sa_parent;
}
