package ch.uzh.ifi.dachs.pizza;

import java.util.ArrayList;
import java.util.List;

public class Datacenter {
	private int number_rows;
	private int columns;
	private int pool_number;
	
	private List<Row> rows;
	private List<Server> servers;
	
	public Datacenter(int rows, int columns, int pool_number) {
		this.number_rows = rows;
		this.columns = columns;
		this.pool_number = pool_number;
		
		this.rows = new ArrayList<Row>();
		for(int i = 0; i < number_rows; i++) {
			this.rows.add(new Row(columns));
		}
		
		this.servers = new ArrayList<Server>();
	}
	
	public void setUnvailable(int row, int column) {
		this.rows.get(row).setUnavailable(column);
	}

	public void addServer(int size, int capacity) {
		this.servers.add(new Server(size, capacity));
	}
}
