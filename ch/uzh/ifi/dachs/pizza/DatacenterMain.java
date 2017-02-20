//Foobar
package ch.uzh.ifi.dachs.pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class DatacenterMain {

	static {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement main = stack[stack.length - 1];
		PROGRAM_NAME = main.getClassName();
	}
	
	public static String PROGRAM_NAME;
	
	public static void main(String[] args) throws FileNotFoundException {
		// Parameter input filename
		if(args.length != 1) {
			System.out.println("Usage: " + DatacenterMain.PROGRAM_NAME + " input_filename");
			return;
		}
		
		String filename = args[0];
		Scanner scanner = new Scanner(new File(filename));
		
		
		
		int rows = scanner.nextInt();
		int columns = scanner.nextInt();
		int unavailable_slots = scanner.nextInt();
		int pool_number = scanner.nextInt();
		
		Datacenter dc = new Datacenter(rows, columns, unavailable_slots, pool_number);		
		
		while(scanner.hasNextLine() && unavailable_slots > 0) {
			
			unavailable_slots--;
		}
		
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
