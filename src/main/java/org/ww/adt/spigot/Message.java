package org.ww.adt.spigot;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Utility wrapper used to manipulate messages sent to players and the console.
 */
public class Message
{
    public static void send(CommandSender cmdSender, String message)
    {
        send(cmdSender, message, '&');
    }

    public static void send(CommandSender cmdSender, String message, Character prefixChar)
    {
        cmdSender.sendMessage(ChatColor.translateAlternateColorCodes(prefixChar, message));
    }

    public static void send(PlayerEvent event, String message)
    {
        send(event, message, '&');
    }

    public static void send(PlayerEvent event, String message, Character prefixChar)
    {
        message = formatMessage(message, event);
        message = ChatColor.translateAlternateColorCodes(prefixChar, message);

        if (event instanceof PlayerJoinEvent)
            ((PlayerJoinEvent) event).setJoinMessage(message);
        else if (event instanceof PlayerQuitEvent)
            ((PlayerQuitEvent) event).setQuitMessage(message);
    }

    public static String formatMessage(String message)
    {
        // Leave blank for now. We will use this method later
        // to replace any values we wish to replace.
        return message;
    }

    public static String formatMessage(String message, Event event)
    {
        if (event instanceof PlayerEvent)
        {
            String playerName = ((PlayerEvent)event).getPlayer().getDisplayName();
            message = message.replaceAll("%\\(player\\)", playerName);
        }
        return message;
    }
}
