package agentDefinitions;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class AgentWorld {
	private ArrayList<AbstractAgent> allAgents;
	private World physicsWorld;
	private ArrayList<Obstacles> obstacList ;



	public AgentWorld() {
		obstacList= new ArrayList<Obstacles>();
		allAgents= new ArrayList<AbstractAgent>();
		setPhysicsWorld(new World(new Vector2(), true));
		

	}


	public void process(float delta) {

		// physicsWorld.step(delta, 8, 3);

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


	public World getPhysicsWorld() {
		return physicsWorld;
	}

	// debug method
	public void setPhysicsWorld(World physicsWorld) {
		this.physicsWorld = physicsWorld;
	}

}
