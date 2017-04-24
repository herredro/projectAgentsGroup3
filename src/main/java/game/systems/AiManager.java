package game.systems;

import game.systems.abstractDef.AbstractSystem;
import game.systems.aiSubSystems.RandomAi;
import game.systems.aiSubSystems.SwarmAi;
import agentDefinitions.AgentWorld;

public class AiManager extends AbstractSystem {

	private RandomAi randomAi;
	private SwarmAi basicSwarmAi;

	public AiManager() {
		super();
		this.randomAi = new RandomAi();
		this.basicSwarmAi = new SwarmAi();
	}

	@Override
	public void proccessStep(AgentWorld world, float delta) {
		// randomAi.proccessStep(world, delta);
		basicSwarmAi.runSwarmAi(world);

	}

}
