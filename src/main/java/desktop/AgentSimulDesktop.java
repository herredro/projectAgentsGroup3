package desktop;

import static game.AgentSimulatorConstants.*;
import game.AgentSimulator;
import util.PackTextures;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;

public class AgentSimulDesktop {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = screenWidth;
		config.height = screenHeight;

		Texture.setEnforcePotImages(false);
		PackTextures.run();

		new LwjglApplication(new AgentSimulator(), config);
	}

}
