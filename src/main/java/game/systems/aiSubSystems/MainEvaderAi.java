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
					Vector2 seperationComponent = calculateSeperationComponent(position, detectedAgents);
					Vector2 avoidObstacleComp = obstacleEvasionCalculator.calculateComp(agent, world);
					Vector2 destination = weightedSumComponents(avoidanceComponent, seperationComponent,
							avoidObstacleComp);

					agent.setDirection(destination.scl(1));
				}

			}
			startOfSim = System.currentTimeMillis();
		}

	}


	private Vector2 weightedSumComponents(Vector2 avoidance, Vector2 seperation, Vector2 avoidObstacleComp) {

		Vector2 sum = new Vector2();
		sum = avoidance.cpy().nor().scl(100).add(seperation.cpy().nor().scl(30).add(avoidObstacleComp.scl(1000)));
		// System.out.println(avoidObstacleComp.len());
		
		return sum;
	}

	
	private Vector2 calculateSeperationComponent(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {

		return new Vector2((float) (1 - Math.random() * 2), (float) (1 - Math.random() * 2));
	}

	private Vector2 calculateAvoidanceComponent(Vector2 position, ArrayList<AbstractAgent> detectedAgents) {
		Vector2 closestPercPos = findClosestPercPos(position, detectedAgents);
		if (position.cpy().sub(closestPercPos).len() <= AgentSimulatorConstants.detectionRadius) {
		return position.cpy().sub(closestPercPos);
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


	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= targetUpdateRate;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

	

}
