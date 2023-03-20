package org.ww.adt.api;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.ww.adt.AabernathyI;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class Aabernathy implements AabernathyI {

    /**
     * The current revision number
     */
    private final static int REVISION = 0;

    /**
     * Singleton instance used to interact with internally.
     */
    private static AabernathyI API;

    /**
     * Parent Plugin object. Hooks in as API used to interact
     * with Minecraft.
     */
    private final Plugin plugin;

    /**
     * The plugin configuration file. As parsed
     * as a YAML format.
     */
    private final YamlConfiguration configuration;

    /**
     * The plugin configuration file.
     */
    private final File configurationFile;

    public Aabernathy(Plugin plugin) throws IOException
    {
        if (plugin == null)
            throw new IllegalArgumentException("Plugin cannot be null");

        this.plugin = plugin;

        configurationFile = getConfigFile();
        configuration = YamlConfiguration.loadConfiguration(configurationFile);

        // Define our configuration values.
        configuration.addDefault("guid", UUID.randomUUID().toString());
        configuration.setComments("guid", Arrays.asList(
            "This is not to be directly modified. This 'guid' value",
            "helps Aabernathy track whether it needs to create this",
            "config file from scratch."
        ));

        configuration.addDefault("debugMode", false);
        configuration.setComments("debugMode", Arrays.asList(
            "This is meant for development purposes only. If 'true',",
            "Aabernathy will output more data to internal logging",
            "and server console."
        ));

        this.plugin.getLogger().info("Config GUID: " + configuration.get("guid", null));

        // Do we need to create the file?
        if (configuration.get("guid", null) == null)
        {
            configuration.options().copyDefaults(true);
            configuration.save(configurationFile);

            // Unset default overwrite of loaded values.
            configuration.options().copyDefaults(false);
        }

        // Set the singleton to this instance
        setAPI(this);
    }

    public boolean start()
    {
        return true;
    }

    public boolean stop() throws IOException
    {
        configuration.save(configurationFile);
        return true;
    }

    /**
     * Sets the singleton instance.
     * @param api
     */
    public static void setAPI(AabernathyI api)
    {
        API = api;
    }

    /**
     * Gets the singleton instance.
     * @returns Instance of this interface that is loaded into memory.
     */
    public static AabernathyI getAPI()
    {
        return API;
    }

    /**
     * Returns the File object representing the on disk location of the
     * configuration. This file should be used to store data.
     * @return the File object for the config file.
     */
    private File getConfigFile()
    {
        File pluginsFolder = plugin.getDataFolder().getParentFile();
        return new File(new File(pluginsFolder, "Aabernathy"), "config.yaml");
    }

    /**
     * Returns the GUID from the configuration.
     * @return GUID value from the configuration.
     */
    private String getConfigGuid()
    {
        Object guid = ;
        if (guid == null)
            return null;
        return (String)guid;
    }

    /**
     * Whether debug mode is enabled.
     * @return debugMode setting status loaded from the configuration.
     */
    private boolean debugModeEnabled()
    {
        return configuration.getBoolean("debugMode");
    }
}
