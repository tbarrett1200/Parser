import java.util.ArrayList;
import java.util.Scanner;

import statement.Statement;

public class Program {

	ArrayList<Statement> statements = new ArrayList<Statement>();

	public Program(Scanner scan) throws Exception {				
		
		try {
			while (true) {
				statements.add(Statement.parse(scan));
			}
		} catch (Exception e) {
			if (e.getMessage().equals("Parse Not Found")) {
				if (scan.hasNext())  throw new Exception("Syntax Error: Expecting Statement");
			}
			else throw e;
		}
	}
	
	@Override
	public String toString() {
	    String program = "Program Start\n";
	    
	    for (Statement d: statements) {
	    	program += d + "\n";
	    }
	    
	    program += "Program End\n";
	    
	    return program;
	}
}
