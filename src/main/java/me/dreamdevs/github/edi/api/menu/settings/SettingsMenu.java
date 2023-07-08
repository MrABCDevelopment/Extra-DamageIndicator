package me.dreamdevs.github.edi.api.menu.settings;

import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.api.events.ClickInventoryEvent;
import me.dreamdevs.github.edi.api.menu.Menu;
import me.dreamdevs.github.edi.api.menu.MenuItem;
import me.dreamdevs.github.edi.api.menu.MenuSize;
import me.dreamdevs.github.edi.api.menu.StaticMenuItem;
import me.dreamdevs.github.edi.utils.ColourUtil;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.ClickType;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SettingsMenu extends Menu {

    public SettingsMenu() {
        super(EDIMain.getInstance().getSettingsManager().getMessage("settings-menu-title"), MenuSize.FOUR_ROWS);

        fill(new StaticMenuItem());

        setItem(10, new MenuItem(Material.ARMOR_STAND, "&cHologram Stay", ColourUtil.colouredLore("", "&7This represents the amount of time (in seconds)", "&7that holograms will stay visible.", "", "&7Currently, it is set to: &e"+EDIMain.getInstance().getSettingsManager().getValueAsInt("hologram-stay"), "", "&eLeft-click to add 1 second.", "&eRight-click to remove 1 second.")) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                int newValue = EDIMain.getInstance().getSettingsManager().getValueAsInt("hologram-stay");
                if(event.getClickType() == ClickType.LEFT)
                    newValue++;
                if(event.getClickType() == ClickType.RIGHT)
                    if(EDIMain.getInstance().getSettingsManager().getValueAsInt("hologram-stay") >= 1)
                        newValue--;
                EDIMain.getInstance().getSettingsManager().getSettings().put("hologram-stay", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(11, new MenuItem(Material.BOOK, "&cUpdate Checker", ColourUtil.colouredLore("", "&7Turn on/off notifications about new updates", "&7to &cExtra-DamageIndicator&7.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("update-checker") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("update-checker");
                EDIMain.getInstance().getSettingsManager().getSettings().put("update-checker", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(12, new MenuItem(Material.SLIME_BALL, "&cShow while sneaking", ColourUtil.colouredLore("", "&7Turn on/off hologram indicators", "&7while player is sneaking.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-sneaking") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-sneaking");
                EDIMain.getInstance().getSettingsManager().getSettings().put("show-while-sneaking", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(13, new MenuItem(Material.GRASS_BLOCK, "&cShow when fell from height", ColourUtil.colouredLore("", "&7Turn on/off hologram indicators", "&7when entity fell from height.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-when-fell-from-height") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-when-fell-from-height");
                EDIMain.getInstance().getSettingsManager().getSettings().put("show-when-fell-from-height", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(14, new MenuItem(Material.FIRE_CHARGE, "&cShow while burning", ColourUtil.colouredLore("", "&7Turn on/off hologram indicators", "&7while entity is burning.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-burning") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-burning");
                EDIMain.getInstance().getSettingsManager().getSettings().put("show-while-burning", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(15, new MenuItem(Material.DRAGON_BREATH, "&cShow while in dragon breath", ColourUtil.colouredLore("", "&7Turn on/off hologram indicators", "&7while entity is in dragon breath.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-in-dragon-breath") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-in-dragon-breath");
                EDIMain.getInstance().getSettingsManager().getSettings().put("show-while-in-dragon-breath", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(16, new MenuItem(Material.POTION, "&cShow while has poison", ColourUtil.colouredLore("", "&7Turn on/off hologram indicators", "&7while entity has poison.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-has-poison") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-in-dragon-breath");
                EDIMain.getInstance().getSettingsManager().getSettings().put("show-while-has-poison", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(19, new MenuItem(Material.VILLAGER_SPAWN_EGG, "&cShow NPC Health Changes", ColourUtil.colouredLore("", "&7Turn on/off hologram indicators", "&7of health changes in Citizen NPCs.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-npc-health-changes") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-npc-health-changes");
                EDIMain.getInstance().getSettingsManager().getSettings().put("show-npc-health-changes", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(20, new MenuItem(Material.PAPER, "&cShow Action Bar", ColourUtil.colouredLore("", "&7Turn on/off action bars", "&7that are shown to the players.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-action-bar") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-action-bar");
                EDIMain.getInstance().getSettingsManager().getSettings().put("show-action-bar", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(21, new MenuItem(Material.ZOMBIE_HEAD, "&cHostile", ColourUtil.colouredLore("", "&7Turn on/off displaying hologram indicators", "&7when hostile creatures take damage.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("hostile") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("hostile");
                EDIMain.getInstance().getSettingsManager().getSettings().put("hostile", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(22, new MenuItem(Material.PIG_SPAWN_EGG, "&cAnimals", ColourUtil.colouredLore("", "&7Turn on/off displaying hologram indicators", "&7when animals take damage.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("animals") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("animals");
                EDIMain.getInstance().getSettingsManager().getSettings().put("animals", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(23, new MenuItem(Material.PLAYER_HEAD, "&cPlayers", ColourUtil.colouredLore("", "&7Turn on/off displaying hologram indicators", "&7when players take damage.", "", "&7Currently it is turned "+((EDIMain.getInstance().getSettingsManager().getValueAsBoolean("players") ? "&aon" : "&coff")))) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                boolean newValue = !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("players");
                EDIMain.getInstance().getSettingsManager().getSettings().put("players", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(24, new MenuItem(Material.FEATHER, "&cY-coord addition", ColourUtil.colouredLore("", "&7This represents the additional y-coordinates,", "&7that hologram indicators will spawn.", "", "&7Currently, it is set to "+EDIMain.getInstance().getSettingsManager().getValueAsDouble("y-coord-addition"), "", "&eLeft-click to add 1 y-coord.", "&eRight-click to remove 1 y-coord.")) {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();
                double newValue = EDIMain.getInstance().getSettingsManager().getValueAsDouble("y-coord-addition");
                if(event.getClickType() == ClickType.LEFT)
                    newValue++;
                if(event.getClickType() == ClickType.RIGHT)
                    newValue--;
                EDIMain.getInstance().getSettingsManager().getSettings().put("y-coord-addition", newValue);
                new SettingsMenu().open(event.getPlayer());
            }
        });

        setItem(25, new MenuItem(Material.EMERALD, "&cSave all settings") {
            @Override
            public void performAction(ClickInventoryEvent event) {
                event.getPlayer().closeInventory();

                FileConfiguration config = EDIMain.getInstance().getConfig();
                ConfigurationSection section = config.getConfigurationSection("settings");

                for(Map.Entry<String, Object> entry : EDIMain.getInstance().getSettingsManager().getSettings().entrySet()) {
                    if(section.isSet(entry.getKey())) {
                        section.set(entry.getKey(), entry.getValue());
                    }
                }

                try {
                    File configFile = new File(EDIMain.getInstance().getDataFolder(), "config.yml");
                    config.save(configFile);
                } catch (IOException e) {}
            }
        });
    }
}