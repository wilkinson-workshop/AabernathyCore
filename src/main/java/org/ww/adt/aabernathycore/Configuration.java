package org.ww.adt.aabernathycore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * More or less a singleton class acting as static access
 * and preprocessing for values that are set by the server
 * admin.
 */
public class Configuration {
    private static JavaPlugin m_parent;
    private static FileConfiguration m_config;
    private static Logger m_logger;

    /**
     * Sets the default values for configuration. Requires
     * the initial configuration object from this plugin.
     * @param config
     */
    public static void init(JavaPlugin parent)
    {
        m_config = parent.getConfig();
        m_logger = parent.getLogger();

        m_config.addDefault("debugMode", false);
    }

    /**
     * Create a save state of the configuration.
     */
    public static void save()
    {
        m_config.options().copyDefaults(true);
        m_parent.saveConfig();
    }

    public static boolean getDebugMode()
    {
        return m_config.getBoolean("debugMode");
    }
}
