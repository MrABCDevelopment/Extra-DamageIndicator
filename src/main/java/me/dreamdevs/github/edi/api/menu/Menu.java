package me.dreamdevs.github.edi.api.menu;

import lombok.Getter;
import me.dreamdevs.github.edi.listeners.InventoryListener;
import me.dreamdevs.github.edi.utils.ColourUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

@Getter
public class Menu implements InventoryHolder {

    private final String title;
    private final Inventory inventory;
    private int size;

    private MenuItem[] menuItems;

    public Menu(String title, MenuSize menuSize) {
        this.title = ColourUtil.colorize(title);
        this.size = menuSize.getSize();
        this.menuItems = new MenuItem[size];
        this.inventory = Bukkit.createInventory(this, size, this.title);
    }

    public void open(Player player) {
        InventoryListener.menus.put(player.getUniqueId(), this);
        player.openInventory(inventory);
    }

    public void setItem(int slot, MenuItem menuItem) {
        this.menuItems[slot] = menuItem;
        inventory.setItem(slot, menuItem.getItemStack());
    }

    public void fill(MenuItem menuItem) {
        for(int x = 0; x<size; x++) {
            setItem(x, menuItem);
        }
    }

    public void addItem(MenuItem menuItem) {
        for(int x = 0; x<size; x++) {
            if(menuItems[x] == null) {
                setItem(x, menuItem);
                break;
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}