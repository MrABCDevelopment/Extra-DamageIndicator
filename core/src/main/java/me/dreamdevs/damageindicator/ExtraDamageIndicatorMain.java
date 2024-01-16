package me.dreamdevs.damageindicator;

import lombok.Getter;
import me.dreamdevs.damageindicator.api.Config;
import me.dreamdevs.damageindicator.api.DamageIndicatorApi;
import me.dreamdevs.damageindicator.api.Language;
import me.dreamdevs.damageindicator.api.utils.Util;
import me.dreamdevs.damageindicator.commands.CommandHandler;
import me.dreamdevs.damageindicator.listeners.EntityHealthChangeListeners;
import me.dreamdevs.damageindicator.managers.IndicatorManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public final class ExtraDamageIndicatorMain extends JavaPlugin {

	private static @Getter ExtraDamageIndicatorMain instance;
	private @Getter IndicatorManager indicatorManager;

	@Override
	public void onEnable() {
		instance = this;
		DamageIndicatorApi.loadApi(this);

		loadConfig();
		loadLanguage();

		this.indicatorManager = new IndicatorManager();
		new CommandHandler(this);
		getServer().getPluginManager().registerEvents(new EntityHealthChangeListeners(), this);

		new Metrics(this, 19021);

		if (Config.SETTINGS_USE_UPDATE_CHECKER.toBoolean()) {
			Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> new UpdateChecker(DamageIndicatorApi.plugin, 101443).getVersion(version -> {
				if (getDescription().getVersion().equals(version)) {
					Util.sendPluginMessage("");
					Util.sendPluginMessage("&aYour version is up to date!");
					Util.sendPluginMessage("&aYour version: " + getDescription().getVersion());
					Util.sendPluginMessage("");
				} else {
					Util.sendPluginMessage("");
					Util.sendPluginMessage("&aThere is new Extra-DamageIndicator version!");
					Util.sendPluginMessage("&aYour version: " + getDescription().getVersion());
					Util.sendPluginMessage("&aNew version: " + version);
					Util.sendPluginMessage("");
				}
			}), 10L, 20L * 600);
		}

	}

	public void loadConfig() {
		File config = new File(getDataFolder(), "config.yml");
		Util.tryCreateFile(config);

		YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
		Stream.of(Config.values()).filter(setting -> conf.getString(setting.getPath()) == null)
				.forEach(setting -> conf.set(setting.getPath(), setting.getDefault()));

		Config.setFile(conf);
		try {
			conf.save(config);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadLanguage() {
		File config = new File(getDataFolder(), "language.yml");
		Util.tryCreateFile(config);

		YamlConfiguration lang = YamlConfiguration.loadConfiguration(config);
		Stream.of(Language.values()).filter(setting -> lang.getString(setting.getPath()) == null)
				.forEach(setting -> lang.set(setting.getPath(), setting.getDefault()));

		Language.setFile(lang);
		try {
			lang.save(config);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}