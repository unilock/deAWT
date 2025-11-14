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

	@Override
	public void toggleFullscreen() {
		try {
			this.fullscreen = !this.fullscreen;
			if (this.fullscreen) {
				Display.setDisplayMode(Display.getDesktopDisplayMode());
				this.displayWidth = Display.getDisplayMode().getWidth();
				this.displayHeight = Display.getDisplayMode().getHeight();
			} else {
				this.displayWidth = this.tempDisplayWidth;
				this.displayHeight = this.tempDisplayHeight;
			}
			if (this.displayWidth <= 0) {
				this.displayWidth = 1;
			}
			if (this.displayHeight <= 0) {
				this.displayHeight = 1;
			}

			if (this.currentScreen != null) {
				this.resize(this.displayWidth, this.displayHeight);
			}

			Display.setFullscreen(this.fullscreen);
			Display.setVSyncEnabled(this.options.enableVsync);
			Display.update();
		} catch (Exception ignored) {
		}
	}
}
