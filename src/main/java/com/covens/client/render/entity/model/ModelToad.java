package com.covens.client.render.entity.model;

import com.covens.common.entity.living.animals.EntityToad;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * toad2 - cybercat5555 Created using Tabula 5.1.0
 */
public class ModelToad extends ModelBase {
	public ModelRenderer stomach;
	public ModelRenderer head01;
	public ModelRenderer lArm01;
	public ModelRenderer rArm01;
	public ModelRenderer lLeg01;
	public ModelRenderer rLeg01;
	public ModelRenderer head02;
	public ModelRenderer jaw;
	public ModelRenderer lEye;
	public ModelRenderer rEye;
	public ModelRenderer lArm02;
	public ModelRenderer lHand;
	public ModelRenderer rArm02;
	public ModelRenderer rHand;
	public ModelRenderer lLeg02;
	public ModelRenderer lLeg03;
	public ModelRenderer rLeg02;
	public ModelRenderer rLeg03;
	private float timer = 0;

	public ModelToad() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head01 = new ModelRenderer(this, 0, 17);
		this.head01.setRotationPoint(0.0F, -1.0118749141693115F, 0.6827225685119629F);
		this.head01.addBox(-2.5F, -1.5F, -3.0F, 5, 5, 4, 0.0F);
		this.setRotateAngle(this.head01, -0.41887902047863906F, 0.0F, 0.0F);
		this.lLeg02 = new ModelRenderer(this, 39, 19);
		this.lLeg02.setRotationPoint(0.8999999761581421F, 3.5999999046325684F, -0.5F);
		this.lLeg02.addBox(-1.2000000476837158F, -1.0F, 0.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(this.lLeg02, 0.45378560551852565F, 0.13962634015954636F, 0.19198621771937624F);
		this.lHand = new ModelRenderer(this, 3, 27);
		this.lHand.setRotationPoint(0.10000000149011612F, 4.5F, -0.30000001192092896F);
		this.lHand.addBox(-1.5F, 0.0F, -2.700000047683716F, 3, 0, 4, 0.0F);
		this.setRotateAngle(this.lHand, 0.7330382858376184F, 0.08726646259971647F, 0.45378560551852565F);
		this.rArm02 = new ModelRenderer(this, 32, 25);
		this.rArm02.mirror = true;
		this.rArm02.setRotationPoint(-1.0F, -0.10000000149011612F, 2.799999952316284F);
		this.rArm02.addBox(-1.0099999904632568F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(this.rArm02, 0.45378560551852565F, 0.0F, 0.0F);
		this.lEye = new ModelRenderer(this, 27, 0);
		this.lEye.setRotationPoint(1.2999999523162842F, -0.5F, -2.299999952316284F);
		this.lEye.addBox(0.0F, -2.0999999046325684F, -1.5F, 2, 2, 3, 0.0F);
		this.setRotateAngle(this.lEye, 0.0F, 0.19198621771937624F, 0.13962634015954636F);
		this.lLeg01 = new ModelRenderer(this, 22, 16);
		this.lLeg01.setRotationPoint(3.299999952316284F, -0.7989158034324646F, 7.941141605377197F);
		this.lLeg01.addBox(0.0F, -1.0F, -1.5F, 2, 5, 3, 0.0F);
		this.setRotateAngle(this.lLeg01, -0.512306872707822F, 0.06843323235500261F, -0.1795160990731347F);
		this.rLeg01 = new ModelRenderer(this, 22, 16);
		this.rLeg01.mirror = true;
		this.rLeg01.setRotationPoint(-3.299999952316284F, -0.7989158034324646F, 7.941141605377197F);
		this.rLeg01.addBox(-2.0F, -1.0F, -1.5F, 2, 5, 3, 0.0F);
		this.setRotateAngle(this.rLeg01, -0.512306872707822F, -0.06843323235500261F, 0.1795160990731347F);
		this.lArm01 = new ModelRenderer(this, 16, 25);
		this.lArm01.setRotationPoint(3.0F, 0.517690896987915F, 1.1627533435821533F);
		this.lArm01.addBox(0.0F, -1.0F, -1.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(this.lArm01, -0.8158759115445331F, 0.09894094522554002F, -0.261566760677931F);
		this.jaw = new ModelRenderer(this, 37, 10);
		this.jaw.setRotationPoint(0.0F, 0.4000000059604645F, -0.6000000238418579F);
		this.jaw.addBox(-3.0F, 0.0F, -5.0F, 6, 2, 6, 0.0F);
		this.lLeg03 = new ModelRenderer(this, 49, 25);
		this.lLeg03.setRotationPoint(-0.10000000149011612F, 0.6000000238418579F, 4.300000190734863F);
		this.lLeg03.addBox(-1.2000000476837158F, -0.5F, -5.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(this.lLeg03, 0.41887902047863906F, -0.33161255787892263F, 0.0F);
		this.rLeg02 = new ModelRenderer(this, 39, 19);
		this.rLeg02.mirror = true;
		this.rLeg02.setRotationPoint(-0.8999999761581421F, 3.5999999046325684F, -0.20000000298023224F);
		this.rLeg02.addBox(-1.2000000476837158F, -1.0F, 0.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(this.rLeg02, 0.45378560551852565F, -0.13962634015954636F, -0.19198621771937624F);
		this.rHand = new ModelRenderer(this, 3, 27);
		this.rHand.mirror = true;
		this.rHand.setRotationPoint(-0.10000000149011612F, 4.5F, -0.30000001192092896F);
		this.rHand.addBox(-1.5F, 0.0F, -2.700000047683716F, 3, 0, 4, 0.0F);
		this.setRotateAngle(this.rHand, 0.7330382858376184F, -0.08726646259971647F, -0.45378560551852565F);
		this.rArm01 = new ModelRenderer(this, 16, 25);
		this.rArm01.mirror = true;
		this.rArm01.setRotationPoint(-3.0F, 0.517690896987915F, 1.1627533435821533F);
		this.rArm01.addBox(-2.0F, -1.0F, -1.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(this.rArm01, -0.8158759115445331F, -0.09894094522554002F, 0.261566760677931F);
		this.rEye = new ModelRenderer(this, 27, 0);
		this.rEye.mirror = true;
		this.rEye.setRotationPoint(-1.2999999523162842F, -0.5F, -2.299999952316284F);
		this.rEye.addBox(-2.0F, -2.0999999046325684F, -1.5F, 2, 2, 3, 0.0F);
		this.setRotateAngle(this.rEye, 0.0F, -0.19198621771937624F, -0.13962634015954636F);
		this.lArm02 = new ModelRenderer(this, 32, 25);
		this.lArm02.setRotationPoint(1.0F, -0.10000000149011612F, 2.799999952316284F);
		this.lArm02.addBox(-0.9900000095367432F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(this.lArm02, 0.45378560551852565F, 0.0F, 0.0F);
		this.head02 = new ModelRenderer(this, 37, 0);
		this.head02.setRotationPoint(0.0F, -0.20000000298023224F, -2.200000047683716F);
		this.head02.addBox(-3.0F, -1.5F, -5.699999809265137F, 6, 2, 6, 0.0F);
		this.setRotateAngle(this.head02, 0.8726646259971648F, 0.0F, 0.0F);
		this.rLeg03 = new ModelRenderer(this, 49, 25);
		this.rLeg03.mirror = true;
		this.rLeg03.setRotationPoint(0.10000000149011612F, 0.6000000238418579F, 4.300000190734863F);
		this.rLeg03.addBox(-1.2000000476837158F, -0.5F, -5.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(this.rLeg03, 0.41887902047863906F, 0.33161255787892263F, 0.0F);
		this.stomach = new ModelRenderer(this, 0, 0);
		this.stomach.setRotationPoint(0.0F, 17.7F, -1.9F);
		this.stomach.addBox(-4.0F, -2.5F, 0.0F, 8, 6, 10, 0.0F);
		this.setRotateAngle(this.stomach, -0.3665191429188092F, 0.0F, 0.0F);
		this.stomach.addChild(this.head01);
		this.lLeg01.addChild(this.lLeg02);
		this.lArm02.addChild(this.lHand);
		this.rArm01.addChild(this.rArm02);
		this.head02.addChild(this.lEye);
		this.stomach.addChild(this.lLeg01);
		this.stomach.addChild(this.rLeg01);
		this.stomach.addChild(this.lArm01);
		this.head02.addChild(this.jaw);
		this.lLeg02.addChild(this.lLeg03);
		this.rLeg01.addChild(this.rLeg02);
		this.rArm02.addChild(this.rHand);
		this.stomach.addChild(this.rArm01);
		this.head02.addChild(this.rEye);
		this.lArm01.addChild(this.lArm02);
		this.head01.addChild(this.head02);
		this.rLeg02.addChild(this.rLeg03);
	}
	
	public void resetAngles() {
		this.stomach.rotateAngleX = -0.36651914291F;
		this.lLeg01.rotateAngleX = -0.51225413546F;
		this.lLeg02.rotateAngleX = 0.45378560551F;
		this.lLeg03.rotateAngleX = 0.41887902047863906F;
		this.rLeg01.rotateAngleX = this.lLeg01.rotateAngleX;
		this.rLeg02.rotateAngleX = this.lLeg02.rotateAngleX;
		this.rLeg03.rotateAngleX = this.lLeg03.rotateAngleX;
		this.lArm01.rotateAngleX = -0.8159414253F;
		this.lArm02.rotateAngleX = 0.45378560551F;
		this.rArm01.rotateAngleX = this.lArm01.rotateAngleX;
		this.rArm02.rotateAngleX = this.lArm02.rotateAngleX;
		this.lLeg03.rotateAngleY = -0.6000000238418579F;
		this.rLeg03.rotateAngleY = 0.6000000238418579F;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.stomach.render(f5);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		if (entityIn.onGround) {
			this.setRotateAngle(this.head02, (float) (0.6981317007977318F + ((headPitch * Math.PI) / 360)), (float) ((netHeadYaw * Math.PI) / 360), 0);
		}
	}

	@Override
	 public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks) {
		float time = entity.ticksExisted * 0.2F;
		EntityToad toad = (EntityToad) entity;
    	float jumpProgress = toad.getJumpProgress(partialRenderTicks);
		this.jaw.rotationPointY = (float) (0.5F + (0.02 * MathHelper.sin(time)));

		

		if (toad.isSitting()) {
			this.lLeg03.rotateAngleY = 1.0471975512F;
			this.rLeg03.rotateAngleY = -1.0471975512F;
		} //Check later whether it works...
		
		resetAngles();
		if(toad.isJumping()) {
			if(jumpProgress < 0.3333334) {
				this.stomach.rotateAngleX = -0.36651914291F + 0.36651914291F*jumpProgress*3;
				this.lLeg01.rotateAngleX = -0.51225413546F + 1.90851753706F*jumpProgress*3;
				this.lLeg02.rotateAngleX = 0.45378560551F -1.85004901F*jumpProgress*3;
				this.lLeg03.rotateAngleX = 0.41887902047863906F + 1.93731546971F*jumpProgress*3;
				this.rLeg01.rotateAngleX = this.lLeg01.rotateAngleX;
				this.rLeg02.rotateAngleX = this.lLeg02.rotateAngleX;
				this.rLeg03.rotateAngleX = this.lLeg03.rotateAngleX;
				this.lArm01.rotateAngleX = -0.8159414253F - 1.54025306F*jumpProgress*3;
				this.lArm02.rotateAngleX = 0.45378560551F + 0.94247779609F*jumpProgress*3;
				this.rArm01.rotateAngleX = this.lArm01.rotateAngleX;
				this.rArm02.rotateAngleX = this.lArm02.rotateAngleX;
				
			}else if(jumpProgress < 0.6666667) {
				this.stomach.rotateAngleX = 0 + 0.36651914291F*(jumpProgress-0.3333334F)*3;
				this.lLeg01.rotateAngleX = 1.3962634016F - 1.90851753706F*(jumpProgress-0.3333334F)*3;
				this.lLeg02.rotateAngleX = -1.3962634016F + 1.85004901F*(jumpProgress-0.3333334F)*3;
				this.lLeg03.rotateAngleX = 2.35619449019F - 1.93731546971F*(jumpProgress-0.3333334F)*3;
				this.rLeg01.rotateAngleX = this.lLeg01.rotateAngleX;
				this.rLeg02.rotateAngleX = this.lLeg02.rotateAngleX;
				this.rLeg03.rotateAngleX = this.lLeg03.rotateAngleX;
				this.lArm01.rotateAngleX = -2.35619449019F + 2.2122048269F*(jumpProgress-0.3333334F)*3;
				this.lArm02.rotateAngleX = 1.3962634016F - 0.94247779609F*(jumpProgress-0.3333334F)*3;
				this.rArm01.rotateAngleX = this.lArm01.rotateAngleX;
				this.rArm02.rotateAngleX = this.lArm02.rotateAngleX;	
			}else {
				this.stomach.rotateAngleX = 0.36651914291F - 0.733038286F*(jumpProgress-0.6666667F)*3;
			}
			
		}

	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
