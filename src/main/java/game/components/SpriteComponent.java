package game.components;

import lombok.Getter;
import lombok.Setter;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

@Getter
@Setter
public class SpriteComponent
		extends Component {

	/** The name of the texture file */
	private String name;
	/** Scale on each axis */
	private float scaleX;
	private float scaleY;
	/** Rotation with respect to bottom left corner */
	private float rotation;
	/** The actual sprite that will be drawn */
	private Sprite sprite;
	/** */
	private TextureRegion region;

	/** If false, RenderSystem.inserted() will be called */
	private boolean set = false;

	public SpriteComponent(String name) {
		this.name = name;
	}
}
