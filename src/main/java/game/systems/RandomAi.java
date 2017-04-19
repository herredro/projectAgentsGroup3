package game.systems;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class RandomAi extends AbstractSystem {
	private long startOfSim;
	private final long movementUpdateRateMillsec = 1000;

	public RandomAi() {
		startOfSim = System.currentTimeMillis();

	}

	@Override
	public void proccessStep(AgentWorld world, float delta) {
		if (isTimeForUpdate()) {
		for (int i = 0; i < world.getAllAgents().size(); i++) {
			AbstractAgent agent = world.getAllAgents().get(i);
			Vector2 position = agent.getPossition();
			Vector2 targetdifference = new Vector2((float) (10 - Math.random() * 20), (float) (10 - Math.random() * 20));
				agent.setTargetPosition(position.cpy().sub(targetdifference));

		}
		}
	}

	private boolean isTimeForUpdate() {
		return trackTimeElapsed() >= movementUpdateRateMillsec;
	}

	private int trackTimeElapsed() {
		return (int) (System.currentTimeMillis() - startOfSim);
	}

}
