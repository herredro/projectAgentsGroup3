package game.systems;

import agentDefinitions.AbstractAgent;
import agentDefinitions.AgentWord;
import agentDefinitions.Obstacles;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class RenderSystem extends AbstractSystem {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private AgentWord world;

	ShapeRenderer renderer = new ShapeRenderer();

	public RenderSystem(OrthographicCamera camera, AgentWord world) {
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

			renderer.polygon(obs.getVertices());

			renderer.end();
		}
		for (AbstractAgent agent : world.getAllAgents()) {
			renderer.begin(ShapeType.Line);

			renderer.circle(agent.getPhysicsBody().getPosition().x, agent.getPhysicsBody().getPosition().y, 5);

			renderer.end();
		}
		end();
	}

	@Override
	public void proccessStep(AgentWord world) {
		renderEverything();

	}

}
