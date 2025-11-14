package cc.unilock.deawt.transformer;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraft.client.MinecraftApplet")
public class VanillaMinecraftAppletTransformer extends MiniTransformer {
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
		ctx.add(
				RETURN()
		);
	}
}
