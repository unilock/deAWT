package cc.unilock.deawt.transformer;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraft.client.Minecraft")
public class MinecraftTransformer extends MiniTransformer {
	@Patch.Method("startGame()V")
	public void patchStartGame(PatchContext ctx) {
		ctx.search(
				INVOKESTATIC("org/lwjgl/opengl/Display", "setTitle", "(Ljava/lang/String;)V")
		).jumpAfter();

		ctx.add(
				ICONST_1(),
				INVOKESTATIC("org/lwjgl/opengl/Display", "setResizable", "(Z)V")
		);
	}
}
