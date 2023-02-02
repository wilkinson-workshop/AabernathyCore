package org.ww.adt.core;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AabernathyPlugin extends JavaPlugin
{

    private Logger ma_logger = getLogger();

    @Override
    public void onEnable() {
        // Initialize plugin API.
        AabernathyAPI.init(this);

        // Initialize Aabernathy components.
        AabernathyAPI.initCommands();
        AabernathyAPI.initConfig();

        if (ConfigController.debugMode())
            ma_logger.info("loading complete.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (!ConfigController.fileExists())
            AabernathyAPI.saveConfig();
        
        if (ConfigController.debugMode())
            ma_logger.info("Aabernathy shutdown successfully.");
    }
}
