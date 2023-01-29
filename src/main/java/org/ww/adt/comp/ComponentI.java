package org.ww.adt.comp;

import org.bukkit.plugin.java.JavaPlugin;

public interface ComponentI {

    /** Initialize this component. */
    public static void init(final JavaPlugin parent) {}

    /** Whether the component has been initialized. */
    public static boolean isInit() { return false; }
}
