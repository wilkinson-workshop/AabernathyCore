package org.ww.adt.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import org.ww.adt.AabernathyI;
import org.ww.adt.Aabernathy;

import java.io.IOException;

public final class Plugin extends JavaPlugin
{
    /**
     * Core implementation API of Aabernathy plugin.
     */
    private AabernathyI aabenernathy;

    @Override
    public void onEnable()
    {
        try {
            aabenernathy = new Aabernathy(this);
        } catch (IOException error)
        {
            getLogger().warning("Failed to initialize API");
            getLogger().warning(error.toString());
        }
    }
}
