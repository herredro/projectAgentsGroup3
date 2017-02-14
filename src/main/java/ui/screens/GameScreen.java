package ui.screens;

import static game.ArcadeConstants.*;
import game.ArcadeWorld;
import game.components.DimensionComponent;
import game.components.PositionComponent;
import game.components.SpriteComponent;
import game.systems.RenderSystem;

import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class GameScreen
		implements Screen {

	private ArcadeWorld world;

	private OrthographicCamera camera;

	public void show() {
		world = new ArcadeWorld();
		camera = new OrthographicCamera(screenWidth, screenHeight);

		world.setSystem(new RenderSystem(camera));

		world.initialize();

		Entity background = world.createEntity();
		background.addComponent(new PositionComponent(new Vector2()));
		background.addComponent(new SpriteComponent("background-pictures-nature"));
		background.addComponent(new DimensionComponent(screenWidth, screenHeight));
		background.addToWorld();
	}

	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		world.setDelta(delta);
		world.process();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	

	public void hide() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void resume() {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
