package agentDefinitions;

import ui.rendering.RenderComponent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Obstacles {

	private Body physicsBody;

	private RenderComponent renderData;

	private Vector2[] vertexList;

	public Obstacles(Body physicsBody, RenderComponent renderData, Vector2[] vertexList) {
		this.setPhysicsBody(physicsBody);
		this.setRenderData(renderData);
		this.vertexList = vertexList;


	}

	public RenderComponent getRenderData() {
		return renderData;
	}

	public void setRenderData(RenderComponent renderData) {
		this.renderData = renderData;
	}

	public Body getPhysicsBody() {
		return physicsBody;
	}

	public void setPhysicsBody(Body physicsBody) {
		this.physicsBody = physicsBody;
	}

	public float[] getVertices() {
		float[] returnStuff = new float[vertexList.length * 2];
		for (int i = 0; i < vertexList.length; i++) {
			returnStuff[2 * i] = vertexList[i].x + physicsBody.getPosition().x;
			returnStuff[2 * i + 1] = vertexList[i].y + physicsBody.getPosition().y;

		}
		return returnStuff;
	}

}
