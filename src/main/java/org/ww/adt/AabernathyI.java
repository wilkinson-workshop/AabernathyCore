package org.ww.adt;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

public interface AabernathyI
{
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
}
