package com.covens.common.item.equipment;

import com.covens.client.render.entity.model.ModelWitchesArmor;
import com.covens.common.core.statics.ModCreativeTabs;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zabi.minecraft.minerva.client.blockmodels.IModelRegister;
import zabi.minecraft.minerva.client.blockmodels.ModelHandler;

public class ItemWitchesArmor extends ItemArmor implements IModelRegister {

	public ItemWitchesArmor(String id, ArmorMaterial materialIn, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndex, equipmentSlotIn);
		this.setMaxStackSize(1);
		this.setRegistryName(id);
		this.setTranslationKey(id);
		this.setCreativeTab(ModCreativeTabs.ITEMS_CREATIVE_TAB);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if (itemStack.getItem() instanceof ItemArmor) {
			ModelWitchesArmor.INSTANCE.hat1.showModel = armorSlot == EntityEquipmentSlot.HEAD;
			ModelWitchesArmor.INSTANCE.body.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			ModelWitchesArmor.INSTANCE.armLeft.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			ModelWitchesArmor.INSTANCE.armRight.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			ModelWitchesArmor.INSTANCE.legLeft.showModel = armorSlot == EntityEquipmentSlot.LEGS;
			ModelWitchesArmor.INSTANCE.legRight.showModel = armorSlot == EntityEquipmentSlot.LEGS;

			ModelWitchesArmor.INSTANCE.isChild = _default.isChild;
			ModelWitchesArmor.INSTANCE.isRiding = _default.isRiding;
			ModelWitchesArmor.INSTANCE.isSneak = _default.isSneak;
			ModelWitchesArmor.INSTANCE.rightArmPose = _default.rightArmPose;
			ModelWitchesArmor.INSTANCE.leftArmPose = _default.leftArmPose;

			return ModelWitchesArmor.INSTANCE;
		}
		return null;
	}

}
