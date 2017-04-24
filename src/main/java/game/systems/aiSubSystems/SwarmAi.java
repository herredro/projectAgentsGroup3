package game.systems.aiSubSystems;

import java.util.ArrayList;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentState;
import agentDefinitions.AgentWorld;
import agentDefinitions.EvaderAgent;
import agentDefinitions.PersuerAgent;

import com.badlogic.gdx.math.Vector2;

public class SwarmAi {
	
	private int deathDistance = 30;

	private DetectionSystem detectionSystem;
	private long startOfSim;
	private final long targetUpdateRate = 500;


	public SwarmAi() {
		super();
		this.detectionSystem = new DetectionSystem();
		startOfSim = System.currentTimeMillis();

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
						System.out.println(agent.getAgentState());

						agent.setTargetPosition(targetAgent.getPossition().cpy());
						agent.setAgentState(AgentState.PERSUER_PERSUIT);

						if (position.cpy().sub(targetAgent.getPossition().cpy()).len() <= deathDistance) {
							targetAgent.setDead(true);
							// world.getPhysicsWorld().destroyBody(targetAgent.getPhysicsBody());
							agent.setAgentState(AgentState.PERSUER_SEARCH);
						}

					} else if (targetAgent == null) {
						Vector2 targetdifference = new Vector2((float) (100 - Math.random() * 200),
								(float) (100 - Math.random() * 200));
						agent.setTargetPosition(position.cpy().sub(targetdifference));
						agent.setAgentState(AgentState.PERSUER_SEARCH);

					}

				}
				if (agent.getClass() == EvaderAgent.class) {
					Vector2 targetdifference = new Vector2((float) (100 - Math.random() * 200),
							(float) (100 - Math.random() * 200));
					agent.setTargetPosition(position.cpy().sub(targetdifference));
				}

			}
			startOfSim = System.currentTimeMillis();
		}

	}


	private AbstractAgent findClosestEvader(Vector2 position, ArrayList<AbstractAgent> detectedAgents,
			AbstractAgent targetAgent) {
		Vector2 closestDistance = new Vector2();

		boolean evaderDetected = false;

		for (int j = 0; j < detectedAgents.size(); j++) {

			if (detectedAgents.get(j).getClass() == EvaderAgent.class) {
				if (evaderDetected == false) {
					Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());
					closestDistance = distance.cpy();
					targetAgent = detectedAgents.get(j);
					evaderDetected = true;

				} else {

				Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());

					if (distance.len() < closestDistance.len()) {
						closestDistance = distance;
						targetAgent = detectedAgents.get(j);

					}

				}

			}

		}
		if (targetAgent != null) {
			System.out.println(targetAgent.getClass());
		}
		return targetAgent;
	}

	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= targetUpdateRate;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

	
	
	
}
