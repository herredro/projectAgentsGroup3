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
	private final long targetUpdateRate = AgentSimulatorConstants.aiDecisionsUpdate;

	// private final int seperationMagn = 1;
	// private final int followMagn = 1;
	// private final int randomMagn = 1;


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
				detectedAgents.remove(agent);

				if (agent.getClass() == PersuerAgent.class) {
					// System.out.println("start");

					Vector2 followDetectedComponent = calculateFollowComponent(position, detectedAgents);
					Vector2 seperationComponent = calculateSeperationComponent(position, detectedAgents);
					// System.out.println(seperationComponent);
					EvaderAgent targetAgent = (EvaderAgent) findClosestEvader(position, detectedAgents);

					if (targetAgent != null) {
							// System.out.println(agent.getAgentState());

						agent.setDirection(weightedSumComponentsPersuit(followDetectedComponent, seperationComponent,
								0.5));
						agent.setAgentState(AgentState.PERSUER_PERSUIT);

							// Remove DeadAgents
							if (position.cpy().sub(targetAgent.getPossition().cpy()).len() <= deathDistance) {
								targetAgent.setDead(true);
							// agent.setAgentState(AgentState.PERSUER_SEARCH);
							}

					} else if (targetAgent == null) {

						agent.setAgentState(AgentState.PERSUER_SEARCH);

						Vector2 destination = weightedSumComponentsSearch(seperationComponent, 5);

						agent.setDirection(destination);
				}

			}
			startOfSim = System.currentTimeMillis();
			}
		}

	}


	private Vector2 weightedSumComponentsPersuit(Vector2 followDetected, Vector2 seperation, double randomScale) {


		Vector2 sum = new Vector2();
		sum = followDetected.cpy().nor().scl(1)
				.add(seperation.cpy().scl((float) 10).add(calculateRandomComponent().nor().scl((float) randomScale)));
		return sum;

	}

	private Vector2 weightedSumComponentsSearch(Vector2 seperation, double randomScale) {

		Vector2 sum = new Vector2();
		sum = (seperation.cpy().scl((float) 5).add(calculateRandomComponent().nor().scl((float) randomScale)));
		return sum;

	}

	private Vector2 calculateRandomComponent() {

		return new Vector2((float) (10 - Math.random() * 20), (float) (10 - Math.random() * 20)).nor();
	}

	private Vector2 calculateSeperationComponent(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {
		

		Vector2 seperation= new Vector2();
		int counter = 0;
		

		for (int j = 0; j < detectedAgents.size(); j++) {
			if (detectedAgents.get(j).getClass() == PersuerAgent.class && j != 0) {
				Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());

				if (distance.len() < 20) {
					if (findClosestEvader(position, detectedAgents) != null) {

						Vector2 closestEvaderPos = findClosestEvader(position, detectedAgents).getPossition();
						if ((position.cpy().sub(closestEvaderPos.cpy()).len() < detectedAgents.get(j).getPossition()
								.cpy().sub(closestEvaderPos.cpy()).len())) {

							return new Vector2();

						}
					}
					// System.out.println(distance.len());
					if (distance.len() == 0) {

						seperation.add(new Vector2((float) ((int) 100 * (1 - 2 * Math.random())),
								(float) ((int) 100 * (1 - 2 * Math.random()))));
						// counter++;
					} else {
					seperation.add(distance.cpy());
						// System.out.println(seperation.len());
						// counter++;
					}
				}
				
			}
		}
		Vector2 returnVec = new Vector2(seperation).nor();
		// System.out.println(returnVec);
		return returnVec;
	}

	private Vector2 calculateFollowComponent(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {
		if (findClosestEvader(position, detectedAgents) != null) {
		Vector2 closestEvaderPos = findClosestEvader(position, detectedAgents).getPossition();
			// System.out.println(closestEvaderPos.cpy().sub(position));
		return closestEvaderPos.cpy().sub(position);
		}
		return new Vector2();
	}

	private AbstractAgent findClosestEvader(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {
		Vector2 closestDistance = new Vector2();
		EvaderAgent returnAgent = null;
		boolean evaderDetected = false;


		for (int j = 0; j < detectedAgents.size(); j++) {

			if (detectedAgents.get(j).getClass() == EvaderAgent.class) {
				if (evaderDetected == false) {
					//System.out.println("detection");
					Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());
					closestDistance = distance.cpy();
					returnAgent = (EvaderAgent) detectedAgents.get(j);
					evaderDetected = true;

				} else {

				Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());

					if (distance.len() < closestDistance.len()) {
						returnAgent = (EvaderAgent) detectedAgents.get(j);
						closestDistance = distance;


					}

				}

			}

		}

		return returnAgent;
	}

	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= targetUpdateRate;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

	
	
	
}
