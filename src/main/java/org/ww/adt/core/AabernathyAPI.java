package org.ww.adt.core;

import org.ww.adt.comp.ComponentI;

/**
 * Aabernathy Core interface for programmatic development.
 */
public class AabernathyAPI implements ComponentI
{

    /**
     * Initializes the API at runtime.
     * @param parent
     */
    public static void init(final AabernathyPlugin parent)
    {
        sa_plugin = parent;
        sa_isInit = true;
    }

    public static boolean isInit()
    {
        return sa_isInit;
    }

    public static ConfigController getConfig()
    {
        return new ConfigController();
    }

    public static AabernathyPlugin getPlugin()
    {
        return sa_plugin;
    }

    public static void initConfig()
    {
        ConfigController.init(sa_plugin);
        ConfigController.save();
    }

    public static void saveConfig()
    {
        ConfigController.save();
    }

    private static boolean sa_isInit = false;

    private static AabernathyPlugin sa_plugin;
}
