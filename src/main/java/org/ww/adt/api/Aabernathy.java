package org.ww.adt.api;

import org.bukkit.configuration.file.FileConfiguration;
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
    private final FileConfiguration configuration;

    public Aabernathy(Plugin plugin) throws IOException
    {
        if (plugin == null)
            throw new IllegalArgumentException("Plugin cannot be null");

        this.plugin = plugin;
        this.plugin.saveDefaultConfig();

        configuration = this.plugin.getConfig();
        plugin.getLogger().info("Config GUID: " + configuration.get("guid", null));

        // Set the singleton to this instance
        setAPI(this);
    }

    public boolean start()
    {
        return true;
    }

    public boolean stop() throws IOException
    {
        this.plugin.saveConfig();
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
}
