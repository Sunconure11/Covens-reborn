package com.covens.common.content.actionbar;

import java.util.ArrayList;

import com.covens.api.event.HotbarActionCollectionEvent;
import com.covens.api.hotbar.IHotbarAction;
import com.covens.common.Covens;
import com.covens.common.lib.LibMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HotbarAction implements IHotbarAction {

	public static final ResourceLocation DEFAULT_ICON_TEXTURE = new ResourceLocation(LibMod.MOD_ID, "textures/gui/abilities.png");

	private static final ArrayList<IHotbarAction> ACTIONS = new ArrayList<>();

	private ResourceLocation name;
	private int xIconIndex = 0, yIconIndex = 0;

	public HotbarAction(ResourceLocation name, int iconX, int iconY) {
		this.name = name;
		this.xIconIndex = iconX;
		this.yIconIndex = iconY;
		ACTIONS.add(this);
	}

	public static void refreshActions(EntityPlayer player, World world) {
		HotbarActionCollectionEvent evt = new HotbarActionCollectionEvent(player, world);
		MinecraftForge.EVENT_BUS.post(evt);
		Covens.proxy.loadActionsClient((ArrayList<IHotbarAction>) evt.getList(), player);
	}

	public static IHotbarAction getFromRegistryName(String name) {
		for (IHotbarAction act : ACTIONS) {
			if (act.getName().toString().equals(name)) {
				return act;
			}
		}
		throw new RuntimeException(name + " was never created as an IHotbarAction");
	}

	public static void registerNewAction(IHotbarAction action) {
		ACTIONS.add(action);
	}

	@Override
	public ResourceLocation getName() {
		return this.name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getIconIndexX() {
		return this.xIconIndex;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getIconIndexY() {
		return this.yIconIndex;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getIcon() {
		return DEFAULT_ICON_TEXTURE;
	}

}
