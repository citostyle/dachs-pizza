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
		
		System.out.println("Test");
		
		scanner.close();
	}
}
