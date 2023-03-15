package me.dreamdevs.github.edi;

import me.dreamdevs.github.edi.api.IHologram;
import org.bukkit.Bukkit;
public class HologramsHandler {

    private IHologram iHologram;
    private EDIMain plugin;

    public HologramsHandler(EDIMain plugin) {
        this.plugin = plugin;
        try {
            Class<? extends IHologram> hapi = null;
            if (Settings.supportedPlugins.get("HolographicDisplays") && Bukkit.getPluginManager().getPlugin("HolographicDisplays") != null) {
                hapi = Class.forName("me.dreamdevs.github.edi.holograms.HolographicDisplaysHolo").asSubclass(IHologram.class);
            } else if (Settings.supportedPlugins.get("DecentHolograms") && Bukkit.getPluginManager().getPlugin("DecentHolograms") != null) {
                hapi = Class.forName("me.dreamdevs.github.edi.holograms.DecentHologramsHolo").asSubclass(IHologram.class);
            } else {
                hapi = Class.forName("me.dreamdevs.github.edi.holograms.DefaultHolo").asSubclass(IHologram.class);
            }
            iHologram = hapi.newInstance();
        } catch (Exception e) {
        }
    }
    public IHologram getHologram() {
        return iHologram;
    }
}