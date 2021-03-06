package com.covens.client.render.entity.model;

import com.covens.common.entity.living.animals.EntitySnake;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * adder - cybercat5555 Created using Tabula 5.1.0
 */
public class ModelSnake extends ModelBase {
	// private static final int COIL_ANIMATION_LENGTH = 160;
	public ModelRenderer neck01a;
	public ModelRenderer neck01b;
	public ModelRenderer neck02;
	public ModelRenderer body01;
	public ModelRenderer head;
	public ModelRenderer tongue;
	public ModelRenderer upperJawM;
	public ModelRenderer upperJawL;
	public ModelRenderer upperJawR;
	public ModelRenderer lowerJaw;
	public ModelRenderer eyes;
	public ModelRenderer lFang;
	public ModelRenderer rFang;
	public ModelRenderer tail01;
	public ModelRenderer tail01b;
	public ModelRenderer tail02;
	public ModelRenderer tail03;
	public ModelRenderer tail03b;
	public ModelRenderer tail04;
	public ModelRenderer tail05;

	public ModelSnake() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.tongue = new ModelRenderer(this, 25, 0);
		this.tongue.setRotationPoint(0.0F, 0.4F, -2.0F);
		this.tongue.addBox(-1.5F, 0.0F, -5.0F, 3, 0, 7, 0.0F);
		this.tail03b = new ModelRenderer(this, 22, 55);
		this.tail03b.setRotationPoint(0.0F, 0.5F, 0.0F);
		this.tail03b.addBox(-1.5F, 0.0F, -1.0F, 3, 1, 7, 0.0F);
		this.upperJawR = new ModelRenderer(this, 17, 0);
		this.upperJawR.mirror = true;
		this.upperJawR.setRotationPoint(-1.9F, -0.9F, -3.0F);
		this.upperJawR.addBox(-0.5F, -1.0F, -4.07F, 2, 2, 5, 0.0F);
		this.setRotateAngle(this.upperJawR, 0.0F, -0.19198621771937624F, 0.0F);
		this.upperJawL = new ModelRenderer(this, 17, 0);
		this.upperJawL.setRotationPoint(0.9F, -0.9F, -3.0F);
		this.upperJawL.addBox(-0.5F, -1.0F, -3.7F, 2, 2, 5, 0.0F);
		this.setRotateAngle(this.upperJawL, 0.0F, 0.19198621771937624F, 0.0F);
		this.tail03 = new ModelRenderer(this, 23, 45);
		this.tail03.setRotationPoint(0.0F, 0.0F, 5.9F);
		this.tail03.addBox(-1.5F, -1.1F, -1.0F, 3, 2, 7, 0.0F);
		this.lFang = new ModelRenderer(this, 0, 0);
		this.lFang.setRotationPoint(1.4F, -0.5F, -3.6F);
		this.lFang.addBox(0.0F, 0.0F, -0.5F, 0, 2, 1, 0.0F);
		this.eyes = new ModelRenderer(this, 40, 0);
		this.eyes.setRotationPoint(0.0F, -2.0F, -3.9F);
		this.eyes.addBox(-2.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
		this.tail01b = new ModelRenderer(this, 46, 18);
		this.tail01b.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail01b.addBox(-2.3F, -1.5F, 0.5F, 2, 3, 7, 0.0F);
		this.tail05 = new ModelRenderer(this, 43, 54);
		this.tail05.setRotationPoint(0.0F, 0.5F, 7.8F);
		this.tail05.addBox(-0.5F, -0.5F, -0.6F, 1, 1, 8, 0.0F);
		this.rFang = new ModelRenderer(this, 0, 0);
		this.rFang.setRotationPoint(-1.4F, -0.5F, -3.6F);
		this.rFang.addBox(0.0F, 0.0F, -0.5F, 0, 2, 1, 0.0F);
		this.body01 = new ModelRenderer(this, 0, 31);
		this.body01.setRotationPoint(0.0F, 0.0F, 1.2F);
		this.body01.addBox(-2.5F, -1.8F, -0.4F, 5, 4, 8, 0.0F);
		this.tail04 = new ModelRenderer(this, 41, 40);
		this.tail04.setRotationPoint(0.0F, 0.5F, 5.8F);
		this.tail04.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 9, 0.0F);
		this.neck01b = new ModelRenderer(this, 43, 28);
		this.neck01b.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck01b.addBox(-2.3F, -1.49F, -6.0F, 2, 3, 8, 0.0F);
		this.head = new ModelRenderer(this, 0, 22);
		this.head.setRotationPoint(0.0F, 0.8F, -5.9F);
		this.head.addBox(-2.5F, -2.4F, -3.0F, 5, 3, 3, 0.0F);
		this.lowerJaw = new ModelRenderer(this, 0, 8);
		this.lowerJaw.setRotationPoint(0.0F, 0.1F, -2.0F);
		this.lowerJaw.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
		this.neck01a = new ModelRenderer(this, 27, 33);
		this.neck01a.setRotationPoint(0.0F, 22.5F, -3.9F);
		this.neck01a.addBox(-0.7F, -1.49F, -6.0F, 3, 3, 8, 0.0F);
		this.tail02 = new ModelRenderer(this, 0, 45);
		this.tail02.setRotationPoint(0.0F, 0.0F, 7.4F);
		this.tail02.addBox(-2.0F, -1.49F, -0.5F, 4, 3, 7, 0.0F);
		this.neck02 = new ModelRenderer(this, 0, 45);
		this.neck02.setRotationPoint(0.0F, 0.0F, -5.9F);
		this.neck02.addBox(-2.0F, -1.5F, -6.0F, 4, 3, 7, 0.0F);
		this.upperJawM = new ModelRenderer(this, 0, 0);
		this.upperJawM.setRotationPoint(0.0F, -1.3F, -2.8F);
		this.upperJawM.addBox(-1.5F, -1.1F, -4.4F, 3, 2, 5, 0.0F);
		this.setRotateAngle(this.upperJawM, 0.08726646259971647F, 0.0F, 0.0F);
		this.tail01 = new ModelRenderer(this, 27, 22);
		this.tail01.setRotationPoint(0.0F, 0.0F, 6.7F);
		this.tail01.addBox(-0.7F, -1.5F, 0.6F, 3, 3, 7, 0.0F);
		this.head.addChild(this.tongue);
		this.tail03.addChild(this.tail03b);
		this.head.addChild(this.upperJawR);
		this.head.addChild(this.upperJawL);
		this.tail02.addChild(this.tail03);
		this.upperJawM.addChild(this.lFang);
		this.head.addChild(this.eyes);
		this.tail01.addChild(this.tail01b);
		this.tail04.addChild(this.tail05);
		this.upperJawM.addChild(this.rFang);
		this.neck01a.addChild(this.body01);
		this.tail03.addChild(this.tail04);
		this.neck01a.addChild(this.neck01b);
		this.neck02.addChild(this.head);
		this.head.addChild(this.lowerJaw);
		this.tail01.addChild(this.tail02);
		this.neck01a.addChild(this.neck02);
		this.head.addChild(this.upperJawM);
		this.body01.addChild(this.tail01);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
		float angle = 0.34906585039F;
		EntitySnake snek = (EntitySnake) entity;
		if (snek.isSitting()) {
			this.neck01a.offsetX = -0.3F;
			this.neck01a.rotateAngleY = -0.09110618695F;
			this.neck02.rotateAngleY = -1.27478848566F;
			this.neck02.rotateAngleX = -0.27314402793F;
			this.body01.rotateAngleY = 1.09397237515F;
			this.tail01.rotateAngleY = 0.95609136424F;
			this.tail02.rotateAngleY = 1.27478848566F;
			this.tail03.rotateAngleY = 0.81960661673F;
			this.tail04.rotateAngleY = 1.54810704652F;
			this.tail05.rotateAngleY = 1.59348560707F;

			this.head.rotateAngleX = 0.13665928043F;
			this.head.rotateAngleY = -0.77405352325F;
			this.head.rotateAngleZ = -0.18203784098F;
		} else {
			this.neck01a.offsetX = 0.3F * MathHelper.cos(limbSwing);
			this.neck01a.rotateAngleY = angle * MathHelper.sin(limbSwing);
			this.neck02.rotateAngleY = angle * MathHelper.sin(limbSwing - 5);
			this.neck02.rotateAngleX = 0;
			this.body01.rotateAngleY = angle * MathHelper.sin(limbSwing + 5);
			this.tail01.rotateAngleY = angle * MathHelper.sin(limbSwing + 11);
			this.tail02.rotateAngleY = angle * MathHelper.sin(limbSwing + 4);
			this.tail03.rotateAngleY = angle * MathHelper.sin(limbSwing + 2);
			this.tail04.rotateAngleY = angle * MathHelper.sin(limbSwing + 3);
			this.tail05.rotateAngleY = (angle / 4F) * MathHelper.sin(limbSwing + 2);
			this.head.rotateAngleY = this.neck02.rotateAngleY;
			this.head.rotateAngleZ = 0.174532925F * MathHelper.cos(limbSwing - 5);
			this.head.rotateAngleX = 0;
			this.head.rotateAngleZ = 0;
		}
		this.neck01a.render(scale);
		this.head.rotateAngleY = 0.001F * MathHelper.sin(limbSwing);
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
