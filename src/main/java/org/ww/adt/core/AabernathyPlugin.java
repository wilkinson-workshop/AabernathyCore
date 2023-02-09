package org.ww.adt.core;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AabernathyPlugin extends JavaPlugin
{

    private Logger ma_logger = getLogger();
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        
        // Initialize plugin API.
        AabernathyAPI.init(this);

        // Initialize Aabernathy components.
        AabernathyAPI.initConfig();
        AabernathyAPI.initCommands();

        if (ConfigController.debugMode())
            ma_logger.info("loading complete.");

        //Adding saveConfig to fix issue

        config.addDefault("youAreAwesome", true);
        getConfig().options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (config.getBoolean("youAreAwesome")) {
            player.sendMessage("You are awesome!");
        } else {
            player.sendMessage("You are not awesome...");
        }
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
