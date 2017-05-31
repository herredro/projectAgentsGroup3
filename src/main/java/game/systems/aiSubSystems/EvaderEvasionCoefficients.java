package game.systems.aiSubSystems;

public class EvaderEvasionCoefficients {

	private float avoidCoef = 30;

	private float separationCoef = 15;

	private float obstacleAvoidanceCoef = 50;

	private float randomComponentCoef = 5;

	public EvaderEvasionCoefficients() {

	}

	public EvaderEvasionCoefficients(float avoidCoef, float separationCoef, float obstacleAvoidanceCoef,
			float randomComponentCoef) {
		this.avoidCoef = avoidCoef;
		this.separationCoef = separationCoef;
		this.obstacleAvoidanceCoef = obstacleAvoidanceCoef;
		this.randomComponentCoef = randomComponentCoef;
	}

	public float getAvoidCoef() {
		return avoidCoef;
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
