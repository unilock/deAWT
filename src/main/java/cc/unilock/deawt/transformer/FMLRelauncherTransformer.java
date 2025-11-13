package cc.unilock.deawt.transformer;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.FMLRelauncher")
public class FMLRelauncherTransformer extends MiniTransformer {
	@Patch.Method("showWindow(Z)V")
	public void patchShowWindow(PatchContext ctx) {
		ctx.search(
				PUTFIELD("cpw/mods/fml/relauncher/FMLRelauncher", "popupWindow", "Ljavax/swing/JDialog;")
		).jumpBefore();

		ctx.add(
				POP(),
				ACONST_NULL()
		);
	}
}
