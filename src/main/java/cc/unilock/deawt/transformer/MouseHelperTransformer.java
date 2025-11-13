package cc.unilock.deawt.transformer;

import nilloader.api.lib.asm.tree.LabelNode;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraft.util.MouseHelper")
public class MouseHelperTransformer extends MiniTransformer {
	@Patch.Method("ungrabMouseCursor()V")
	public void patchUngrabMouseCursor(PatchContext ctx) {
		LabelNode Lskip = new LabelNode();

		ctx.jumpToStart();
		ctx.add(GOTO(Lskip));

		ctx.search(
				ILOAD(1)
		).jumpBefore();
		ctx.add(
				Lskip,
				INVOKESTATIC("org/lwjgl/opengl/Display", "getWidth", "()I"),
				ISTORE(1),
				INVOKESTATIC("org/lwjgl/opengl/Display", "getHeight", "()I"),
				ISTORE(2)
		);
	}
}
