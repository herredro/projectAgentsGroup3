package game.systems;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class MovementSystem extends AbstractSystem {

	private long startOfSim;
	private final long movementUpdateRateMillsec = 500;
	private final int maxVelosity = 50;

	public MovementSystem() {
		startOfSim = System.currentTimeMillis();
	}

	public void moveAgent(AbstractAgent agent, Vector2 velocity) {

		agent.getPhysicsBody().setLinearVelocity(velocity);

	}

	@Override
	public void proccessStep(AgentWorld world, float delta) {
		if (isTimeForUpdate()) {

			for (int i = 0; i < world.getAllAgents().size(); i++) {
				moveAgent(world.getAllAgents().get(i),
						new Vector2((float) (maxVelosity - (2 * maxVelosity * Math.random())),
								(float) (maxVelosity - (2 * maxVelosity * Math.random()))));

			}
			startOfSim = System.currentTimeMillis();
	}
	}

	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= movementUpdateRateMillsec;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

}
