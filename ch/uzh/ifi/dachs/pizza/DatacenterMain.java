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
		Datacenter dc = Datacenter.createDatacenterFromFile(filename);
	}
}
