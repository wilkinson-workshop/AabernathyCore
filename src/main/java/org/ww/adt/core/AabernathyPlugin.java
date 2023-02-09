package org.ww.adt.core;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AabernathyPlugin extends JavaPlugin
{

    private Logger ma_logger = getLogger();

    @Override
    public void onEnable() {

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(this);
        
        // Initialize plugin API.
        AabernathyAPI.init(this);

        // Initialize Aabernathy components.
        AabernathyAPI.initConfig();
        AabernathyAPI.initCommands();

        if (ConfigController.debugMode())
            ma_logger.info("loading complete.");

        //Adding saveConfig to fix issue

        

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // if (!ConfigController.fileExists())
        //     AabernathyAPI.saveConfig();
        reloadConfig();
        saveConfig();
        
        if (ConfigController.debugMode())
            ma_logger.info("Aabernathy shutdown successfully.");
    }
}
