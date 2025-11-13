package cc.unilock.deawt.transformer;

import cc.unilock.deawt.DeAWTMinecraft;
import net.minecraft.client.MinecraftApplet;
import net.minecraft.util.Session;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraft.client.MinecraftApplet")
public class MinecraftAppletTransformer extends MiniTransformer {
	@Patch.Method("init()V")
	public void patchInit(PatchContext ctx) {
		ctx.jumpToStart();
		ctx.add(
				ALOAD(0),
				INVOKESTATIC("cc/unilock/deawt/transformer/MinecraftAppletTransformer$Hooks", "init", "(Lnet/minecraft/client/MinecraftApplet;)V"),
				RETURN()
		);
	}

	@Patch.Method("start()V")
	public void patchStart(PatchContext ctx) {
		ctx.jumpToStart();
		ctx.add(RETURN());
	}

	@Patch.Method("stop()V")
	public void patchStop(PatchContext ctx) {
		ctx.jumpToStart();
		ctx.add(RETURN());
	}

	@Patch.Method("destroy()V")
	public void patchDestroy(PatchContext ctx) {
		ctx.jumpToStart();
		ctx.add(RETURN());
	}

	public static class Hooks {
		public static void init(MinecraftApplet that) {
			boolean fullscreen = "true".equalsIgnoreCase(that.getParameter("fullscreen"));
			that.mc = new DeAWTMinecraft(that.getWidth(), that.getHeight(), fullscreen);

			that.mc.minecraftUri = that.getDocumentBase().getHost();
			if (that.getDocumentBase().getPort() > 0) {
				that.mc.minecraftUri = that.mc.minecraftUri + ":" + that.getDocumentBase().getPort();
			}

			if (that.getParameter("username") != null && that.getParameter("sessionid") != null) {
				that.mc.session = new Session(that.getParameter("username"), that.getParameter("sessionid"));
				System.out.println("Setting user: " + that.mc.session.username + ", " + that.mc.session.sessionId);
			} else {
				that.mc.session = new Session("Player", "");
			}

			that.mc.setDemo("true".equals(that.getParameter("demo")));
			if (that.getParameter("server") != null && that.getParameter("port") != null) {
				that.mc.setServer(that.getParameter("server"), Integer.parseInt(that.getParameter("port")));
			}

			that.mc.run();
		}
	}
}
