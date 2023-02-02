package org.ww.adt.cli;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ww.adt.core.ConfigController;

public class CommandDevel implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            ((Player)sender).sendMessage(
                "[Aabernathy]" + Color.WHITE + "Current State: DebugMode: " +
                Color.RED + ConfigController.debugMode() + Color.WHITE);
        }
        return true;
    }
}
