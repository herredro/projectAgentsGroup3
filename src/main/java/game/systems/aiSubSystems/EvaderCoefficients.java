package game.systems.aiSubSystems;

public class EvaderCoefficients {

	private EvaderEvasionCoefficients evasionCoefs;

	public EvaderCoefficients() {
		this.evasionCoefs = new EvaderEvasionCoefficients();
	}

	public EvaderCoefficients(EvaderEvasionCoefficients evasionCoefs) {
		this.evasionCoefs = evasionCoefs;
	}

	public EvaderEvasionCoefficients getEvasionCoefs() {
		return evasionCoefs;
	}

}
