package net.minefortune.fakeop.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import net.minefortune.fakeop.util.GhostInventoryHolder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class FakeItemListener implements Listener {
    private int mapGuiSlotToNmsSlot(int guiSlot) {
        if (guiSlot >= 0 && guiSlot <= 35) {
            return guiSlot + 9;
        }
        return -1;
    }

    public void sendFakeItem(Player player, int slot, ItemStack stack) {
        try {
            PacketContainer packet = ProtocolLibrary.getProtocolManager()
                    .createPacket(PacketType.Play.Server.SET_SLOT);

            packet.getIntegers().write(0, 0);
            packet.getIntegers().write(1, 0);
            packet.getIntegers().write(2, slot);

            packet.getItemModifier().write(0, stack);

            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals(ChatColor.RED + "Click an empty slot you want to put a ghost item!")) {
            return; // not our menu
        }

        e.setCancelled(true);
        if (e.getClickedInventory() != null && e.getClickedInventory() == e.getView().getTopInventory()) {
            if (e.getView().getTopInventory().getHolder() instanceof GhostInventoryHolder holder) {
                int clickedSlot = e.getSlot();

                Player target = holder.target();
                int nmsSlot = mapGuiSlotToNmsSlot(clickedSlot);
                if (nmsSlot == -1) {
                    return;
                }
                sendFakeItem(target, nmsSlot, holder.stack());

                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Placed ghost item in slot " + clickedSlot + " of " + target.getName());
            }
        } else {
            e.getWhoClicked().sendMessage(ChatColor.RED + "Please click a slot in the ghost GUI!");
        }

        e.getWhoClicked().closeInventory();
    }
}
