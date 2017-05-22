package game.systems.systemUtil;

import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

public class AgentRayCast implements com.badlogic.gdx.physics.box2d.RayCastCallback {

	private AgentWorld world;
	private Vector2 detectedObstacle;

	public AgentRayCast(AgentWorld world) {
		this.world = world;

	}

	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
		// System.out.println(fixture.getUserData());
		Integer agentId = (Integer) fixture.getUserData();
		if (agentId == null) {

			detectedObstacle = point.cpy();
			// System.out.println(point);
			return fraction;
		}

		return -1;

	}

	public Vector2 getDetectedObstacle() {
		return detectedObstacle;
	}


}
