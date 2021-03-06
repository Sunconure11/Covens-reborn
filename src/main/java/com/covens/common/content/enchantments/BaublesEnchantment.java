package com.covens.common.content.enchantments;

import java.util.ArrayList;
import java.util.List;

import com.covens.common.item.equipment.baubles.ItemTalisman;
import com.covens.common.lib.LibMod;
import com.google.common.collect.Lists;

import baubles.api.BaublesApi;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;

public class BaublesEnchantment extends Enchantment {

	private int maxLevel;

	protected BaublesEnchantment(String name, Rarity rarityIn, int maxLevelIn) {
		super(rarityIn, EnumEnchantmentType.WEARABLE, new EntityEquipmentSlot[0]);
		this.setRegistryName(new ResourceLocation(LibMod.MOD_ID, name));
		this.setName(name);
		this.maxLevel = maxLevelIn;
	}

	@Override
	public int getMaxLevel() {
		return this.maxLevel;
	}

	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof ItemTalisman;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return this.canApply(stack);
	}

	@Override
	public List<ItemStack> getEntityEquipment(EntityLivingBase entityIn) {
		if (entityIn instanceof EntityPlayer) {
			ArrayList<ItemStack> result = new ArrayList<>();
			IItemHandler ih = BaublesApi.getBaublesHandler((EntityPlayer) entityIn);
			for (int i = 0; i < ih.getSlots(); i++) {
				ItemStack is = ih.getStackInSlot(i);
				if (!is.isEmpty()) {
					result.add(is);
				}
			}
			return result;
		}
		return Lists.newArrayList();
	}

	public int getMaxLevelOnPlayer(EntityPlayer p) {
		return this.getEntityEquipment(p).parallelStream().mapToInt(is -> EnchantmentHelper.getEnchantmentLevel(this, is)).max().orElse(0);
	}

	public int getTotalLevelOnPlayer(EntityPlayer p) {
		return this.getEntityEquipment(p).parallelStream().mapToInt(is -> EnchantmentHelper.getEnchantmentLevel(this, is)).sum();
	}

	public void onWornTick(EntityPlayer player) {
		// Override this
	}

	public void onUnequipped(EntityPlayer player) {
		// Override this
	}

	public void onEquipped(EntityPlayer player) {
		// Override this
	}

}
