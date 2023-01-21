package org.ww.adt.aabernathycore;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AabernathyCore extends JavaPlugin {

    private Logger m_logger = getLogger();
    @Override
    public void onEnable() {
        // Plugin startup logic
        m_logger.info("starting Aabernathy Core..."); // TODO: Display plugin version.

        // Initialize configuration.
        Configuration.init(this);
        Configuration.save();
        m_logger.info("loading complete.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Configuration.save();
        m_logger.info("Aabernathy saved and shutdown successfully.");
    }
}
