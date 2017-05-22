package game.systems.aiSubSystems;

import game.systems.systemUtil.AgentRayCast;

import java.util.ArrayList;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;

public class ObstacleEvasionCalculator {
	ArrayList<Vector2> dirList;

	public ObstacleEvasionCalculator() {
		dirList = new ArrayList<Vector2>();
		Vector2 rayCastDir1 = new Vector2(0, 1);
		Vector2 rayCastDir2 = new Vector2(1, 0);
		Vector2 rayCastDir3 = new Vector2(0, -1);
		Vector2 rayCastDir4 = new Vector2(-1, 0);
		dirList.add(rayCastDir1);
		dirList.add(rayCastDir2);
		dirList.add(rayCastDir3);
		dirList.add(rayCastDir4);

	}

	public Vector2 calculateComp(AbstractAgent agent, AgentWorld world) {



		Vector2 ret = new Vector2();
		for (int i = 0; i < 4; i++) {



		Vector2 startPoint = agent.getPossition().cpy().add(new Vector2((float) 0.1,(float) 0.1));

			Vector2 endPoint = startPoint.cpy().add(dirList.get(i)).add(new Vector2((float) 0.1, (float) -0.1)).nor()
					.scl(300);
			AgentRayCast rayCast = new AgentRayCast(world);

			world.getPhysicsWorld().rayCast(rayCast, startPoint, endPoint);

			if (rayCast.getDetectedObstacle() == null) {

			} else {
				if (agent.getPossition().cpy().sub(rayCast.getDetectedObstacle().cpy()).len() <= 200) {
				ret = ret.add(rayCast.getDetectedObstacle().sub(agent.getPossition().cpy()));
					System.out.println("avoidance detected");
				}
			}
		}
		// System.out.println(ret.len());
		return ret.nor().scl(-1);
	}

}
