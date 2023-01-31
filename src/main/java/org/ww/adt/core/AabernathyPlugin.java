package org.ww.adt.core;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AabernathyPlugin extends JavaPlugin
{

    private Logger ma_logger = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        ma_logger.info("starting Aabernathy Core..."); // TODO: Display plugin version.

        // Initialize plugin API.
        AabernathyAPI.init(this);

        // Initialize Aabernathy components.
        AabernathyAPI.initConfig();
        AabernathyAPI.initCommands();
        ma_logger.info("loading complete.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        AabernathyAPI.saveConfig();
        ma_logger.info("Aabernathy saved and shutdown successfully.");
    }
}
