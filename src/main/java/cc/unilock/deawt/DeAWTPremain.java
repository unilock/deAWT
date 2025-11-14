package cc.unilock.deawt;

import cc.unilock.deawt.transformer.*;
import nilloader.api.ClassTransformer;
import nilloader.api.ModRemapper;
import nilloader.api.NilLogger;

public class DeAWTPremain implements Runnable {
	public static final NilLogger LOGGER = NilLogger.get("deAWT");

	@Override
	public void run() {
		ModRemapper.setTargetMapping("default");

		ClassTransformer.register(new MinecraftAppletTransformer());
		if (isFML()) {
			LOGGER.info("deAWT running on FML!");
			ClassTransformer.register(new FMLMinecraftAppletTransformer());
			ClassTransformer.register(new FMLRelauncherTransformer());
		} else {
			LOGGER.info("deAWT running on Vanilla!");
			ClassTransformer.register(new VanillaMinecraftAppletTransformer());
		}

		ClassTransformer.register(new DisplayTransformer());
		ClassTransformer.register(new MinecraftTransformer());
		ClassTransformer.register(new MouseHelperTransformer());
	}

	private boolean isFML() {
		return this.getClass().getClassLoader().getResource("cpw/mods/fml/relauncher/FMLRelauncher.class") != null;
	}
}
