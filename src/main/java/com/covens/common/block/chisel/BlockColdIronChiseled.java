package com.covens.common.block.chisel;

import com.covens.common.block.BlockMod;
import com.covens.common.lib.LibBlockName;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import zabi.minecraft.minerva.client.blockmodels.ModelHandler;

public class BlockColdIronChiseled extends BlockMod {
	public static final PropertyEnum<BlockColdIronVariant> VARIANT = PropertyEnum.create("variant", BlockColdIronVariant.class);

	public BlockColdIronChiseled(Material material, SoundType sound) {
		super(LibBlockName.COLD_IRON_BLOCK + "_chisel", material, sound);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, BlockColdIronVariant.values()[meta]);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (int i = 0; i < BlockColdIronVariant.values().length; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return this.getMetaFromState(state);
	}

	@Override
	public void registerModel() {
		for (BlockColdIronChiseled.BlockColdIronVariant v : BlockColdIronChiseled.BlockColdIronVariant.values()) {
			ModelHandler.registerForgeModel(this, v.ordinal(), "variant=" + v.getName());
		}
	}

	public static enum BlockColdIronVariant implements IStringSerializable {

		SYMBOL, SUN, MOON, CUP, WAND, SWORD, PENTACLE, PENTAGRAM, BEVEL;

		@Override
		public String getName() {
			return this.name().toLowerCase();
		}
	}
}
