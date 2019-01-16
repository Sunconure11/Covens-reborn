package com.covens.common.block.tiles;

import static com.covens.api.state.StateProperties.HALF;
import static com.covens.api.state.StateProperties.MIRROR_VARIANTS;

import java.util.Random;

import com.covens.common.block.BlockModTileEntity;
import com.covens.common.block.ModBlocks;
import com.covens.common.lib.LibBlockName;
import com.covens.common.tile.tiles.TileEntityMagicMirror;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMagicMirror extends BlockModTileEntity {

	private static final AxisAlignedBB BOUNDING_BOX_NORTH = new AxisAlignedBB(0.0f, 0.0f, 13.0f / 16.0f, 1.0f, 1.0f, 1.0f);
	private static final AxisAlignedBB BOUNDING_BOX_SOUTH = new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f - (13.0f / 16.0f));
	private static final AxisAlignedBB BOUNDING_BOX_EAST = new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f - (13.0f / 16.0f), 1.0f, 1.0f);
	private static final AxisAlignedBB BOUNDING_BOX_WEST = new AxisAlignedBB(13.0f / 16.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);

	public BlockMagicMirror() {
		super(LibBlockName.MAGIC_MIRROR, Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(MIRROR_VARIANTS, 0).withProperty(BlockHorizontal.FACING, EnumFacing.NORTH).withProperty(HALF, true));
		this.setLightOpacity(0);
		this.setHardness(1f);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, MIRROR_VARIANTS, BlockHorizontal.FACING, HALF);
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing dir = EnumFacing.getDirectionFromEntityLiving(pos, placer);
		if ((dir == EnumFacing.DOWN) || (dir == EnumFacing.UP)) {
			dir = EnumFacing.fromAngle(placer.rotationYaw).getOpposite();
		}
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, dir);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (!worldIn.getBlockState(pos.up()).getBlock().isAir(worldIn.getBlockState(pos.up()), worldIn, pos)) {
			return false;
		}
		return super.canPlaceBlockAt(worldIn, pos);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// BitMap: xHFF, Half, Facing
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex() | (state.getValue(HALF) ? 4 : 0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(HALF, (meta & 4) == 4);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntityMagicMirror magicMirror = (TileEntityMagicMirror) worldIn.getTileEntity(pos);
		if (magicMirror != null) {
			return state.withProperty(MIRROR_VARIANTS, magicMirror.getShadeType());
		}
		return state;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return state.getValue(HALF);
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(BlockHorizontal.FACING);
		if (facing == EnumFacing.NORTH) {
			return BOUNDING_BOX_NORTH;
		} else if (facing == EnumFacing.SOUTH) {
			return BOUNDING_BOX_SOUTH;
		} else if (facing == EnumFacing.EAST) {
			return BOUNDING_BOX_EAST;
		} else if (facing == EnumFacing.WEST) {
			return BOUNDING_BOX_WEST;
		}
		return BOUNDING_BOX_NORTH;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos.up(), ModBlocks.magic_mirror.getDefaultState().withProperty(BlockHorizontal.FACING, state.getValue(BlockHorizontal.FACING)).withProperty(HALF, false));
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		IBlockState neighborState = world.getBlockState(fromPos);
		if (neighborState.getBlock() == ModBlocks.magic_mirror) {
			if ((neighborState.getValue(HALF) == state.getValue(HALF)) && (fromPos.getX() == pos.getX()) && (fromPos.getZ() == pos.getZ())) {
				world.setBlockToAir(fromPos);
				world.setBlockToAir(pos);
			}
		} else {
			if (state.getValue(HALF)) {
				if (fromPos.equals(pos.up())) {
					world.setBlockToAir(pos);
				}
			} else {
				if (fromPos.equals(pos.down())) {
					world.setBlockToAir(pos);
				}
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (!state.getValue(HALF)) {
			return Items.AIR;
		}
		return super.getItemDropped(state, rand, fortune);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getValue(HALF)) {
			worldIn.setBlockToAir(pos.up());
		} else {
			worldIn.setBlockToAir(pos.down());
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		IBlockState state = this.getStateFromMeta(meta);
		if (!state.getValue(HALF)) {
			return null;
		}
		return new TileEntityMagicMirror();
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
