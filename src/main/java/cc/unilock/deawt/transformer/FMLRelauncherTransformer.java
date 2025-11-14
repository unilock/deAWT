package cc.unilock.deawt.transformer;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.FMLRelauncher")
public class FMLRelauncherTransformer extends MiniTransformer {
	@Patch.Method("showWindow(Z)V")
	public void patchShowWindow(PatchContext ctx) {
		ctx.jumpToStart();
		ctx.add(
				NEW("cpw/mods/fml/relauncher/DummyDownloader"),
				DUP(),
				INVOKESPECIAL("cpw/mods/fml/relauncher/DummyDownloader", "<init>", "()V"),
				PUTSTATIC("cpw/mods/fml/relauncher/RelaunchLibraryManager", "downloadMonitor", "Lcpw/mods/fml/relauncher/IDownloadDisplay;"),
				RETURN()
		);
	}
}
