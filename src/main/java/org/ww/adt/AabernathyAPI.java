package org.ww.adt;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import java.util.logging.Logger;

public interface AabernathyAPI
{
    /**
     * Returns what level of debug information is output to server logs
     * and console.
     * @return verbosity level of debug information for this plugin.
     */
    Integer getDebugLevel();

    /**
     * Gets the FileConfiguration instance.
     * @return Aabernathy configuration object.
     */
    FileConfiguration getConfig();

    /**
     * Gets the FileConfiguration instance.
     * @param forceReload whether the config should be reloaded from disk first.
     * @return Aabernathy configuration object.
     */
    FileConfiguration getConfig(boolean forceReload);

    /**
     * Saves the configuration to disk.
     */
    void saveConfig();

    /**
     * Gets the logger associated with this plugin.
     * @return plugin Logger instance.
     */
    Logger getLogger();

    /**
     * Register an event listener and all the containing events.
     * @param listener
     */
    public void registerEvent(Listener listener);
}
