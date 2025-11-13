package cc.unilock.deawt.transformer;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("org.lwjgl.opengl.Display")
public class DisplayTransformer extends MiniTransformer {
	@Patch.Method("createWindow()V")
	@Patch.Method.AffectsControlFlow
	public void patchCreateWindow(PatchContext ctx) {
		ctx.search(
				GETSTATIC("org/lwjgl/opengl/Display", "cached_icons", "[Ljava/nio/ByteBuffer;")
		).jumpBefore();

		ctx.add(
				RETURN()
		);
	}
}
