package org.ww.adt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender cmdSender, Command cmd, String str, String[] strings)
    {
        if (!(cmdSender instanceof Player))
        {
            return true;
        }
        return true;
    }
}
