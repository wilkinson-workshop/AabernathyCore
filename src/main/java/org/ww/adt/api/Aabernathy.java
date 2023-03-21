package org.ww.adt.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.ww.adt.AabernathyI;

import java.io.IOException;

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

    public Aabernathy(Plugin plugin) throws IOException
    {
        if (plugin == null)
            throw new IllegalArgumentException("Plugin cannot be null");

        this.plugin = plugin;
        this.plugin.saveDefaultConfig();

        // Set the singleton to this instance
        setAPI(this);
    }

    public FileConfiguration getConfig()
    {
        return getConfig(false);
    }

    public FileConfiguration getConfig(boolean forceReload)
    {
        if (forceReload)
            this.plugin.reloadConfig();
        return this.plugin.getConfig();
    }

    public void saveConfig()
    {
        this.plugin.saveConfig();
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
     * @return Instance of this interface that is loaded into memory.
     */
    public static AabernathyI getAPI()
    {
        return API;
    }
}
