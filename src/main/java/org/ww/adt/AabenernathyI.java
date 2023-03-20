package org.ww.adt;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public interface AabenernathyI {

    /**
     * Start Aabernathy plugin.
     * @return True if plugin was shutdown successfully, False if otherwise.
     */
    boolean start();

    /**
     * Stop Aabernathy plugin.
     * @return True if plugin was shutdown successfully, False if otherwise.
     */
    boolean stop() throws IOException;
}
