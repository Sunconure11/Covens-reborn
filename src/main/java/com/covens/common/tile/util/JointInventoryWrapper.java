package com.covens.common.tile.util;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.covens.common.core.helper.Log;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class JointInventoryWrapper implements IItemHandlerModifiable {

	// The index in the list is the outside slot, the integer is the wrapped handler
	// slot
	private ArrayList<Tuple<Supplier<ItemStackHandler>, Integer>> bindings = new ArrayList<>();
	private ArrayList<Mode> modes = new ArrayList<>();

	@Override
	public int getSlots() {
		return this.bindings.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if ((slot >= this.bindings.size()) || (this.bindings.get(slot) == null)) {
			return ItemStack.EMPTY;
		}
		Tuple<Supplier<ItemStackHandler>, Integer> res = this.bindings.get(slot);
		return res.getFirst().get().getStackInSlot(res.getSecond());
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if ((slot >= this.bindings.size()) || (this.bindings.get(slot) == null) || !this.modes.get(slot).canInsert()) {
			return stack;
		}
		Tuple<Supplier<ItemStackHandler>, Integer> res = this.bindings.get(slot);
		return res.getFirst().get().insertItem(res.getSecond(), stack, simulate);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if ((slot >= this.bindings.size()) || (this.bindings.get(slot) == null) || !this.modes.get(slot).canExtract()) {
			return ItemStack.EMPTY;
		}
		Tuple<Supplier<ItemStackHandler>, Integer> res = this.bindings.get(slot);
		return res.getFirst().get().extractItem(res.getSecond(), amount, simulate);
	}

	@Override
	public int getSlotLimit(int slot) {
		if ((slot >= this.bindings.size()) || (this.bindings.get(slot) == null)) {
			return 0;
		}
		Tuple<Supplier<ItemStackHandler>, Integer> res = this.bindings.get(slot);
		return res.getFirst().get().getSlotLimit(res.getSecond());
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		if ((slot >= this.bindings.size()) || (this.bindings.get(slot) == null)) {
			Log.w("Slot " + slot + " wasn't bound properly: " + stack);
		}
		Tuple<Supplier<ItemStackHandler>, Integer> res = this.bindings.get(slot);
		res.getFirst().get().setStackInSlot(res.getSecond(), stack);
	}

	public JointInventoryWrapper bind(Supplier<ItemStackHandler> handler, int slot, Mode mode) {
		this.bindings.add(new Tuple<Supplier<ItemStackHandler>, Integer>(handler, slot));
		this.modes.add(mode);
		return this;
	}

	public static enum Mode {

		INSERT(true, false), EXTRACT(false, true), BOTH(true, true), NONE(false, false);

		private boolean r_in, r_out;

		Mode(boolean in, boolean out) {
			this.r_in = in;
			this.r_out = out;
		}

		public boolean canInsert() {
			return this.r_in;
		}

		public boolean canExtract() {
			return this.r_out;
		}
	}
}
