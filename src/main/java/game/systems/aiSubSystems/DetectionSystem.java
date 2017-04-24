package game.systems.aiSubSystems;


import game.systems.systemUtil.AABBFindAllCallback;

import java.util.ArrayList;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class DetectionSystem {





	public ArrayList<ArrayList<AbstractAgent>> detectAllAgents(AgentWorld world) {
		ArrayList<ArrayList<AbstractAgent>> returnList = new ArrayList<ArrayList<AbstractAgent>>();
		for (int i = 0; i < world.getAllAgents().size(); i++) {
			AbstractAgent agent = world.getAllAgents().get(i);
			float radius = 100f;
			AABBFindAllCallback detection = new AABBFindAllCallback(world, agent.getPossition(), radius);

			Vector2 position = agent.getPossition();
			world.getPhysicsWorld().QueryAABB(detection, position.x - radius, position.y - radius, position.x + radius,
					position.y + radius);


			returnList.add(detection.getDetectedAgents());

			}
		return returnList;
	}

}
