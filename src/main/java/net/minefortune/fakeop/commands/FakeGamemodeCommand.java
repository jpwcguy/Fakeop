package net.minefortune.fakeop.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FakeGamemodeCommand implements CommandExecutor, TabCompleter {
    @Deprecated // it's not doe
    public void sendFakeGamemode(Player player, GameMode gamemode) {
        try {
            PacketContainer packet = ProtocolLibrary.getProtocolManager()
                    .createPacket(PacketType.Play.Server.GAME_STATE_CHANGE);
            packet.getGameStateIDs().write(0, 3);
            packet.getFloat().write(0, (float) gamemode.getValue());
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated // it's not doe
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) return false;

        GameMode gameMode;
        try {
            gameMode = GameMode.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + "Please enter a valid gamemode.");
            return true;
        }
        Player player;
        if (args.length == 1) {
            player = sender instanceof Player ? (Player) sender : null;
        } else {
            player = Bukkit.getPlayer(args[1]);
        }

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        sendFakeGamemode(player, gameMode);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.sendMessage(ChatColor.WHITE + "Set your game mode to " + gameMode.name() + " Mode");
        if (!player.equals(sender)) {
            sender.sendMessage(ChatColor.WHITE + "Set " + player.getName() + " game mode to " + gameMode.name() + " Mode" + ChatColor.DARK_PURPLE + " (Sent to player " + player.getName() + ")");
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return List.of("adventure", "creative", "spectator", "survival");
        } else if (args.length == 2) {
            List<String> list = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach((player) -> list.add(player.getName()));
            return list;
        }
        return List.of();
    }
}
