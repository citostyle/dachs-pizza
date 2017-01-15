package ch.uzh.ifi.dachs.pizza;

import java.awt.Point;

public class Slice {
	private Point first;
	
	private Point second;
	
	public Slice(Point first, Point second) {
		this.first = first;
		this.second = second;
	}
	
	public Point getFirst() {
		return first;
	}

	public Point getSecond() {
		return second;
	}
	
	
	public String toString() {
		return String.format("%d %d %d %d", first.x, first.y, second.x, second.y);
	}
}
