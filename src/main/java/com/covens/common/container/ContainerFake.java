package com.covens.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerFake extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
