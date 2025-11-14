package cc.unilock.deawt.transformer;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraft.client.MinecraftApplet")
public class FMLMinecraftAppletTransformer extends MiniTransformer {
	@Patch.Method("fmlInitReentry()V")
	public void patchFmlInitReentry(PatchContext ctx) {
		ctx.jumpToStart();
		ctx.add(
				ALOAD(0),
				INVOKESTATIC("cc/unilock/deawt/transformer/MinecraftAppletTransformer$Hooks", "init", "(Lnet/minecraft/client/MinecraftApplet;)V"),
				RETURN()
		);
	}

	@Patch.Method("fmlStartReentry()V")
	public void patchFmlStartReentry(PatchContext ctx) {
		ctx.jumpToStart();
		ctx.add(
				RETURN()
		);
	}
}
