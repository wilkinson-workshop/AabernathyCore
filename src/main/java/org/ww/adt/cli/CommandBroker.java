package org.ww.adt.cli;

import org.bukkit.plugin.java.JavaPlugin;

import org.ww.adt.comp.ComponentI;
import org.ww.adt.core.AabernathyPlugin;

public class CommandBroker implements ComponentI {

    /**
     * Registers commands this broker is in charge of.
     */
    public static void init(final JavaPlugin parent)
    {
        sa_parent = (AabernathyPlugin)parent;
        sm_registerCommands();
        sa_isInit = true;
        sa_parent.getLogger().info(CommandBroker.class.getName() + " initialized.");
    }

    public static boolean isInit()
    {
        return sa_isInit;
    }

    private static void sm_registerCommands()
    {
        sa_parent.getCommand("kit").setExecutor(new CommandKit());
        sa_parent.getCommand("devel").setExecutor(new CommandDevel());
    }

    private static boolean sa_isInit = false;

    private static AabernathyPlugin sa_parent;
}
