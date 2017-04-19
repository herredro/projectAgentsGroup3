package game.systems;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class MovementSystem extends AbstractSystem {

	private final int maxVelosity = 1;
	private long startOfSim;
	private final long movementUpdateRateMillsec = 500;

	public MovementSystem() {
		startOfSim = System.currentTimeMillis();
	}

	@Override
	public void proccessStep(AgentWorld world, float delta) {
		if (isTimeForUpdate()) {
	
			for (int i = 0; i < world.getAllAgents().size(); i++) {
				moveAgent(world.getAllAgents().get(i));
			}
			startOfSim = System.currentTimeMillis();
		}
	}

	public void moveAgent(AbstractAgent agent) {
		if (agent.getTargetPosition() != null) {
		Vector2 velocity = agent.getTargetPosition().cpy().sub(agent.getPossition().cpy()).nor();

	
		agent.getPhysicsBody().setLinearVelocity(velocity.scl(maxVelosity));
		}

	}

	

	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= movementUpdateRateMillsec;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

}
