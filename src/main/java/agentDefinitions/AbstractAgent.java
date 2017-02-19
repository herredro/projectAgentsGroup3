package agentDefinitions;

import ui.rendering.RenderComponent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class AbstractAgent {


	protected Body physicsBody;

	protected RenderComponent renderData;

	protected Vector2 direction;

	public AbstractAgent(Body physicsBody, RenderComponent renderData) {
		super();
		this.physicsBody = physicsBody;
		this.renderData = renderData;
		this.direction = new Vector2();
	}

	public AbstractAgent(Body physicsBody, RenderComponent renderData, Vector2 direction) {
		super();
		this.physicsBody = physicsBody;
		this.renderData = renderData;
		this.direction = direction;
	}

	public Body getPhysicsBody() {
		return physicsBody;
	}

	public RenderComponent getRenderData() {
		return renderData;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public Vector2 getPossition() {
		return physicsBody.getPosition().cpy();
	}

}
