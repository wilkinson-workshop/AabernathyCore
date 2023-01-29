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
        sa_parent = parent;
        sa_isInit = true;
        sa_parent.getLogger().info(AabernathyAPI.class.getName() + " initialized.");
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
        return sa_parent;
    }

    public static void initConfig()
    {
        ConfigController.init(sa_parent);
        ConfigController.save();
    }

    public static void saveConfig() { getConfig().save(); }

    private static boolean sa_isInit = false;

    private static AabernathyPlugin sa_parent;
}
