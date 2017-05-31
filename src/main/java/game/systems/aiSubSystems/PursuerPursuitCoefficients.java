package game.systems.aiSubSystems;

public class PursuerPursuitCoefficients {

	private float followDetectedCoef = 50;

	private float separationCoef = 20;

	private float obstacleAvoidanceCoef = 20;

	private float randomComponentCoef = 10;

	public PursuerPursuitCoefficients() {
	}

	public PursuerPursuitCoefficients(float followDetectedCoef, float separationCoef, float obstacleAvoidanceCoef,
			float randomComponentCoef) {
		this.followDetectedCoef = followDetectedCoef;
		this.separationCoef = separationCoef;
		this.obstacleAvoidanceCoef = obstacleAvoidanceCoef;
		this.randomComponentCoef = randomComponentCoef;
	}

	public float getFollowDetectedCoef() {
		return followDetectedCoef;
	}

	public float getSeparationCoef() {
		return separationCoef;
	}

	public float getObstacleAvoidanceCoef() {
		return obstacleAvoidanceCoef;
	}

	public float getRandomComponentCoef() {
		return randomComponentCoef;
	}

}
