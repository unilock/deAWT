package cc.unilock.deawt;

import cc.unilock.deawt.transformer.DisplayTransformer;
import cc.unilock.deawt.transformer.MinecraftAppletTransformer;
import cc.unilock.deawt.transformer.MinecraftTransformer;
import cc.unilock.deawt.transformer.MouseHelperTransformer;
import nilloader.api.ClassTransformer;
import nilloader.api.ModRemapper;
import nilloader.api.NilLogger;

public class DeAWTPremain implements Runnable {
	public static final NilLogger LOGGER = NilLogger.get("deAWT");

	@Override
	public void run() {
		ModRemapper.setTargetMapping("default");

		ClassTransformer.register(new DisplayTransformer());
//		ClassTransformer.register(new FMLRelauncherTransformer());
		ClassTransformer.register(new MinecraftAppletTransformer());
		ClassTransformer.register(new MinecraftTransformer());
		ClassTransformer.register(new MouseHelperTransformer());
	}
}
