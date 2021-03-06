package com.covens.api.event;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;

public class AltarUpgradeController {

	private BlockPos[] lockedTypes;

	public AltarUpgradeController() {
		this.lockedTypes = new BlockPos[AltarUpgradeController.EnumUpgradeClass.values().length];
	}

	public void use(AltarUpgradeController.EnumUpgradeClass obj, BlockPos pos) {
		if (this.lockedTypes[obj.ordinal()] == null) {
			this.lockedTypes[obj.ordinal()] = pos;
		}
	}

	@Nullable
	public BlockPos[] getModifierPositions() {
		return this.lockedTypes.clone();
	}

	public static enum EnumUpgradeClass {
		CUPS, // Container shaped objects (+ their content), boost multiplier
		WANDS, // For now light sources, boost gain. Should be expanded to better represent
				// wands
		PENTACLES, // Anything generally magical. Boost both gain and multiplier
		SWORDS // Ceremonial or magical swords and knives, they change unique features of the
				// altar
	}

}
