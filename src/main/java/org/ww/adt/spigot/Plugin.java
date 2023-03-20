package org.ww.adt.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import org.ww.adt.AabernathyI;
import org.ww.adt.api.Aabernathy;

import java.io.IOException;
import java.util.logging.Logger;

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

        if (!aabenernathy.start())
            getLogger().warning("Failed to start API");
    }

    @Override
    public void onDisable()
    {
        try {
            if (!aabenernathy.stop())
                getLogger().warning("Failed to stop API");
        } catch (IOException error)
        {
            getLogger().warning("Failed to stop API");
            getLogger().warning(error.toString());
        }
    }
}
