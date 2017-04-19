package game;

import java.io.File;

import ui.screens.AgentSimulationScreen;

import com.badlogic.gdx.Game;

public class AgentSimulator extends Game {

	private AgentSimulationScreen gameScreen;
	private File mapFile;
	private int scale;

	public AgentSimulator(File mapFile, int scale) {
		super();
		this.mapFile = mapFile;
		this.scale = scale;

	}

	public void create() {
		gameScreen = new AgentSimulationScreen(mapFile, scale);
		setScreen(gameScreen);
	}



}
