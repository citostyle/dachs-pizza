//Foobar
package ch.uzh.ifi.dachs.pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
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
		
		Pizza pizza = new Pizza(rows, columns, ingredients, max_cells);
		
		String line;
		int i = 0;
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			if(line.isEmpty()) {
				continue;
			}
			for(int j = 0; j < columns; j++) {
				pizza.setCell(i, j, line.charAt(j));
			}
			i++;
		}
		
		scanner.close();
		
		System.out.println(pizza);
		
		PizzaSolution greedy_solution = pizza.getSolution(new GreedyStrategy());
		System.out.println("Total score: " + greedy_solution.getTotalScore());
		System.out.println(greedy_solution);
		
		System.out.println("Is Valid: " + pizza.solutionIsValid(greedy_solution));
		//System.out.println(greedy_solution);

		PizzaSolution row_solution = pizza.getSolution(new RowStrategy());
		System.out.println("Total score: " + row_solution.getTotalScore());
		
		System.out.println("Is Valid: " + pizza.solutionIsValid(row_solution));
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
	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	public int getIngredients() {
		return ingredients;
	}

	public int getMax_cells() {
		return max_cells;
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				sb.append(this.asciiCellValue(this.pizza[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public boolean[][] getPizza() {
		return this.pizza;
	}
	
	public PizzaSolution getSolution(PizzaStrategy solution) {
		return solution.computeSlices(this);
	}

	public boolean solutionIsValid(PizzaSolution solution) {
		boolean[][] covered = new boolean[this.getRows()][this.getColumns()];
		for(Slice slice : solution.getSlices()) {
			for (int i = slice.getFirst().x; i <= slice.getSecond().x; i++) {
				for (int j = slice.getFirst().y; j <= slice.getSecond().y; j++) {
					if (covered[i][j]) return false;
					covered[i][j] = true;
				}
			}
		}
		return true;
	}


	public boolean sliceIsValid(Slice slice) {
		int numberM = 0;
		int numberT = 0;

		if (slice.getSecond().x < slice.getFirst().x || slice.getSecond().y < slice.getFirst().y){
			return false;
		}

		if (slice.getSecond().x >= this.getRows() || slice.getSecond().y >= this.getColumns()){
			return false;
		}

		for(int i = slice.getFirst().x; i <= slice.getSecond().x; i++)  {
			for(int j = slice.getFirst().y; j <= slice.getSecond().y; j++) {
				if(this.pizza[i][j] == Pizza.T) {
					numberT++;
				} else {
					numberM++;
				}
			}
		}
		return numberM >= this.ingredients && numberT >= this.ingredients && numberM + numberT <= this.max_cells;
	}
	
	public int getUniqueIngredientCount() {
		// not ideal, but at least there's an abstraction in place now
		return 2;
	}
}
