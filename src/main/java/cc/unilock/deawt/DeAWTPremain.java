package cc.unilock.deawt;

import cc.unilock.deawt.transformer.MinecraftAppletTransformer;
import nilloader.api.ClassTransformer;
import nilloader.api.ModRemapper;
import nilloader.api.NilLogger;

public class DeAWTPremain implements Runnable {
	public static final NilLogger LOGGER = NilLogger.get("deAWT");

	@Override
	public void run() {
		ModRemapper.setTargetMapping("default");

		ClassTransformer.register(new MinecraftAppletTransformer());
	}
}
