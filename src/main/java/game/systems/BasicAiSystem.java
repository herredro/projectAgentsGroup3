package game.systems;

import game.systems.systemUtil.AABBFindAllCallback;
import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class BasicAiSystem extends AbstractSystem {

	@Override
	public void proccessStep(AgentWorld world, float delta) {
		System.out.println("im running");
		for (int i = 0; i < world.getAllAgents().size(); i++) {
			AbstractAgent agent = world.getAllAgents().get(i);
			float radius = 30f;
			AABBFindAllCallback detection = new AABBFindAllCallback(world, agent.getPossition(), radius);

			Vector2 position = agent.getPossition();
			world.getPhysicsWorld().QueryAABB(detection, position.x - radius, position.y - radius, position.x + radius,
					position.y + radius);

			System.out.println(detection.getDetectedAgents().size());
			// for (int j = 0; j < detection.getDetectedAgents().size(); j++) {
			// System.out.println(detection.getDetectedAgents().get(i).getPossition());
			// }
	}

}
}
