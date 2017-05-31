package game.systems.aiSubSystems;

public class CoefficientFactory {

	public static PursuerCoefficients USED_PURSUER_COEFFICIENTS = new PursuerCoefficients();
	public static EvaderCoefficients USED_EVADER_COEFFICIENTS = new EvaderCoefficients();

	public PursuerCoefficients makeDefaultPursuerCoefficients() {
		PursuerSearchCoefficients searchCoefficients = new PursuerSearchCoefficients();
		PursuerPursuitCoefficients pursuitCoefficients = new PursuerPursuitCoefficients();
		return new PursuerCoefficients(searchCoefficients, pursuitCoefficients);
	}

	public PursuerCoefficients makePursuerCoefficients(PursuerSearchCoefficients searchCoefficients,
			PursuerPursuitCoefficients pursuitCoefficients) {
		return new PursuerCoefficients(searchCoefficients, pursuitCoefficients);
	}

}
