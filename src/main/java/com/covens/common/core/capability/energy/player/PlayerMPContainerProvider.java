package com.covens.common.core.capability.energy.player;

import com.covens.api.mp.IMagicPowerContainer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PlayerMPContainerProvider implements ICapabilitySerializable<NBTBase> {

	private IMagicPowerContainer container = new PlayerMPContainer();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == IMagicPowerContainer.CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == IMagicPowerContainer.CAPABILITY) {
			return IMagicPowerContainer.CAPABILITY.cast(this.container);
		}
		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return IMagicPowerContainer.CAPABILITY.getStorage().writeNBT(IMagicPowerContainer.CAPABILITY, this.container, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		IMagicPowerContainer.CAPABILITY.getStorage().readNBT(IMagicPowerContainer.CAPABILITY, this.container, null, nbt);
	}

}
