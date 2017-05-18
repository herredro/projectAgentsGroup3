package ui.screens;

import static game.AgentSimulatorConstants.agentScreenHeight;
import static game.AgentSimulatorConstants.agentScreenWidth;
import factorys.AgentFactory;
import fileReader.SaveFileReader;
import game.systems.SystemsManager;

import java.io.File;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentType;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class AgentSimulationScreen implements Screen {

	private AgentWorld world;
	private OrthographicCamera camera;
	private SystemsManager systemsManager;
	private SaveFileReader saveLoader;

	public AgentSimulationScreen(File mapFile, int scale) {
		this.world = new AgentWorld();
		this.camera = new OrthographicCamera(agentScreenWidth, agentScreenHeight);
		this.systemsManager= new SystemsManager(world, camera);
		try {
			this.saveLoader = new SaveFileReader(mapFile, world);
		} catch (Error e) {
			System.out.println(e);
		}
		// System.out.println(saveLoader.readNextLine());
		// System.out.println(saveLoader.getTextFile() == null);
		saveLoader.setScaleFactor(scale);
		saveLoader.loadObstacles();
		// creatBoxBoarder();
		addAgents(AgentType.PERSUER, 40, 0, 0);
		addAgents(AgentType.EVADER, 40, 100, 100);
		addAgents(AgentType.EVADER, 20, -100, -100);

	}

	private void addAgents(AgentType type, int count, int x, int y) {
		AgentFactory factory = new AgentFactory(world.getPhysicsWorld(), world.getIdMap());
		for (int i = 1; i <= count; i++) {
			AbstractAgent agent = factory.createAgent(new Vector2(x, y), type);
			world.addAgent(agent);
		}

	}



	public void show() {

	}

	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();

		systemsManager.runSystemStep(delta);
		// world.process(delta);

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
