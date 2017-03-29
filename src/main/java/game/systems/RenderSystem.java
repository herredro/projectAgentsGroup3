package game.systems;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWorld;
import agentDefinitions.EvaderAgent;
import agentDefinitions.Obstacles;
import agentDefinitions.PersuerAgent;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class RenderSystem extends AbstractSystem {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private AgentWorld world;

	private ShapeRenderer renderer = new ShapeRenderer();

	public RenderSystem(OrthographicCamera camera, AgentWorld world) {
		super();
		this.camera = camera;
		this.world = world;
		this.batch = new SpriteBatch();
	}

	void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	void end() {
		batch.end();
	}

	public void renderEverything() {
		begin();
		renderer.setProjectionMatrix(camera.combined);

		for (Obstacles obs : world.getObstacList()) {
			renderer.begin(ShapeType.Line);
			renderer.setColor(Color.WHITE);
			renderer.polygon(obs.getVertices());

			renderer.end();
		}
		for (AbstractAgent agent : world.getAllAgents()) {
			renderer.begin(ShapeType.Line);
			if (agent.getClass() == PersuerAgent.class) {
				renderer.setColor(Color.GREEN);
				renderer.circle(agent.getPhysicsBody().getPosition().x, agent.getPhysicsBody().getPosition().y, 5);
			}
			if (agent.getClass() == EvaderAgent.class) {
				renderer.setColor(Color.RED);
				renderer.circle(agent.getPhysicsBody().getPosition().x, agent.getPhysicsBody().getPosition().y, 5);
			}
			renderer.end();
		}
		end();
	}

	@Override
	public void proccessStep(AgentWorld world, float delta) {
		renderEverything();

	}

}
