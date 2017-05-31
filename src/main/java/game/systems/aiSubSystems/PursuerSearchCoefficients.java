package game.systems.aiSubSystems;

public class PursuerSearchCoefficients {

	private float separationCoef = 70;

	private float obstacleAvoidanceCoef = 20;

	private float randomComponentCoef = 10;

	public PursuerSearchCoefficients() {

	}

	public PursuerSearchCoefficients(float separationCoef, float obstacleAvoidanceCoef, float randomComponentCoef) {
		this.separationCoef = separationCoef;
		this.obstacleAvoidanceCoef = obstacleAvoidanceCoef;
		this.randomComponentCoef = randomComponentCoef;
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
