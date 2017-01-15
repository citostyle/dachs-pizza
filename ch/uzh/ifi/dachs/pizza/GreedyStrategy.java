package ch.uzh.ifi.dachs.pizza;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GreedyStrategy implements PizzaStrategy {
	
	private int taken[][]; 
	
	private Pizza pizza;
	private int ingredient;
	private List<Slice> slices = new ArrayList<Slice>();
	
	@Override
	public List<Slice> computeSlices(Pizza pizza) {
		
		//initialize taken array
		taken = new int[pizza.getRows()][pizza.getColumns()];
		
		//initialize members
		this.pizza = pizza;
		
		Point currentPosition = new Point(0, 0);
		Point lastPosition = new Point(pizza.getRows()-1, pizza.getColumns()-1);
		int cellCount = this.pizza.getMax_cells();
		// todo: extract method isFinished() 
		while(!currentPosition.equals(lastPosition)) {
			Slice candidate = this.getFirstCandidate(currentPosition, cellCount);
			if(candidate != null) {
				this.slices.add(candidate);
			}
			currentPosition = this.getNextPosition(currentPosition);
		}
		return slices; 
	}
	
	private Point getNextPosition(Point cell) {
		Point next = new Point();
		next.y = (cell.y + 1) % this.pizza.getColumns();
		next.x = cell.x;
		if(next.y == 0) {
			next.x += 1; 
		}
		return next;
	}

	private Slice getFirstCandidate(Point cell, int cellCount) {
		if(this.taken[cell.x][cell.y] != 0) {
			return null;
		}

		if(cellCount < this.pizza.getIngredients()*this.pizza.getUniqueIngredientCount()) {
			return null;
		}
		
		for(IntPair pair : this.getCandidatePairs(cellCount)) {
			Slice candiateSlice = new Slice(cell, 
					new Point(cell.x + pair.first - 1, cell.y + pair.second - 1));
			if(this.pizza.sliceIsValid(candiateSlice) && !sliceTaken(candiateSlice)) {
				fillTaken(candiateSlice);
				return candiateSlice;
			}
		}
		
		return getFirstCandidate(cell, cellCount-1);
	}
	
	private boolean sliceTaken(Slice slice) {
		for(int i = slice.getFirst().x; i <= slice.getSecond().x; i++)  {
			for(int j = slice.getFirst().y; j <= slice.getSecond().y; j++) {
				if(taken[i][j] != 0) {
					return true;
				}
			}
		}
		return false;
	}

	private void fillTaken(Slice slice) {
		for(int i = slice.getFirst().x; i <= slice.getSecond().x; i++)  {
			for(int j = slice.getFirst().y; j <= slice.getSecond().y; j++) {
				taken[i][j] = 1;
			}
		}		
	}
	
	class IntPair {
		public int first;
		public int second;
		
		public IntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
	
	private List<IntPair> getCandidatePairs(int n) {
		List<Integer> factors = this.getFactors(n);
		List<IntPair> pairs = new ArrayList<IntPair>();
		
		for(Integer factor : factors) {
			int second = n/factor;
			pairs.add(new IntPair(factor, second));
			if(factor != second) {
				pairs.add(new IntPair(second, factor));
			}
		}	
		return pairs;
	}
	
	private List<Integer> getFactors(int n) {
		List<Integer> factors = new ArrayList<Integer>();
		for(int i=1; i <= Math.floor(Math.sqrt(n)); i++) {
			if(n % i == 0) {
				factors.add(i);
			}
		}
		return factors;
	}

}
