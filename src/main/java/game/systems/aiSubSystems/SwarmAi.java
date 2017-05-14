package game.systems.aiSubSystems;

import game.AgentSimulatorConstants;

import java.util.ArrayList;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentState;
import agentDefinitions.AgentWorld;
import agentDefinitions.EvaderAgent;
import agentDefinitions.PersuerAgent;

import com.badlogic.gdx.math.Vector2;

public class SwarmAi {
	
	private int deathDistance;

	private DetectionSystem detectionSystem;
	private long startOfSim;
	private final long targetUpdateRate = 500;


	public SwarmAi() {
		super();
		this.detectionSystem = new DetectionSystem();
		startOfSim = System.currentTimeMillis();
		deathDistance = AgentSimulatorConstants.deathRadius;

	}
	
	
	public void runSwarmAi(AgentWorld world) {

		if (isTimeForUpdate()) {
			// System.out.println("ai running");
			ArrayList<ArrayList<AbstractAgent>> detectionList = detectionSystem.detectAllAgents(world);

			for (int i = 0; i < world.getAllAgents().size(); i++) {
				AbstractAgent agent = world.getAllAgents().get(i);
				Vector2 position = agent.getPossition().cpy();
				ArrayList<AbstractAgent> detectedAgents = detectionList.get(i);
				AbstractAgent targetAgent = null;

				if (agent.getClass() == PersuerAgent.class) {
					targetAgent = findClosestEvader(position, detectedAgents, targetAgent);

					if (targetAgent != null) {
						// System.out.println(agent.getAgentState());

						agent.setDirection(targetAgent.getPossition().cpy().sub(position.cpy()));
						agent.setAgentState(AgentState.PERSUER_PERSUIT);

						// Remove DeadAgents
						if (position.cpy().sub(targetAgent.getPossition().cpy()).len() <= deathDistance) {
							targetAgent.setDead(true);
							agent.setAgentState(AgentState.PERSUER_SEARCH);
						}

					} else if (targetAgent == null) {
						Vector2 targetdifference = new Vector2((float) (100 - Math.random() * 200),
								(float) (100 - Math.random() * 200));
						agent.setDirection(targetdifference);
						agent.setAgentState(AgentState.PERSUER_SEARCH);

					}

				}
				if (agent.getClass() == EvaderAgent.class) {
					Vector2 targetdifference = new Vector2((float) (100 - Math.random() * 200),
							(float) (100 - Math.random() * 200));
					agent.setDirection(targetdifference);
				}

			}
			startOfSim = System.currentTimeMillis();
		}

	}


	private AbstractAgent findClosestEvader(Vector2 position, ArrayList<AbstractAgent> detectedAgents,
			AbstractAgent targetAgentPlaceHolder) {
		Vector2 closestDistance = new Vector2();

		boolean evaderDetected = false;

		for (int j = 0; j < detectedAgents.size(); j++) {

			if (detectedAgents.get(j).getClass() == EvaderAgent.class) {
				if (evaderDetected == false) {
					Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());
					closestDistance = distance.cpy();
					targetAgentPlaceHolder = detectedAgents.get(j);
					evaderDetected = true;

				} else {

				Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());

					if (distance.len() < closestDistance.len()) {
						closestDistance = distance;
						targetAgentPlaceHolder = detectedAgents.get(j);

					}

				}

			}

		}
		// if (targetAgentPlaceHolder != null) {
		// System.out.println(targetAgentPlaceHolder.getClass());
		// }
		return targetAgentPlaceHolder;
	}

	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= targetUpdateRate;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

	
	
	
}
