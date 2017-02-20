package ch.uzh.ifi.dachs.pizza;

public class Row {

	private int[] columns;
	public static final int EMPTY = -1;
	public static final int UNAVAILABLE = -2;
	
	public Row(int columns) {
		this.columns = new int[columns];
		for(int i=0; i < columns; i++) {
			this.columns[i] = Row.EMPTY;
		}
	}
	
	public void setUnavailable(int column) {
		this.columns[column] = UNAVAILABLE;
	}
}
