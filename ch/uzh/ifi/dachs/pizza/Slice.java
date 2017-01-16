package ch.uzh.ifi.dachs.pizza;

import java.awt.Point;

public class Slice {
	private Point first;
	
	private Point second;

	private int numCells;
	
	public Slice(Point first, Point second) {
		this.first = first;
		this.second = second;
		this.numCells = (second.x - first.x+1) * (second.y - first.y+1);
	}
	
	public Point getFirst() {
		return first;
	}

	public Point getSecond() {
		return second;
	}

	public int getNumCells() { return numCells;	}

	public String toString() {
		return String.format("%d %d %d %d", first.x, first.y, second.x, second.y);
	}
}
