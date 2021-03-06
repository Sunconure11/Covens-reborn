package com.covens.common.item.tool;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.covens.common.core.statics.ModCreativeTabs;
import com.covens.common.item.ModMaterials;
import com.covens.common.lib.LibItemName;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zabi.minecraft.minerva.client.blockmodels.IModelRegister;
import zabi.minecraft.minerva.client.blockmodels.ModelHandler;

/**
 * This class was created by BerciTheBeast on 27.3.2017. It's distributed as
 * part of Covens under the MIT license. Parts of the code were created by
 * Vazkii, for Botania.
 */
public class ItemSilverSword extends ItemSword implements IModelRegister {

	public ItemSilverSword() {
		super(ModMaterials.TOOL_SILVER);
		this.setMaxStackSize(1);
		this.setRegistryName(LibItemName.SILVER_SWORD);
		this.setTranslationKey(LibItemName.SILVER_SWORD);
		this.setCreativeTab(ModCreativeTabs.ITEMS_CREATIVE_TAB);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
		if (!target.world.isRemote) {
			if ((target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) && (attacker instanceof EntityPlayer)) {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 12);
				stack.damageItem(25, attacker);
			} else {
				stack.damageItem(1, attacker);
			}
		}

		return true;
	}

	public String getNameInefficiently(ItemStack stack) {
		return this.getTranslationKey().substring(5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.GRAY + I18n.format("witch.tooltip." + this.getNameInefficiently(stack) + "_description.name"));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}
}
