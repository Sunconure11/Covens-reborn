package com.covens.common.block.misc;

import java.util.Random;

import com.covens.common.Covens;
import com.covens.common.block.BlockMod;
import com.covens.common.block.ModBlocks;
import com.covens.common.lib.LibBlockName;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class BlockLantern extends BlockMod {

	private static final AxisAlignedBB bounding_box = new AxisAlignedBB(0.2, 0, 0.2, 0.8, 0.9375, 0.8);

	private boolean lit;

	public BlockLantern(boolean lit) {
		super(lit ? LibBlockName.REVEALING_LANTERN : LibBlockName.LANTERN, Material.IRON);
		this.setHarvestLevel("pickaxe", 0);
		this.setLightOpacity(0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(Covens.COLOR, EnumDyeColor.WHITE));
		this.lit = lit;
		this.setHardness(1.5f);
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return this.lit ? 15 : 0;
	}

	@Override
	@SuppressWarnings("deprecation")
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return bounding_box;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (this.lit) {
			world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5, 0, 0, 0);
		}
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return (state.getValue(Covens.COLOR)).ordinal();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, state.getValue(Covens.COLOR).ordinal());
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, Covens.COLOR);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (int i = 0; i < EnumDyeColor.values().length; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Covens.COLOR, EnumDyeColor.values()[meta]);
	}

	@Override
	@SuppressWarnings("deprecation")
	public int getMetaFromState(IBlockState state) {
		return state.getValue(Covens.COLOR).ordinal();
	}

	@Override
	public void registerModel() {
		for (int i = 0; i < EnumDyeColor.values().length; i++) {
			ModelResourceLocation mrl = new ModelResourceLocation(ModBlocks.revealing_lantern.getRegistryName(), "inventory");
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, mrl);
		}
	}
}
