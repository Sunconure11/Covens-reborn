package com.covens.common.content.incantation;

import java.util.HashMap;
import java.util.Map;

import com.covens.api.incantation.IIncantation;

/**
 * This class was created by Arekkuusu on 19/04/2017. It's distributed as part
 * of Covens under the MIT license.
 */
public final class ModIncantations {

	// Todo: Convert all of these into spells.

	private static final Map<String, IIncantation> commands = new HashMap<>();

	private ModIncantations() {
	}

	public static void init() {
		registerIncantation("medico", new IncantationHeal());
		registerIncantation("lux", new IncantationCandlelight());
		registerIncantation("tenebrae", new IncantationSnuff());
		registerIncantation("aqua", new IncantationFisheye());
		registerIncantation("ignis", new IncantationWitchFire());
	}

	public static void registerIncantation(String name, IIncantation incantation) {
		getCommands().put(name, incantation);
	}

	public static Map<String, IIncantation> getCommands() {
		return commands;
	}
}
