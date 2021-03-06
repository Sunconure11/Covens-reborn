package com.covens.common.entity.living;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityMultiSkin extends EntityTameable {

	private static final DataParameter<Integer> SKIN = EntityDataManager.createKey(EntityMultiSkin.class, DataSerializers.VARINT);

	public EntityMultiSkin(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SKIN, 0);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		this.setEntitySkinIndex(this.rand.nextInt(this.getSkinTypes()));
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@SideOnly(Side.CLIENT)
	public int getSkinIndex() {
		return this.dataManager.get(SKIN);
	}

	public void setEntitySkinIndex(int type) {
		if (type >= this.getSkinTypes()) {
			throw new IllegalArgumentException(String.format("Skin of index %d doesn't exist for %s", type, this.getClass().getName()));
		}
		this.dataManager.set(SKIN, type);
		this.dataManager.setDirty(SKIN);
	}

	public abstract int getSkinTypes();

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("skin", this.dataManager.get(SKIN));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.dataManager.set(SKIN, compound.getInteger("skin"));
		this.dataManager.setDirty(SKIN);
	}

}
