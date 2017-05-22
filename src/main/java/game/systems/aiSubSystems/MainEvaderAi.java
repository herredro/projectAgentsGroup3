package game.systems.aiSubSystems;

import game.AgentSimulatorConstants;

import java.util.ArrayList;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;
import agentDefinitions.EvaderAgent;
import agentDefinitions.PersuerAgent;

import com.badlogic.gdx.math.Vector2;

public class MainEvaderAi {
	


	private DetectionSystem detectionSystem;
	private long startOfSim;
	private final long targetUpdateRate = 500;
	private ObstacleEvasionCalculator obstacleEvasionCalculator;
	private float seperationRadius = 40;


	public MainEvaderAi() {
		super();
		this.detectionSystem = new DetectionSystem();
		startOfSim = System.currentTimeMillis();
		this.obstacleEvasionCalculator = new ObstacleEvasionCalculator();


	}
	
	
	public void runEvasionAi(AgentWorld world) {

		if (isTimeForUpdate()) {
			// System.out.println("ai running");
			ArrayList<ArrayList<AbstractAgent>> detectionList = detectionSystem.detectAllAgents(world);

			for (int i = 0; i < world.getAllAgents().size(); i++) {
				AbstractAgent agent = world.getAllAgents().get(i);
				Vector2 position = agent.getPossition().cpy();
				ArrayList<AbstractAgent> detectedAgents = detectionList.get(i);


				if (agent.getClass() == EvaderAgent.class) {

					Vector2 avoidanceComponent = calculateAvoidanceComponent(position, detectedAgents);
					Vector2 seperationComponent = calculateRandomComponent(position, detectedAgents);
					Vector2 avoidObstacleComp = obstacleEvasionCalculator.calculateComp(agent, world);
					Vector2 sepeVector2 = calculateSeperationComponent(position, detectedAgents);
					Vector2 destination = weightedSumComponents(avoidanceComponent, seperationComponent,
							avoidObstacleComp, sepeVector2);
					

					agent.setDirection(destination.scl(1));
				}

			}
			startOfSim = System.currentTimeMillis();
		}

	}


	private Vector2 weightedSumComponents(Vector2 avoidance, Vector2 random, Vector2 avoidObstacleComp,
 Vector2 sepr) {

		Vector2 sum = new Vector2();
		sum = avoidance.cpy().nor().scl(100).add(random.cpy().nor().scl(10).add(avoidObstacleComp.scl(50)))
				.add((sepr).nor().scl(30));
		// System.out.println(sepr.len());
		// System.out.println(avoidance);
		return sum;
	}

	
	private Vector2 calculateRandomComponent(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {

		return new Vector2((float) (1 - Math.random() * 2), (float) (1 - Math.random() * 2));
	}

	private Vector2 calculateAvoidanceComponent(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {
		Vector2 closestPercPos = findClosestPercPos(position, detectedAgents);

		if (closestPercPos.len() <= AgentSimulatorConstants.detectionRadius) {
			// System.out.println("persuer detected");
			return closestPercPos.scl(1);
		}
		return new Vector2();
	}

	private Vector2 findClosestPercPos(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {
		Vector2 closestDistance = new Vector2();
	
		boolean persuerDetected = false;

	
		for (int j = 0; j < detectedAgents.size(); j++) {
	
			if (detectedAgents.get(j).getClass() == PersuerAgent.class) {
				if (persuerDetected == false) {
					Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());
					closestDistance = distance.cpy();

					persuerDetected = true;
	
				} else {
	
				Vector2 distance = position.cpy().sub(detectedAgents.get(j).getPossition().cpy());
	
					if (distance.len() < closestDistance.len()) {
						closestDistance = distance;

	
					}
	
				}
	
			}
	
		}

		return closestDistance;
	}

	private Vector2 calculateSeperationComponent(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {

		Vector2 seperation = new Vector2();
		int counter = 0;

		for (int j = 0; j < detectedAgents.size(); j++) {
			if (detectedAgents.get(j).getClass() == EvaderAgent.class && j != 0) {
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

	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= targetUpdateRate;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

	

}
