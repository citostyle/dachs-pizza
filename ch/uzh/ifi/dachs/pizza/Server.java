package ch.uzh.ifi.dachs.pizza;

public class Server {
	private int size;
	private int capacity;
	
	private int row;
	private int column;
	
	public Server(int size, int capacity) {
		this.size = size;
		this.capacity = capacity;
	}

	public int getRow(){
		return this.row;
	}

	public int getCapacity(){
		return this.capacity;
	}
}
