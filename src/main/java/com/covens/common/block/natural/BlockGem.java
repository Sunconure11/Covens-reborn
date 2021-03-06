package com.covens.common.block.natural;

import static com.covens.common.core.statics.ModCreativeTabs.BLOCKS_CREATIVE_TAB;

import javax.annotation.Nonnull;

import com.covens.common.block.BlockMod;
import com.covens.common.lib.LibBlockName;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import thaumcraft.api.crafting.IInfusionStabiliserExt;
import zabi.minecraft.minerva.client.blockmodels.ModelHandler;

@Optional.Interface(iface = "thaumcraft.api.crafting.IInfusionStabiliserExt", modid = "thaumcraft")
public class BlockGem extends BlockMod implements IInfusionStabiliserExt {
	public static final PropertyEnum<BlockGem.Gem> GEM = PropertyEnum.create("gem", BlockGem.Gem.class);

	public BlockGem() {
		super(LibBlockName.GEM_BLOCK, Material.ROCK);
		this.setHardness(5.0F);
		this.setCreativeTab(BLOCKS_CREATIVE_TAB);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(GEM, BlockGem.Gem.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(GEM).ordinal();
	}

	@Override
	public void getSubBlocks(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
		for (int i = 0; i < BlockGem.Gem.values().length; ++i) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, GEM);
	}

	@Override
	public void registerModel() {
		BlockGem.Gem[] values = BlockGem.Gem.values();
		for (int i = 0; i < values.length; i++) {
			BlockGem.Gem gem = values[i];
			ModelHandler.registerForgeModel(this, i, "gem=" + gem.getName());
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(GEM).ordinal();
	}

	@Optional.Method(modid = "thaumcraft")
	@Override
	public boolean canStabaliseInfusion(World world, BlockPos blockPos) {
		return true;
	}

	@Override
	@Optional.Method(modid = "thaumcraft")
	public float getStabilizationAmount(World world, BlockPos pos) {
		return 0;
	}

	public enum Gem implements IStringSerializable {
		GARNET, NUUMMITE, TIGERS_EYE, TOURMALINE, BLOODSTONE, JASPER, MALACHITE, AMETHYST, ALEXANDRITE;

		@Override
		public String getName() {
			return this.name().toLowerCase();
		}
	}
}
