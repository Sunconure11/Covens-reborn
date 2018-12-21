package com.covens.common.lib;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Covens under
 * the MIT license.
 */
public final class LibMod {

	//ID for MOD
	public static final String MOD_ID = "covens";

	//Name of MOD
	public static final String MOD_NAME = "Covens";

	//Version of MOD
	public static final String MOD_VER = "0.0.1.3";

	//Dependency
	public static final String DEPENDENCIES = "required-after:forge@[14.23.4.2755,];"
			+ "required-after:patchouli@[1.0-6,];"
			+ "after:jei@[4.9.1.168,];"
			+ "after:waila@[1.8.24-B39_1.12,];"
			+ "required-after:baubles@[1.5.2,];"
			+ "after:thaumcraft@[1.12.2:6.1.BETA26,];";

	//Client proxy location
	public static final String PROXY_CLIENT = "com.covens.client.core.ClientProxy";

	//Server proxy location
	public static final String PROXY_COMMON = "com.covens.common.core.proxy.ServerProxy";

	private LibMod() {
	}
}
