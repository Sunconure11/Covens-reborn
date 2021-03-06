package com.covens.common.content.ritual.rituals;

import com.covens.api.infusion.IInfusion;
import com.covens.api.mp.IMagicPowerContainer;
import com.covens.common.content.infusion.capability.InfusionCapability;
import com.covens.common.content.ritual.RitualImpl;
import com.covens.common.core.net.NetworkHandler;
import com.covens.common.core.net.messages.InfusionChangedMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualInfusion extends RitualImpl {

	private IInfusion type;

	public RitualInfusion(NonNullList<Ingredient> input, NonNullList<ItemStack> output, int timeInTicks, int circles, int altarStartingPower, int powerPerTick, IInfusion type) {
		super(type.getRegistryName(), input, output, timeInTicks, circles, altarStartingPower, powerPerTick);
		this.type = type;
	}

	@Override
	public void onFinish(EntityPlayer player, TileEntity tile, World world, BlockPos pos, NBTTagCompound data, BlockPos effectivePosition, int covenSize) {
		if (player == null) {
			return;
		}
		IMagicPowerContainer pc = player.getCapability(IMagicPowerContainer.CAPABILITY, null);
		pc.drain(pc.getAmount());
		player.getCapability(InfusionCapability.CAPABILITY, null).setType(this.type);
		if (player instanceof EntityPlayerMP) {
			NetworkHandler.HANDLER.sendTo(new InfusionChangedMessage(player), (EntityPlayerMP) player);
		}
	}

	@Override
	public boolean canBePerformedRemotely() {
		return false;
	}

}
