package game.systems.abstractDef;

import agentDefinitions.AgentWorld;

public abstract class AbstractSystem {
	public abstract void proccessStep(AgentWorld world, float delta);
}
