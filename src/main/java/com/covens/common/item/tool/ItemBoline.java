package com.covens.common.item.tool;

import com.covens.client.core.IModelRegister;
import com.covens.client.handler.ModelHandler;
import com.covens.common.core.statics.ModCreativeTabs;
import com.covens.common.lib.LibItemName;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * This class was created by BerciTheBeast on 27.3.2017.
 * It's distributed as part of Covens under
 * the MIT license.
 */
public class ItemBoline extends ItemShears implements IModelRegister {

	@Nonnull
	public ItemBoline() {
		super();
		setMaxDamage(600);
		this.setMaxStackSize(1);
		setRegistryName(LibItemName.BOLINE);
		setTranslationKey(LibItemName.BOLINE);
		setCreativeTab(ModCreativeTabs.ITEMS_CREATIVE_TAB);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
		if (!target.world.isRemote) {
			if (attacker instanceof EntityPlayer)
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 5);
			stack.damageItem(1, attacker);
		}
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("biome_id")) {
			tooltip.add(Biome.getBiome(stack.getTagCompound().getInteger("biome_id")).getBiomeName());
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = stack.getTagCompound();
		Biome b = worldIn.getBiome(playerIn.getPosition());
		tag.setInteger("biome_id", Biome.getIdForBiome(b));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}
}
