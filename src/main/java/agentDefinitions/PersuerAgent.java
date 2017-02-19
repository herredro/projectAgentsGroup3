package agentDefinitions;

import ui.rendering.RenderComponent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class PersuerAgent extends AbstractAgent {

	public PersuerAgent(Body physicsBody, RenderComponent renderData) {
		super(physicsBody, renderData);

	}

	public PersuerAgent(Body physicsBody, RenderComponent renderData, Vector2 direction) {
		super(physicsBody, renderData, direction);

	}

}
