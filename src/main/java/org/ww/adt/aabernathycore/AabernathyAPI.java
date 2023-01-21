package org.ww.adt.aabernathycore;

/**
 * Aabernathy Core interface for programmatic development.
 */
public class AabernathyAPI {

    private static AabernathyPlugin m_plugin;

    /**
     * Initializes the API at runtime.
     * @param parent
     */
    public static void init(final AabernathyPlugin parent)
    {
        m_plugin = parent;
    }

    public static ConfigController getConfig()
    {
        return new ConfigController();
    }

    public static AabernathyPlugin getPlugin()
    {
        return m_plugin;
    }

    public static void initConfig()
    {
        ConfigController.init(m_plugin);
        ConfigController.save();
    }

    public static void saveConfig()
    {
        ConfigController.save();
    }
}
