package util;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class PackTextures {

	public static void run() {
		TexturePacker2.Settings settings = new TexturePacker2.Settings();
		int size = 2048 * 2;
		settings.maxHeight = size;
		settings.maxWidth = size;
		TexturePacker2.processIfModified(settings, "assets/textures", "assets/packed-textures", "textures.pack");
	}
}
