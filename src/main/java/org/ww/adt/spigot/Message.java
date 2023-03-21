package org.ww.adt.spigot;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

/**
 * Utility wrapper used to manipulate messages sent to players and the console.
 */
public class Message
{
    /**
     * Default character used for format translation.
     */
    private final static Character pfxChar = '&';

    /**
     * Send message to entity calling some command.
     * @param cmdSender
     * @param message
     */
    public static void send(CommandSender cmdSender, String message)
    {
        send(cmdSender, message, pfxChar);
    }

    /**
     * Send message to entity calling some command.
     * @param cmdSender
     * @param message
     * @param prefixChar
     */
    public static void send(CommandSender cmdSender, String message, Character prefixChar)
    {
        cmdSender.sendMessage(ChatColor.translateAlternateColorCodes(prefixChar, message));
    }

    /**
     * Send message to players if PlayerEvent is triggered.
     * @param event
     * @param message
     */
    public static void send(PlayerEvent event, String message)
    {
        send(event, message, pfxChar);
    }

    /**
     * Send message to players if PlayerEvent is triggered.
     * @param event
     * @param message
     * @param prefixChar
     */
    public static void send(PlayerEvent event, String message, Character prefixChar)
    {
        message = formatMessage(message, event);
        message = ChatColor.translateAlternateColorCodes(prefixChar, message);

        if (event instanceof PlayerJoinEvent)
            ((PlayerJoinEvent) event).setJoinMessage(message);
        else if (event instanceof PlayerQuitEvent)
            ((PlayerQuitEvent) event).setQuitMessage(message);
    }

    /**
     * Send message to a player.
     * @param message
     * @param player
     */
    public static void send(String[] message, Player player)
    {
        send(message, player, pfxChar);
    }

    /**
     * Send message to a player.
     * @param message
     * @param player
     * @param prefixChar
     */
    public static void send(String[] message, Player player, Character prefixChar)
    {
        ArrayList<String> render = new ArrayList<>();

        for (String m : message)
        {
            m = formatMessage(m, player);
            m =  ChatColor.translateAlternateColorCodes(prefixChar, m);
            render.add(m);
        }

        player.sendMessage(render.toArray(new String[0]));
    }

    /**
     * Format the given message replacing keywords with their corresponding
     * value.
     * @param message
     * @return a formatted version of passed string.
     */
    public static String formatMessage(String message)
    {
        // Leave blank for now. We will use this method later
        // to replace any values we wish to replace.
        return message;
    }

    /**
     * Format the given message replacing keywords with their corresponding
     * value.
     * @param message
     * @param event
     * @return a formatted version of passed string.
     */
    public static String formatMessage(String message, Event event)
    {
        if (event instanceof PlayerEvent)
        {
            Player player = ((PlayerEvent)event).getPlayer();
            message = formatMessage(message, player);
        }
        return message;
    }

    /**
     * Format the given message replacing keywords with their corresponding
     * value.
     * @param message
     * @param player
     * @return a formatted version of passed string.
     */
    public static String formatMessage(String message, Player player)
    {
        return message.replaceAll("%\\(player\\)", player.getDisplayName());
    }
}
