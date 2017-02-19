package ui.screens;

import static game.AgentSimulatorConstants.screenHeight;
import static game.AgentSimulatorConstants.screenWidth;
import factorys.AgentFactory;
import factorys.ObstacleFactory;
import game.systems.RenderSystem;
import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentType;
import agentDefinitions.AgentWord;
import agentDefinitions.Obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class AgentSimulationScreen implements Screen {

	private AgentWord world;
	private OrthographicCamera camera;

	public AgentSimulationScreen() {
		this.world = new AgentWord();
		this.camera = new OrthographicCamera(screenWidth, screenHeight);
		RenderSystem renderSystem = new RenderSystem(camera, world);
		world.setRenderSystem(renderSystem);
		creatBoxBoarder();
		addAgents(AgentType.PERSUER, 2);
	}

	private void addAgents(AgentType type, int count) {
		AgentFactory factory = new AgentFactory(world.getPhysicsWorld());
		for (int i = 1; i <= count; i++) {
			AbstractAgent agent = factory.createAgent(new Vector2(30 + i * 100, 30), type);
			world.addAgent(agent);
		}

	}



	public void show() {

	}

	private void creatBoxBoarder() {
		ObstacleFactory obstacleFactory = new ObstacleFactory(world.getPhysicsWorld());
		Obstacles rightBoarder = obstacleFactory.createObstacle(new Vector2[] { new Vector2(), new Vector2(10, 0),
				new Vector2(10, 300), new Vector2(0, 300) }, new Vector2(0, 0));
		world.addObstacle(rightBoarder);
		Obstacles leftBoarder = obstacleFactory.createObstacle(new Vector2[] { new Vector2(), new Vector2(10, 0),
				new Vector2(10, 300), new Vector2(0, 300) }, new Vector2(600, 0));
		world.addObstacle(leftBoarder);
		Obstacles bottomBoarder = obstacleFactory.createObstacle(new Vector2[] { new Vector2(), new Vector2(600, 0),
				new Vector2(600, 10), new Vector2(0, 10) }, new Vector2(0, 0));
		world.addObstacle(bottomBoarder);
		Obstacles topBoarder = obstacleFactory.createObstacle(new Vector2[] { new Vector2(), new Vector2(600, 0),
				new Vector2(600, 10), new Vector2(0, 10) }, new Vector2(0, 300));
		world.addObstacle(topBoarder);
		
	}

	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		world.process(delta);

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
