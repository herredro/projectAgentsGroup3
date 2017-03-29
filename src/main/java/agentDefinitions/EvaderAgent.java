package agentDefinitions;

import ui.rendering.RenderComponent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class EvaderAgent extends AbstractAgent {
	public EvaderAgent(Body physicsBody, RenderComponent renderData) {
		super(physicsBody, renderData);

	}

	public EvaderAgent(Body physicsBody, RenderComponent renderData, Vector2 direction) {
		super(physicsBody, renderData, direction);

	}

}
