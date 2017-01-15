package ch.uzh.ifi.dachs.pizza;

import java.util.List;

public interface PizzaStrategy {
	List<Slice> computeSlices(Pizza pizza);
}
