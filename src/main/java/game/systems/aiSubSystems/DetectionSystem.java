package game.systems.aiSubSystems;


import game.AgentSimulatorConstants;
import game.systems.systemUtil.AABBFindAllAgentsCallback;

import java.util.ArrayList;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class DetectionSystem {


	public ArrayList<ArrayList<AbstractAgent>> detectAllAgents(AgentWorld world) {
		ArrayList<ArrayList<AbstractAgent>> returnList = new ArrayList<ArrayList<AbstractAgent>>();
		for (int i = 0; i < world.getAllAgents().size(); i++) {
			AbstractAgent agent = world.getAllAgents().get(i);
			float radius = AgentSimulatorConstants.detectionRadius;
			AABBFindAllAgentsCallback detection = new AABBFindAllAgentsCallback(world, agent.getPossition(), radius);

			Vector2 position = agent.getPossition();
			world.getPhysicsWorld().QueryAABB(detection, position.x - radius, position.y - radius, position.x + radius,
					position.y + radius);


			returnList.add(detection.getDetectedAgents());

			}
		return returnList;
	}

}
