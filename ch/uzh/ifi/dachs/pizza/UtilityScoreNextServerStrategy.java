package ch.uzh.ifi.dachs.pizza;

public class UtilityScoreNextServerStrategy extends NextServerStrategy {
	public UtilityScoreNextServerStrategy(Datacenter dc) {
		super(dc);
		// sort by utility and getNextServer returns by that
		// has to save state about what the current server is
	}

	@Override
	public Server getNextServer() {
		return null;
	}

}
