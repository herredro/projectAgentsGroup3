package factorys;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentType;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class PopulateWorld {

	public static void addAllAgents(AgentWorld world) {
		addAgents(world, AgentType.PERSUER, 10, -200, -200);
		addAgents(world, AgentType.EVADER, 10, 200, 200);
	}

	private static void addAgents(AgentWorld world, AgentType type, int count, int x, int y) {
		AgentFactory factory = new AgentFactory(world.getPhysicsWorld(), world.getIdMap());
		for (int i = 1; i <= count; i++) {
			AbstractAgent agent = factory.createAgent(new Vector2(x, y), type);
			world.addAgent(agent);
		}

	}
}
