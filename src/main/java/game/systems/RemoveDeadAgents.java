package game.systems;

import game.systems.abstractDef.AbstractSystem;

import java.util.ArrayList;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

public class RemoveDeadAgents extends AbstractSystem {

	@Override
	public void proccessStep(AgentWorld world, float delta) {
		ArrayList<AbstractAgent> removeagents = new ArrayList<AbstractAgent>();
		for (int i = 0; i < world.getAllAgents().size(); i++) {
			if (world.getAllAgents().get(i).isDead()) {
				removeagents.add(world.getAllAgents().get(i));
			}
		}
		for (int j = 0; j < removeagents.size(); j++) {
			world.getAllAgents().remove(removeagents.get(j));
			world.getPhysicsWorld().destroyBody(removeagents.get(j).getPhysicsBody());
		}
	}

}
