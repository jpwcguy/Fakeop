package net.minefortune.fakeop.commands;

import net.minefortune.fakeop.util.GhostInventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class FakeItemCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;
        if (sender instanceof Player senderPlayer) {
            if (senderPlayer.getInventory().getItemInMainHand().getType() == Material.AIR) {
                senderPlayer.sendMessage(ChatColor.RED + "You need to hold an item in your hand to become a ghost item.");
                return true;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player != null) {
                Inventory viewerInv = Bukkit.createInventory(new GhostInventoryHolder(player, senderPlayer.getInventory().getItemInMainHand()), 36, ChatColor.RED + "Click an empty slot you want to put a ghost item!");
                updateInventoryView(player, viewerInv);
                senderPlayer.openInventory(viewerInv);
            } else {
                sender.sendMessage(ChatColor.RED + "Player not found.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Only players can run this command.");
        }
        return true;
    }

    public void updateInventoryView(Player target, Inventory viewerInv) {
        for (int i = 0; i < 9; i++) {
            viewerInv.setItem(i, target.getInventory().getItem(9 + i));
        }
        for (int i = 0; i < 9; i++) {
            viewerInv.setItem(9 + i, target.getInventory().getItem(18 + i));
        }
        for (int i = 0; i < 9; i++) {
            viewerInv.setItem(18 + i, target.getInventory().getItem(27 + i));
        }
        for (int i = 0; i < 9; i++) {
            viewerInv.setItem(27 + i, target.getInventory().getItem(i));
        }
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach((player) -> list.add(player.getName()));
            return list;
        }
        return List.of();
    }
}
