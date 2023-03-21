package org.ww.adt;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class Aabernathy implements AabernathyI {

    /**
     * Singleton instance used to interact with internally.
     */
    private static AabernathyI instance;

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
        setInstance(this);
    }

    public Integer getDebugLevel()
    {
        return getConfig(true).getInt("debugLevel");
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

    public void registerEvent(Listener listener)
    {
        this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
    }

    /**
     * Sets the singleton instance.
     * @param apiInstance
     */
    public static void setInstance(AabernathyI apiInstance)
    {
        instance = apiInstance;
    }

    /**
     * Gets the singleton instance.
     * @return Instance of this interface that is loaded into memory.
     */
    public static AabernathyI getInstance()
    {
        return instance;
    }
}
