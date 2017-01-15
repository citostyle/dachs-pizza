package ch.uzh.ifi.dachs.pizza;

import java.util.List;

public class PizzaSolution {
	private List<Slice> slices;
	
	public PizzaSolution(List<Slice> slices) {
		this.slices = slices;
	}
	
	public int getTotalScore() {
		return slices.stream().mapToInt(slice -> slice.getNumCells()).sum();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(slices.size());
		sb.append("\n");
		for(Slice slice : slices) {
			sb.append(slice.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	public List<Slice> getSlices(){
		return slices;
	}
}
