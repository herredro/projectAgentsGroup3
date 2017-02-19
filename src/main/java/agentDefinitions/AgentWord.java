package agentDefinitions;

import game.systems.MovementSystem;
import game.systems.RenderSystem;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class AgentWord {
	private ArrayList<AbstractAgent> allAgents;
	private World physicsWorld;
	private ArrayList<Obstacles> obstacList ;

	private RenderSystem renderSystem;
	private MovementSystem movementSystem;

	public AgentWord() {
		obstacList= new ArrayList<Obstacles>();
		allAgents= new ArrayList<AbstractAgent>();
		setPhysicsWorld(new World(new Vector2(), true));
		movementSystem = new MovementSystem();
		
	}


	public void process(float delta) {
		renderSystem.renderEverything();
		movementSystem.moveAgent(allAgents.get(0), new Vector2(50, 0));
		physicsWorld.step(delta, 8, 3);

	}
	public ArrayList<AbstractAgent> getAllAgents() {
		return allAgents;
	}

	public ArrayList<Obstacles> getObstacList() {
		return obstacList;
	}

	public void addAgent(AbstractAgent agent) {
		allAgents.add(agent);

	}

	public void addObstacle(Obstacles obstacle) {
		obstacList.add(obstacle);

	}

	public void setRenderSystem(RenderSystem renderSystem) {
		this.renderSystem = renderSystem;
	}

	public World getPhysicsWorld() {
		return physicsWorld;
	}

	// debug method
	public void setPhysicsWorld(World physicsWorld) {
		this.physicsWorld = physicsWorld;
	}

}
