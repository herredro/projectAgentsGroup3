package game;

import ui.screens.GameScreen;

import com.badlogic.gdx.Game;

public class Arcade
		extends Game {

	private GameScreen gameScreen;

	public void create() {
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

}
