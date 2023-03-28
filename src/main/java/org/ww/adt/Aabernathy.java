package org.ww.adt;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.ww.adt.data.Engine;
import org.ww.adt.events.PlayerEventListener;

import java.io.IOException;
import java.util.logging.Logger;

public class Aabernathy implements AabernathyAPI {

    /**
     * Singleton instance used to interact with internally.
     */
    private static AabernathyAPI instance;

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

        // Initialize component API.
        AabernathyComponent.setApiInstance(this);
        
        new Engine();
        
        // Set event listeners.
        new PlayerEventListener();
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

    public Logger getLogger()
    {
        return this.plugin.getLogger();
    }

    public void registerEvent(Listener listener)
    {
        this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
    }
}
