package desktop;

import static game.AgentSimulatorConstants.screenHeight;
import static game.AgentSimulatorConstants.screenWidth;
import game.AgentSimulator;

import java.io.File;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class AgentSimulDesktop {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = screenWidth;
		config.height = screenHeight;

		// Texture.setEnforcePotImages(false);

		new LwjglApplication(new AgentSimulator(new File("savedmaps/world.txt"), 200), config);
	}

}
