package me.dreamdevs.github.edi.api.menu;

import me.dreamdevs.github.edi.api.events.ClickInventoryEvent;
import org.bukkit.Material;

public class StaticMenuItem extends MenuItem {

    public StaticMenuItem() {
        super((Material.getMaterial("WHITE_STAINED_GLASS_PANE") != null) ? Material.WHITE_STAINED_GLASS_PANE : Material.getMaterial("STAINED_GLASS_PANE"), " ");
    }

    @Override
    public void performAction(ClickInventoryEvent event) {
        // DO NOTHING
    }

}