package game.systems;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWord;

import com.badlogic.gdx.math.Vector2;

public class MovementSystem extends AbstractSystem {

	public void moveAgent(AbstractAgent agent, Vector2 velocity) {

		agent.getPhysicsBody().setLinearVelocity(velocity);

	}

	@Override
	public void proccessStep(AgentWord world) {
		moveAgent(world.getAllAgents().get(0), new Vector2(5, 5));

	}

}
