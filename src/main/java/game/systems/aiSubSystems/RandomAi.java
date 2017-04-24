package game.systems.aiSubSystems;

import game.systems.abstractDef.AbstractSystem;
import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class RandomAi extends AbstractSystem {
	private long startOfSim;
	private final long targetUpdateRate = 5000;

	public RandomAi() {
		startOfSim = System.currentTimeMillis();

	}

	@Override
	public void proccessStep(AgentWorld world, float delta) {
		if (isTimeForUpdate()) {
			System.out.println("ai running");
		for (int i = 0; i < world.getAllAgents().size(); i++) {
			AbstractAgent agent = world.getAllAgents().get(i);
			Vector2 position = agent.getPossition();
				Vector2 targetdifference = new Vector2((float) (100 - Math.random() * 200),
						(float) (100 - Math.random() * 200));
				agent.setTargetPosition(position.cpy().sub(targetdifference));

		}
			startOfSim = System.currentTimeMillis();
		}
	}

	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= targetUpdateRate;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

}
