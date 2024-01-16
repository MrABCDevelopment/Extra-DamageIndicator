package me.dreamdevs.damageindicator.managers;

import lombok.Getter;
import me.dreamdevs.damageindicator.api.indicators.IHologramIndicator;
import me.dreamdevs.damageindicator.api.utils.Util;
import me.dreamdevs.damageindicator.api.utils.VersionUtil;

public class IndicatorManager {

	private @Getter IHologramIndicator hologramIndicator;

	public IndicatorManager() {
		try {
			Class<? extends IHologramIndicator> clazz = Class.forName("me.dreamdevs.damageindicator.versions."+ VersionUtil.getVersion()+".HologramIndicator").asSubclass(IHologramIndicator.class);
			hologramIndicator = clazz.getDeclaredConstructor().newInstance();

			Util.sendPluginMessage("&aIndicatorManager hooked with correct version!");
		} catch (Exception e) {
			Util.sendPluginMessage("&cThis version is not supported!");
		}
	}

}