package com.covens.client.core.event;

import com.covens.client.handler.Keybinds;
import com.covens.common.core.net.NetworkHandler;
import com.covens.common.core.net.messages.PlaceHeldItemMessage;

import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MiscEventHandler {

	private Minecraft mc;

	public MiscEventHandler(Minecraft minecraft) {
		mc = minecraft;
	}

	@SubscribeEvent
	public void onKeyPress(KeyInputEvent evt) {
		if (Keybinds.placeItem.isPressed()) {
			if (isPointingToUpperFace() && canReplaceAbove() && isTopSolid()) {
				NetworkHandler.HANDLER.sendToServer(new PlaceHeldItemMessage(mc.objectMouseOver.getBlockPos().up()));
			}
		}
	}
	
	private boolean isPointingToUpperFace() {
		return mc.objectMouseOver.typeOfHit == Type.BLOCK && mc.objectMouseOver.sideHit == EnumFacing.UP;
	}
	
	private boolean canReplaceAbove() {
		return mc.world.getBlockState(mc.objectMouseOver.getBlockPos().up()).getBlock().isReplaceable(mc.world, mc.objectMouseOver.getBlockPos().up());
	}
	
	private boolean isTopSolid() {
		return mc.world.getBlockState(mc.objectMouseOver.getBlockPos()).getBlockFaceShape(mc.player.world, mc.objectMouseOver.getBlockPos(), EnumFacing.UP) == BlockFaceShape.SOLID;
	}
}
