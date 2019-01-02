package com.covens.client.core;

import com.covens.client.core.hud.HudController;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.IClientCommand;

public class ClientCommandGuiConfig extends CommandBase implements IClientCommand {

	@Override
	public String getName() {
		return "cvguicfg";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return this.getName();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		HudController.INSTANCE.showHud();
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
		return false;
	}

}
