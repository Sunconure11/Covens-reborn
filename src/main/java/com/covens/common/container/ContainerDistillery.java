package com.covens.common.container;

import com.covens.common.container.slots.ModSlot;
import com.covens.common.container.slots.SlotFiltered;
import com.covens.common.container.slots.SlotOutput;
import com.covens.common.tile.tiles.TileEntityDistillery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerDistillery extends ModContainer<TileEntityDistillery> {

	public int progress = 0;
	public int totalTime = 1;
	public int burnTime = 0;

	public ContainerDistillery(InventoryPlayer playerInventory, TileEntityDistillery tileEntity) {
		super(tileEntity);
		IItemHandler c = tileEntity.getGuiHandler();
		for (int i = 0; i < 3; i++) {
			this.addSlotToContainer(new ModSlot<>(tileEntity, c, 0 + i * 4, 18, 18 * (i + 1) - 1));
			this.addSlotToContainer(new SlotOutput<>(tileEntity, c, 1 + i * 4, 124, 18 * (i + 1) - 1));
			this.addSlotToContainer(new ModSlot<>(tileEntity, c, 2 + i * 4, 36, 18 * (i + 1) - 1));
			this.addSlotToContainer(new SlotOutput<>(tileEntity, c, 3 + i * 4, 142, 18 * (i + 1) - 1));
		}

		this.addSlotToContainer(new SlotFiltered<>(tileEntity, c, 12, 80, 58, is -> is.getItem() == Items.BLAZE_POWDER));
		this.addPlayerSlots(playerInventory);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
//		Slot slot = inventorySlots.get(index);
//		if (slot != null && slot.getHasStack()) {
//			ItemStack itemstack1 = slot.getStack();
//			itemstack = itemstack1.copy();
//			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size() - 2;
//			if (index < containerSlots) {
//				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
//					return ItemStack.EMPTY;
//				}
//			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
//				return ItemStack.EMPTY;
//			}
//			if (itemstack1.getCount() == 0) {
//				slot.putStack(ItemStack.EMPTY);
//			} else {
//				slot.onSlotChanged();
//			}
//			if (itemstack1.getCount() == itemstack.getCount()) {
//				return ItemStack.EMPTY;
//			}
//			slot.onTake(player, itemstack1);
//		}
		return itemstack;
	}

	@Override
	public int getFieldFromTile(int id) {
		if (id == 0) {
			return this.getTileEntity().getStartingTime() - this.getTileEntity().getProgress();
		} else if (id == 1) {
			return this.getTileEntity().getStartingTime();
		} else if (id == 2) {
			return this.getTileEntity().getHeat();
		}
		return -1;
	}

	@Override
	protected int getFieldsToSync() {
		return 3;
	}

	@Override
	protected void onFieldUpdated(int id, int data) {
		switch (id) {
			case 0:
				progress = data;
				break;
			case 1:
				totalTime = (data==0?-1:data);
				break;
			case 2:
				burnTime = data;
				break;
			default:
				break;
		}
	}
}