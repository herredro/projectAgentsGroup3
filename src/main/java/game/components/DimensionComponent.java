package game.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import com.artemis.Component;

/**
 * Immutable class to store the dimensions of an object for easy retrieval
 * 
 * @author Giannis Papadopoulos
 */
@AllArgsConstructor
@Getter
@ToString
public class DimensionComponent
		extends Component {

	private final float length;
	private final float width;

}
