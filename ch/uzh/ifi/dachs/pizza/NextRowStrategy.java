package ch.uzh.ifi.dachs.pizza;

public abstract class NextRowStrategy {
	protected Datacenter dc;

	public NextRowStrategy(Datacenter dc) {
		this.dc = dc;
	}
	
	abstract public Row getNextRow(Server s);
}