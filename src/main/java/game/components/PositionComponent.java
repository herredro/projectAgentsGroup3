package game.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

@Getter
@AllArgsConstructor
public class PositionComponent
		extends Component {

	@Setter
	private Vector2 position;

}
