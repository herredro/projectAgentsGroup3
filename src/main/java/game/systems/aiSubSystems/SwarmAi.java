package game.systems.aiSubSystems;

import static game.systems.aiSubSystems.CoefficientFactory.USED_PURSUER_COEFFICIENTS;
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
	private ObstacleEvasionCalculator  obstacleEvasionCalculator;

	private final float seperationRadius = 100;

	public SwarmAi() {
		super();
		this.detectionSystem = new DetectionSystem();
		startOfSim = System.currentTimeMillis();
		deathDistance = AgentSimulatorConstants.deathRadius;
		this.obstacleEvasionCalculator= new ObstacleEvasionCalculator();
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
				

					Vector2 followDetectedComponent = calculateFollowComponent(position, detectedAgents);
					Vector2 seperationComponent = calculateSeperationComponent(position, detectedAgents);
					Vector2 avoidObsComponent = obstacleEvasionCalculator.calculateComp(agent, world);
					// Vector2 avoidObsComponent = new Vector2();
					EvaderAgent targetAgent = (EvaderAgent) findClosestEvader(position, detectedAgents);
					
					if (targetAgent != null) {
							// System.out.println(agent.getAgentState());

						agent.setDirection(weightedSumComponentsPersuit(followDetectedComponent, seperationComponent,
								avoidObsComponent, USED_PURSUER_COEFFICIENTS.getPursuitCoefs().getRandomComponentCoef()));
						agent.setAgentState(AgentState.PERSUER_PERSUIT);

							// Remove DeadAgents
							if (position.cpy().sub(targetAgent.getPossition().cpy()).len() <= deathDistance) {
								targetAgent.setDead(true);
							// agent.setAgentState(AgentState.PERSUER_SEARCH);
							}

					} else if (targetAgent == null) {

						agent.setAgentState(AgentState.PERSUER_SEARCH);

						Vector2 destination = weightedSumComponentsSearch(seperationComponent, avoidObsComponent,
								USED_PURSUER_COEFFICIENTS.getSearchCoefs().getRandomComponentCoef());

						agent.setDirection(destination);
				}

			}
			startOfSim = System.currentTimeMillis();
			}
		}

	}


	private Vector2 weightedSumComponentsPersuit(Vector2 followDetected, Vector2 seperation,Vector2 obsAvoid ,double randomScale) {


		Vector2 sum = new Vector2();
		sum = followDetected
				.cpy()
				.nor()
				.scl(USED_PURSUER_COEFFICIENTS.getPursuitCoefs().getFollowDetectedCoef())
				.add(seperation.cpy().scl((float) USED_PURSUER_COEFFICIENTS.getPursuitCoefs().getSeparationCoef())
						.add(calculateRandomComponent().nor().scl((float) randomScale))
						.add(obsAvoid.scl((float) USED_PURSUER_COEFFICIENTS.getPursuitCoefs()
								.getObstacleAvoidanceCoef())));
		
		return sum;

	}

	private Vector2 weightedSumComponentsSearch(Vector2 seperation, Vector2 obsAvoid, double randomScale) {

		Vector2 sum = new Vector2();
		sum = (seperation.cpy().scl((float) USED_PURSUER_COEFFICIENTS.getSearchCoefs().getSeparationCoef())
				.add(calculateRandomComponent().nor().scl((float) randomScale)).add(obsAvoid
				.scl((float) USED_PURSUER_COEFFICIENTS.getSearchCoefs().getObstacleAvoidanceCoef())));
		// System.out.println(seperation.len());
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

				if (distance.len() < seperationRadius) {

					seperation.add(distance.cpy());
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
