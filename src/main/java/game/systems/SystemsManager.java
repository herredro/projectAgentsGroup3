package game.systems;

import java.util.ArrayList;

import agentDefinitions.AgentWorld;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class SystemsManager {

	private ArrayList<AbstractSystem> systemList;

	private AgentWorld world;

	public SystemsManager(AgentWorld world, OrthographicCamera camera) {
		this.world = world;
		this.systemList= new ArrayList<AbstractSystem>();
		systemList.add(new MovementSystem());
		systemList.add(new RenderSystem(camera, world));
		
	}

	public void runSystemStep(float delta) {
		for (int i = 0; i < systemList.size(); i++) {
			systemList.get(i).proccessStep(world);
			world.getPhysicsWorld().step(delta, 8, 3);
		}
	}

}
