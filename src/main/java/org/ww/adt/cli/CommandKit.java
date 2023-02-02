package org.ww.adt.cli;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandKit implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player)sender;
            ItemStack[] iStacks = {
                new ItemStack(Material.COOKED_BEEF, 12),
                new ItemStack(Material.WOODEN_AXE),
                new ItemStack(Material.WRITABLE_BOOK)
            };

            player.getInventory().addItem(iStacks);
            player.sendMessage("[Aabernathy] sent " + player.getName() + " starting equipment.");
        }

        return true;
    }
}
