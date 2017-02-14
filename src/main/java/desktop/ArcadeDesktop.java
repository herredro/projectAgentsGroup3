package desktop;

import static game.ArcadeConstants.*;
import game.Arcade;
import util.PackTextures;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;

public class ArcadeDesktop {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = screenWidth;
		config.height = screenHeight;

		Texture.setEnforcePotImages(false);
		PackTextures.run();

		new LwjglApplication(new Arcade(), config);
	}

}
