package game.systems.systemUtil;



import java.util.ArrayList;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

public class AABBFindAllCallback implements QueryCallback {

/** The game world, used for entity retrieval in order to pass them to the predicate */
private AgentWorld world;

/** The center of the querying rectangle, the dimensions are specified in the query call itself */
private Vector2 origin;

/** The radius for the search (this is needed because the query is run on a rectangle) */
private float radius;

private ArrayList<AbstractAgent> detectedAgents;




public AABBFindAllCallback(AgentWorld world, Vector2 origin, float radius) {
	this.world = world;
	this.origin = origin;
	this.radius = radius;
	detectedAgents= new ArrayList<AbstractAgent>();	
}

@Override
	public boolean reportFixture(Fixture fixture) {
		Integer agentId = (Integer) fixture.getUserData();
		if (agentId == null) {
			// Fixture was not an agent
			return true;
		}

		AbstractAgent agent = world.getIdMap().get(agentId);
		Vector2 position = agent.getPossition();
		if (position.dst(origin) < radius) {
			detectedAgents.add(agent);
		}

	return true;
}

	public ArrayList<AbstractAgent> getDetectedAgents() {
		return detectedAgents;
}

}

