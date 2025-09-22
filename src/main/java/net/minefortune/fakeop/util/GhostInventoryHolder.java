package net.minefortune.fakeop.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public record GhostInventoryHolder(Player target, ItemStack stack) implements InventoryHolder {

    @Override
    public Inventory getInventory() {
        return null; // not used
    }
}
