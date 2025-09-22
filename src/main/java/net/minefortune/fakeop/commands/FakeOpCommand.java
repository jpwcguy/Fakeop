package net.minefortune.fakeop.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FakeOpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;
        Player player = Bukkit.getPlayer(args[0]);
        if (player != null) {
            if (args.length == 2) {
                if (args[1].equalsIgnoreCase("-v")) {
                    Bukkit.getOnlinePlayers().forEach(onlineplayer -> onlineplayer.sendMessage(ChatColor.WHITE + "Made " + player.getName() + " a server operator"));
                } else return false;
            } else {
                player.sendMessage(ChatColor.WHITE + "Made " + player.getName() + " a server operator");
                if (!player.getName().equals(sender.getName()))
                    sender.sendMessage(ChatColor.WHITE + "Made " + player.getName() + " a server operator" + ChatColor.DARK_PURPLE + " (Sent to player " + player.getName() + ")");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Player not found.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach((player) -> list.add(player.getName()));
            return list;
        } else if (args.length == 2) {
            return List.of("-v");
        }
        return List.of();
    }
}
