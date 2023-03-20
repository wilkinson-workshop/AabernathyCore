package org.ww.adt.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import org.ww.adt.AabenernathyI;
import org.ww.adt.api.Aabernathy;

import java.io.IOException;
import java.util.logging.Logger;

public final class Plugin extends JavaPlugin
{
    /**
     * Core implementation API of Aabernathy plugin.
     */
    private AabenernathyI aabenernathy;

    @Override
    public void onEnable()
    {
        try {
            aabenernathy = new Aabernathy(this);
            aabenernathy.start();
        } catch (IOException error)
        {
            getLogger().warning("Failed to load API");
            getLogger().warning(error.toString());
        }
    }

    @Override
    public void onDisable()
    {
        aabenernathy.stop();
    }
}
