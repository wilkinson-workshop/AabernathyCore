package org.ww.adt.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.ww.adt.AabernathyI;
import org.ww.adt.spigot.Message;

import java.util.ArrayList;
import java.util.List;
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
        // Send global message to all players.
        Message.send(event, getJoinMessage());

        // Send personal message to player.
        Message.send(getMessageOfTheDay(), event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Message.send(event, getQuitMessage());
    }

    /**
     * Get message of the day.
     * @return message of the day as defined in the configuration.
     */
    private String[] getMessageOfTheDay()
    {
        ArrayList<String> message = (ArrayList<String>) apiInstance
                .getConfig(true)
                .getStringList("messages.motd");

        if (message.size() == 0)
            message.add("Hello %(player), welcome to Aabernathy!");
        return message.toArray(new String[0]);
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
