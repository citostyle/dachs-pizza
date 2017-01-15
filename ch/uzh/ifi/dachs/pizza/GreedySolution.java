package ch.uzh.ifi.dachs.pizza;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GreedySolution implements Solution {
	
	@Override
	public List<Slice> computeSlices(Pizza pizza) {
		
		List<Slice> slices = new ArrayList<Slice>();
		slices.add(new Slice(new Point(0, 0), new Point(2, 1)));
		slices.add(new Slice(new Point(0, 2), new Point(2, 2)));
		
		return slices; 
	}

}
