package game;

import ui.screens.AgentSimulationScreen;

import com.badlogic.gdx.Game;

public class AgentSimulator extends Game {

	private AgentSimulationScreen gameScreen;

	public void create() {
		gameScreen = new AgentSimulationScreen();
		setScreen(gameScreen);
	}

}
