package com.covens.common.item.equipment.baubles;

import java.util.List;

import javax.annotation.Nullable;

import com.covens.common.content.enchantments.BaublesEnchantment;
import com.covens.common.core.statics.ModCreativeTabs;
import com.covens.common.item.ItemMod;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTalisman extends ItemMod implements IBauble {

	private int enchantability = 0;
	private BaubleType type = BaubleType.TRINKET;

	public ItemTalisman(BaubleType type, int enchantability, String id) {
		super(id);
		this.setCreativeTab(ModCreativeTabs.ITEMS_CREATIVE_TAB);
		this.enchantability = enchantability;
		this.setMaxStackSize(1);
		this.type = type;
	}

	@Override
	public boolean isDamageable() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		if (!stack.isItemEnchanted()) {
			tooltip.add(TextFormatting.AQUA + I18n.format("witch.tooltip.talismans_description.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			for (int i = 0; i < baubles.getSlots(); i++) {
				if (baubles.getStackInSlot(i).isEmpty() && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
					baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
					if (!player.capabilities.isCreativeMode) {
						player.setHeldItem(hand, ItemStack.EMPTY);
					}
					this.onEquipped(player.getHeldItem(hand), player);
					break;
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public int getItemEnchantability(ItemStack stack) {
		return this.enchantability;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return !EnchantmentHelper.hasBindingCurse(itemstack);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return this.type;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.BINDING_CURSE;
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		EnchantmentHelper.getEnchantments(itemstack).keySet().parallelStream().filter(e -> e instanceof BaublesEnchantment).map(e -> (BaublesEnchantment) e).forEach(e -> e.onUnequipped((EntityPlayer) player));
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		EnchantmentHelper.getEnchantments(itemstack).keySet().parallelStream().filter(e -> e instanceof BaublesEnchantment).map(e -> (BaublesEnchantment) e).forEach(e -> e.onEquipped((EntityPlayer) player));
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		EnchantmentHelper.getEnchantments(itemstack).keySet().parallelStream().filter(e -> e instanceof BaublesEnchantment).map(e -> (BaublesEnchantment) e).forEach(e -> e.onWornTick((EntityPlayer) player));
	}

}
