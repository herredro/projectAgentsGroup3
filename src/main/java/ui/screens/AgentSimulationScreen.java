package ui.screens;

import static game.AgentSimulatorConstants.agentScreenHeight;
import static game.AgentSimulatorConstants.agentScreenWidth;
import factorys.PopulateWorld;
import fileReader.SaveFileReader;
import game.systems.SystemsManager;

import java.io.File;

import agentDefinitions.AgentWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

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
		PopulateWorld.addAllAgents(world);
	}

	public AgentWorld getWorld() {
		return world;
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
