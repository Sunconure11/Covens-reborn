package com.covens.common.core.capability.mimic;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

public interface IMimicData {
	boolean isMimicking();

	void setMimicking(boolean mimicking, EntityPlayer player);

	UUID getMimickedPlayerID();

	void setMimickedPlayerID(UUID mimickedPlayerID);

	String getMimickedPlayerName();

	void setMimickedPlayerName(String mimickedPlayerName);

	/**
	 * Calling this won't call cleanup methods, and should only be used when
	 * restoring data from NBT Prefer the use of
	 * {@link IMimicData#setMimicking(boolean, EntityPlayer)}
	 */
	void setMimickingDirect(boolean mimicking);
}
