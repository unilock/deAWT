package cc.unilock.deawt;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import org.lwjgl.opengl.Display;

public class DeAWTMinecraft extends Minecraft {
	public DeAWTMinecraft(int width, int height, boolean fullscreen) {
		super(null, null, width, height, fullscreen);
	}

	@Override
	public void displayCrashReportInternal(CrashReport crashReport) {
		this.shutdownMinecraftApplet();
		System.exit(1);
	}

	@Override
	public void runTick() {
		if (Display.getWidth() != this.displayWidth || Display.getHeight() != this.displayHeight) {
			this.resize(Display.getWidth(), Display.getHeight());
		}

		super.runTick();
	}
}
