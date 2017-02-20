package ch.uzh.ifi.dachs.pizza;

public abstract class NextServerStrategy {
	protected Datacenter dc;

	public NextServerStrategy(Datacenter dc) {
		this.dc = dc;
	}
	
	abstract public Server getNextServer();
}
