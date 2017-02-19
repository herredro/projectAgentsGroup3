package game.systems;

import agentDefinitions.AbstractAgent;

import com.badlogic.gdx.math.Vector2;

public class MovementSystem {

	public void moveAgent(AbstractAgent agent, Vector2 velocity) {

		agent.getPhysicsBody().setLinearVelocity(velocity);

	}

}
