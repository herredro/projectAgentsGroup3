package ui.rendering;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class RenderComponent {

	private Body physicsBody;
	private TextureRegion textureRegion;

	public RenderComponent(Body physicsBody, TextureRegion textureRegion) {
		this.physicsBody = physicsBody;
		this.textureRegion = textureRegion;
	}

	public Body getPhysicsBody() {
		return physicsBody;
	}

	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	public Vector2 getPosition() {
		return physicsBody.getPosition().cpy();
	}

}
