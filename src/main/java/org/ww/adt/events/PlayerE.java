package org.ww.adt.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.ww.adt.AabernathyI;
import org.ww.adt.spigot.Message;

import java.util.ArrayList;
import java.util.Random;

public class PlayerE implements Listener
{

    /**
     * Parent instance of Aabernathy's API.
     */
    private final AabernathyI apiInstance;

    /**
     * Random number generator.
     */
    private final static Random random = new Random();

    public PlayerE(AabernathyI apiInstance)
    {
        this.apiInstance = apiInstance;
        this.apiInstance.registerEvent(this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Message.send(event, getJoinMessage());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Message.send(event, getQuitMessage());
    }

    /**
     * Returns a random join message from configuration.
     * @return a random join message.
     */
    private String getJoinMessage()
    {
        ArrayList<String> messageOpts = (ArrayList<String>) apiInstance
                .getConfig(true)
                .getStringList("messages.onPlayerJoin");

        if (messageOpts.size() == 0)
            return "%(player) has joined the game.";
        return messageOpts.get(random.nextInt(messageOpts.size()));
    }

    /**
     * Returns a random quit message from configuration.
     * @return a random quit message.
     */
    private String getQuitMessage()
    {
        ArrayList<String> messageOpts = (ArrayList<String>) apiInstance
                .getConfig(true)
                .getStringList("messages.onPlayerQuit");

        if (messageOpts.size() == 0)
            return "%(player) has left the game.";
        return messageOpts.get(random.nextInt(messageOpts.size()));
    }
}
