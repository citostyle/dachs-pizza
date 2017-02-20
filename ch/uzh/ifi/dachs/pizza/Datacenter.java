//Foobar
package ch.uzh.ifi.dachs.pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Datacenter {

	static {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement main = stack[stack.length - 1];
		PROGRAM_NAME = main.getClassName();
	}
	
	public static String PROGRAM_NAME;
	
	public static void main(String[] args) throws FileNotFoundException {
		// Parameter input filename
		if(args.length != 1) {
			System.out.println("Usage: " + Datacenter.PROGRAM_NAME + " input_filename");
			return;
		}
		
		String filename = args[0];
		Scanner scanner = new Scanner(new File(filename));
		
		int rows = scanner.nextInt();
		int columns = scanner.nextInt();
		int ingredients = scanner.nextInt();
		int max_cells = scanner.nextInt();
		
		/*Pizza pizza = new Pizza(rows, columns, ingredients, max_cells);
		
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
		}*/
		
		scanner.close();
	}
}
