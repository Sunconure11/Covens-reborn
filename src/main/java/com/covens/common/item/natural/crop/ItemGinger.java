package com.covens.common.item.natural.crop;

import com.covens.common.core.statics.ModCreativeTabs;
import com.covens.common.lib.LibItemName;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * This class was created by Arekkuusu on 02/03/2017. It's distributed as part
 * of Covens under the MIT license.
 */
public class ItemGinger extends ItemCropFood {

	public ItemGinger() {
		super(LibItemName.GINGER, 4, 0.8F, false);
		this.setCreativeTab(ModCreativeTabs.PLANTS_CREATIVE_TAB);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		player.setFire(10);
	}
}
