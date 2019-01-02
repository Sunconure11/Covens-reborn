package com.covens.common.core.net.messages;

import java.util.Random;

import com.covens.common.core.net.SimpleMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SmokeSpawn extends SimpleMessage<SmokeSpawn> {
	public double x, y, z;

	public SmokeSpawn(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public SmokeSpawn() {
	}

	@Override
	public IMessage handleMessage(MessageContext context) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			Random r = new Random();
			for (int i = 0; i < 5; i++) {
				Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.CLOUD, this.x + (r.nextGaussian() * 0.2), this.y + (r.nextDouble() * 0.2), this.z + (r.nextGaussian() * 0.2), 0, 0.1, 0);
			}
		});
		return null;
	}

}
