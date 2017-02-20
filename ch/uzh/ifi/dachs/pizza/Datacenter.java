package ch.uzh.ifi.dachs.pizza;

import java.util.ArrayList;
import java.util.List;

public class Datacenter {
	private int number_rows;
	private int columns;
	private int unavailable_slots;
	private int pool_number;
	
	private List<Row> rows;
	
	public Datacenter(int rows, int columns, int unavailable_slots, int pool_number) {
		this.number_rows = rows;
		this.columns = columns;
		this.unavailable_slots = unavailable_slots;
		this.pool_number = pool_number;
		
		this.rows = new ArrayList<Row>();
		for(int i = 0; i < number_rows; i++) {
			this.rows.add(new Row(columns));
		}
	}
}
