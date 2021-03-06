package com.covens.common.block.tiles;

import static net.minecraft.block.BlockHorizontal.FACING;

import com.covens.common.block.BlockMod;
import com.covens.common.lib.LibBlockName;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Joseph on 9/16/2018.
 */

public class BlockBrazier extends BlockMod {

	private static final PropertyBool HANGING = PropertyBool.create("hanging");
	private static final AxisAlignedBB BBOX_STANDING = new AxisAlignedBB(0.15625, 0, 0.15625, 0.84375, 1 + (1d / 32d), 0.84375);
	private static final AxisAlignedBB BBOX_HANGING = new AxisAlignedBB(0.15625, -1d / 32d, 0.15625, 0.84375, 1, 0.84375);

	public BlockBrazier() {
		super(LibBlockName.BRAZIER, Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HANGING, false));
		this.setSoundType(SoundType.METAL);
		this.setResistance(3F);
		this.setHardness(3F);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return state.getValue(HANGING) ? BBOX_HANGING : BBOX_STANDING;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		final EnumFacing facing = EnumFacing.byHorizontalIndex(meta & 0b11);
		final boolean hanging = ((meta >> 2) & 1) == 1;
		return this.getDefaultState().withProperty(FACING, facing).withProperty(HANGING, hanging);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		final EnumFacing facing = state.getValue(FACING);
		final int bit = state.getValue(HANGING) ? 1 : 0;
		return facing.getHorizontalIndex() | (bit << 2);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, HANGING);
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
		return super.canPlaceBlockOnSide(world, pos, side) && (((side == EnumFacing.DOWN) && this.canHangFromAbove(world, pos)) || ((side != EnumFacing.DOWN) && this.canSitBelow(world, pos)));
	}

	private boolean canHangFromAbove(World world, BlockPos pos) {
		BlockFaceShape fs = world.getBlockState(pos.up()).getBlockFaceShape(world, pos.up(), EnumFacing.DOWN);
		return (fs != BlockFaceShape.BOWL) && (fs != BlockFaceShape.UNDEFINED);
	}

	private boolean canSitBelow(World world, BlockPos pos) {
		return world.getBlockState(pos.down()).getBlockFaceShape(world, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, world, pos, blockIn, fromPos);
		if (!world.isRemote) {
			boolean hang = state.getValue(HANGING);
			if (hang && !this.canHangFromAbove(world, pos)) {
				if (this.canSitBelow(world, pos)) {
					world.setBlockState(pos, state.withProperty(HANGING, false), 3);
				} else {
					world.destroyBlock(pos, true);
				}
			} else if (!hang && !this.canSitBelow(world, pos)) {
				if (this.canHangFromAbove(world, pos)) {
					world.setBlockState(pos, state.withProperty(HANGING, true), 3);
				} else {
					world.destroyBlock(pos, true);
				}
			}
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		final EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw);
		final boolean hang = facing == EnumFacing.DOWN;
		return this.getDefaultState().withProperty(FACING, enumfacing.getOpposite()).withProperty(HANGING, hang);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
