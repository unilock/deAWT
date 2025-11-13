package cc.unilock.deawt;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;

public class DeAWTMinecraft extends Minecraft {
	public DeAWTMinecraft(int width, int height, boolean fullscreen) {
		super(null, null, width, height, fullscreen);
	}

	@Override
	public void displayCrashReportInternal(CrashReport crashReport) {
		this.shutdownMinecraftApplet();
		System.exit(1);
	}
}
