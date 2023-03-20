package org.ww.adt;

import java.io.File;

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
    boolean stop();
}
