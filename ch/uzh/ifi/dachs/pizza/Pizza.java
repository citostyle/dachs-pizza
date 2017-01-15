//Foobar
package ch.uzh.ifi.dachs.pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pizza {

	static {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement main = stack[stack.length - 1];
		PROGRAM_NAME = main.getClassName();
	}
	
	public static String PROGRAM_NAME;
	
	public static void main(String[] args) throws FileNotFoundException {
		// Parameter inpute filename
		if(args.length != 1) {
			System.out.println("Usage: " + Pizza.PROGRAM_NAME + " input_filename");
			return;
		}
		
		String filename = args[0];
		Scanner scanner = new Scanner(new File(filename));
		
		int rows = scanner.nextInt();
		int columns = scanner.nextInt();
		int ingredients = scanner.nextInt();
		int max_cells = scanner.nextInt();
		
		Pizza solution = new Pizza(rows, columns, ingredients, max_cells);
		
		String line;
		int i = 0;
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			if(line.isEmpty()) {
				continue;
			}
			for(int j = 0; j < columns; j++) {
				solution.setCell(i, j, line.charAt(j));
			}
			i++;
		}
		
		scanner.close();
		
		System.out.println(solution.asciiMatrix());
	}
	
	private int rows;
	private int columns;
	private int ingredients;
	private int max_cells;
	
	private static final boolean T = true;
	private static final boolean M = false;
	
	private boolean pizza[][];
	
	public Pizza(int rows, int columns, int ingredients, int max_cells) {
		this.rows = rows;
		this.columns = columns;
		this.ingredients = ingredients;
		this.max_cells = max_cells;
		
		this.pizza = new boolean[rows][columns];
	}
	
	private boolean parseCellValue(char cell) {
		return cell == 'T';
	}
	
	private String asciiCellValue(boolean cell) {
		return cell ? "T" : "M";
	}

	public void setCell(int row, int column, char cell) {
		this.pizza[row][column] = this.parseCellValue(cell);
	}
	
	public String asciiMatrix() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				sb.append(this.asciiCellValue(this.pizza[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
