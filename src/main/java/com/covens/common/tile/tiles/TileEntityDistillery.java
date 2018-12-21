package com.covens.common.tile.tiles;

import java.util.ArrayList;
import java.util.List;

import com.covens.api.mp.IMagicPowerConsumer;
import com.covens.common.Covens;
import com.covens.common.crafting.DistilleryRecipe;
import com.covens.common.crafting.ModDistilleryRecipes;
import com.covens.common.lib.LibGui;
import com.covens.common.tile.ModTileEntity;
import com.covens.common.tile.util.JointInventoryWrapper;
import com.covens.common.tile.util.JointInventoryWrapper.Mode;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityDistillery extends ModTileEntity implements ITickable {

	public static final int BURN_TIME = 1200;

	private IMagicPowerConsumer mp = IMagicPowerConsumer.CAPABILITY.getDefaultInstance();
	private int progress = 0;
	private int heat = 0;
	private String currentRecipe = "";
	private int startingProgress = -1;
	private ItemStackHandler fuelInventory = new ItemStackHandler(1) {
		
		@Override
		protected void onContentsChanged(int slot) {
			markDirty();
		}

		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			if (stack.getItem() != Items.BLAZE_POWDER) {
				return stack;
			}
			return super.insertItem(slot, stack, simulate);
		}
	};
	
	private ItemStackHandler inventoryOutput = new ItemStackHandler(6) {
		@Override
		protected void onContentsChanged(int slot) {
			contentsChanged();
		}
	};
	private ItemStackHandler inventoryInput = new ItemStackHandler(6) {
		@Override
		protected void onContentsChanged(int slot) {
			contentsChanged();
		}
	};
	
	private JointInventoryWrapper ioSideWrapper = new JointInventoryWrapper();
	private JointInventoryWrapper ioGuiWrapper = new JointInventoryWrapper();
	private JointInventoryWrapper ioBackWrapper = new JointInventoryWrapper();

	public TileEntityDistillery() {
		for (int i = 0; i < 6; i++) {
			ioSideWrapper.bind(() -> inventoryInput, i, Mode.INSERT);
			ioSideWrapper.bind(() -> inventoryOutput, i, Mode.EXTRACT);
			ioGuiWrapper.bind(() -> inventoryInput, i, Mode.BOTH);
			ioGuiWrapper.bind(() -> inventoryOutput, i, Mode.EXTRACT);
		}
		ioGuiWrapper.bind(() -> fuelInventory, 0, Mode.BOTH);
		ioBackWrapper.bind(() -> fuelInventory, 0, Mode.INSERT);
	}

	@Override
	public void update() {
		if (heat > 0) {
			heat--;
			markDirty();
		}
		if (currentRecipe.length() > 0) {
			if (heat == 0) {
				burnFuel();
			}
			if (heat > 0) {
				if (progress > 0) {
					if (mp.drainAltarFirst(world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, false), getPos(), this.world.provider.getDimension(), 2)) {
						progress--;
						markDirty();
					}
				} else {
					DistilleryRecipe recipe = ModDistilleryRecipes.REGISTRY.getValue(new ResourceLocation(currentRecipe));
					if (recipe == null) {
						currentRecipe = "";
						progress = 0;
						startingProgress = -1;
					} else {
						for (int i = 0; i < inventoryInput.getSlots(); i++) {
							if (!inventoryInput.getStackInSlot(i).isEmpty()) {
								inventoryInput.extractItem(i, 1, false);
							}
						}

						for (ItemStack is : recipe.getOutputs()) {
							ItemStack remaining = is.copy();
							for (int i = 0; i < inventoryOutput.getSlots() && !remaining.isEmpty(); i++) {
								remaining = inventoryOutput.insertItem(i, remaining, false);
							}
						}
						progress = startingProgress;
						checkRecipe();
					}
				}
				markDirty();
			}
		}
	}

	private void burnFuel() {
		if (!fuelInventory.extractItem(0, 1, false).isEmpty()) {
			heat = BURN_TIME;
		}
	}


	protected void contentsChanged() {
		if (!world.isRemote) {
			checkRecipe();
			this.markDirty();
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.openGui(Covens.instance, LibGui.DISTILLERY.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	private void checkRecipe() {
		DistilleryRecipe recipe = ModDistilleryRecipes.REGISTRY.getValuesCollection().parallelStream()
				.filter(dr -> dr.matches(asList(inventoryInput)))
				.findFirst().orElse(null);
		if (recipe == null) {
			currentRecipe = "";
			progress = 0;
			startingProgress = -1;
		} else if (!currentRecipe.equals(recipe.getRegistryName().toString()) && canOutputFit(recipe)) {
			currentRecipe = recipe.getRegistryName().toString();
			progress = recipe.getTime();
			startingProgress = recipe.getTime();
		}
	}

	private List<ItemStack> asList(ItemStackHandler inv) {
		ArrayList<ItemStack> list = Lists.newArrayList();
		for (int i = 0; i < inv.getSlots(); i++) {
			list.add(inv.getStackInSlot(i));
		}
		return list;
	}

	private boolean canOutputFit(DistilleryRecipe recipe) {
		ItemStackHandler simulated = new ItemStackHandler(6);
		for (int i = 0; i < inventoryOutput.getSlots(); i++) {
			simulated.setStackInSlot(i, inventoryOutput.getStackInSlot(i).copy());
		}
		for (ItemStack is : recipe.getOutputs()) {
			ItemStack remaining = is.copy();
			for (int i = 0; i < simulated.getSlots() && !remaining.isEmpty(); i++) {
				remaining = simulated.insertItem(i, remaining, false);
			}
			if (!remaining.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public ItemStackHandler getInputInventory() {
		return inventoryInput;
	}

	public ItemStackHandler getOutputInventory() {
		return inventoryOutput;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		if (capability == IMagicPowerConsumer.CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == world.getBlockState(pos).getValue(BlockHorizontal.FACING)) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(ioBackWrapper);
			}
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(ioSideWrapper);
		}
		if (capability == IMagicPowerConsumer.CAPABILITY) {
			return IMagicPowerConsumer.CAPABILITY.cast(mp);
		}
		return super.getCapability(capability, facing);
	}

	public IItemHandler getGuiHandler() {
		return ioGuiWrapper;
	}

	@Override
	protected void readAllModDataNBT(NBTTagCompound tag) {
		inventoryInput.deserializeNBT(tag.getCompoundTag("inv_in"));
		inventoryOutput.deserializeNBT(tag.getCompoundTag("inv_out"));
		fuelInventory.deserializeNBT(tag.getCompoundTag("fuel"));
		mp.readFromNbt(tag.getCompoundTag("mp"));
		progress = tag.getInteger("progress");
		currentRecipe = tag.getString("recipe");
		startingProgress = tag.getInteger("recipeTime");
	}

	@Override
	protected void writeAllModDataNBT(NBTTagCompound tag) {
		tag.setTag("inv_in", inventoryInput.serializeNBT());
		tag.setTag("inv_out", inventoryOutput.serializeNBT());
		tag.setTag("fuel", fuelInventory.serializeNBT());
		tag.setTag("mp", mp.writeToNbt());
		tag.setInteger("progress", progress);
		tag.setString("recipe", currentRecipe);
		tag.setInteger("recipeTime", startingProgress);
	}

	public int getProgress() {
		return progress;
	}

	@Override
	protected void writeModSyncDataNBT(NBTTagCompound tag) {
		//NO-OP
	}

	@Override
	protected void readModSyncDataNBT(NBTTagCompound tag) {
		//NO-OP
	}

	public int getStartingTime() {
		return startingProgress;
	}

	public int getHeat() {
		return heat;
	}

}
