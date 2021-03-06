/**
 * This class was created by <ZaBi94> on Dec 10th, 2017.
 * It's distributed as part of Covens under
 * the MIT license.
 */

package com.covens.client.render.entity.renderer;

import com.covens.common.entity.EntityBrew;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.ItemStack;

public class RenderBrewBottle extends RenderSnowball<EntityBrew> {

	public RenderBrewBottle(RenderManager renderManager) {
		super(renderManager, null, Minecraft.getMinecraft().getRenderItem());
	}

	@Override
	public ItemStack getStackToRender(EntityBrew entityIn) {
		return entityIn.getBrew();
	}

}
