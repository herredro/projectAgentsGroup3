package game.systems.aiSubSystems;

public class PursuerCoefficients {

	private PursuerSearchCoefficients searchCoefs;

	private PursuerPursuitCoefficients pursuitCoefs;

	public PursuerCoefficients() {
		this.searchCoefs = new PursuerSearchCoefficients();
		this.pursuitCoefs = new PursuerPursuitCoefficients();
	}

	public PursuerCoefficients(PursuerSearchCoefficients searchCoefs, PursuerPursuitCoefficients pursuitCoefs) {
		this.searchCoefs = searchCoefs;
		this.pursuitCoefs = pursuitCoefs;
	}

	public PursuerSearchCoefficients getSearchCoefs() {
		return searchCoefs;
	}

	public PursuerPursuitCoefficients getPursuitCoefs() {
		return pursuitCoefs;
	}


}
