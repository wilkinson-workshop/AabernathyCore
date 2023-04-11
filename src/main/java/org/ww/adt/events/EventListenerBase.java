package org.ww.adt.events;

import org.bukkit.event.Listener;
import org.ww.adt.AabernathyComponent;

public abstract class EventListenerBase extends AabernathyComponent implements Listener {

    public EventListenerBase()
    {
        super();
        getApiInstance().registerEvent(this);
    }
}
