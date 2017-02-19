package desktop;

import static game.AgentSimulatorConstants.screenHeight;
import static game.AgentSimulatorConstants.screenWidth;
import game.AgentSimulator;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class AgentSimulDesktop {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = screenWidth;
		config.height = screenHeight;

		// Texture.setEnforcePotImages(false);

		new LwjglApplication(new AgentSimulator(), config);
	}

}
