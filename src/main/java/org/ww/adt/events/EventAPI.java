package org.ww.adt.events;

import org.bukkit.event.Listener;
import org.ww.adt.AabernathyAPI;
import org.ww.adt.AabernathyComponent;

public abstract class EventAPI extends AabernathyComponent implements Listener {

    public EventAPI()
    {
        super();
        getApiInstance().registerEvent(this);
    }
}
