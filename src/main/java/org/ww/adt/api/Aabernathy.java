package org.ww.adt.api;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.ww.adt.AabenernathyI;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Aabernathy implements AabenernathyI {

    /**
     * The current revision number
     */
    private final static int REVISION = 0;

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
        {
            throw new IllegalArgumentException("Plugin cannot be null");
        }

        this.plugin = plugin;

        configurationFile = getConfigFile();
        configuration = YamlConfiguration.loadConfiguration(configurationFile);

        // Define our configuration values.
        configuration.addDefault("guid", UUID.randomUUID().toString());

        // Do we need to create the file?
        if (configuration.get("guid", null) == null)
        {
            configuration.options().copyDefaults(true);
            configuration.save(configurationFile);
        }
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
     * Returns the File object representing the on disk location of the
     * configuration. This file should be used to store data.
     * @return the File object for the config file.
     */
    private File getConfigFile()
    {
        File pluginsFolder = plugin.getDataFolder().getParentFile();
        return new File(new File(pluginsFolder, "Aabernathy"), "config.yaml");
    }
}