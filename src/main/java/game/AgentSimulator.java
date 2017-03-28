package game;

import java.io.File;

import ui.screens.AgentSimulationScreen;

import com.badlogic.gdx.Game;

public class AgentSimulator extends Game {

	private AgentSimulationScreen gameScreen;
	private File mapFile;

	public AgentSimulator(File mapFile) {
		super();
		this.mapFile = mapFile;


	}

	public void create() {
		gameScreen = new AgentSimulationScreen(mapFile);
		setScreen(gameScreen);
	}

}
