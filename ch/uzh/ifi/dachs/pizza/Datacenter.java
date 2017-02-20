package ch.uzh.ifi.dachs.pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Datacenter {
	private int number_rows;
	private int columns;
	private int pool_number;
	
	private List<Row> rows;
	private List<Server> servers;

	private Pool[] pools;

	
	public Datacenter(int rows, int columns, int pool_number) {
		this.number_rows = rows;
		this.columns = columns;
		this.pool_number = pool_number;

		this.pools = new Pool[pool_number];
		for (int i = 0;i< pool_number;i++){
			this.pools[i] = new Pool();
		}


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
	
	public int getPool_number() {
		return pool_number;
	}

	public List<Row> getRows() {
		return rows;
	}

	public List<Server> getServers() {
		return servers;
	}
	
	public void RowAllocation(UtilityScoreNextServerStrategy utilityScoreNextServerStrategy, GreedyNextRowStrategy greedyNextRowStrategy) {
		Server server;
		Row row;
		while(!this.rowAllocationIsDone()) {
			server = utilityScoreNextServerStrategy.getNextServer();
			row = greedyNextRowStrategy.getNextRow(server);
			
		}
	}
	
	private boolean rowAllocationIsDone() {
		return false;
	}
	
	public static Datacenter createDatacenterFromFile(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		
		int rows = scanner.nextInt();
		int columns = scanner.nextInt();
		int unavailable_slots = scanner.nextInt();
		int pool_number = scanner.nextInt();
		int server_number = scanner.nextInt();
		
		Datacenter dc = new Datacenter(rows, columns, pool_number);		
		
		int urow;
		int ucolumn;
		while(scanner.hasNextLine() && unavailable_slots > 0) {
			urow = scanner.nextInt();
			ucolumn = scanner.nextInt();
			dc.setUnvailable(urow, ucolumn);
			unavailable_slots--;
		}
		
		int size;
		int capacity;
		while(scanner.hasNextLine() && server_number > 0) {
			size = scanner.nextInt();
			capacity = scanner.nextInt();
			dc.addServer(size, capacity);
			server_number--;
		}
		
		scanner.close();
		return dc;		
	}


	public int getWorstCaseCapacity(){
		int min = Integer.MAX_VALUE;

		for(Pool pool : this.pools){
			min = Math.min(min, pool.getWorstCaseCapacity());
		}

		return min;

	}

}
