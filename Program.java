import java.util.HashMap;
import java.util.Scanner;

import declaration.Declaration;
import declaration.FunctionDeclaration;
import declaration.VariableDeclaration;

public class Program {

	HashMap<String, Declaration> variables = new HashMap<String, Declaration>();
	HashMap<String, Declaration> functions = new HashMap<String, Declaration>();

	public Program(Scanner scan) {				
		try {
			while (true) {
				Declaration d = Declaration.parse(scan);
				if (d instanceof VariableDeclaration) variables.put(d.identifier, d);
				else if (d instanceof FunctionDeclaration) functions.put(d.identifier, d);

			}
		} catch (Exception e) {
			if (!functions.containsKey("main")) {
				System.out.println("Error: no main method found");
			}
		}
	}
	
	@Override
	public String toString() {
	    String program = "Program Start\n";
	    
	    for (Declaration d: variables.values()) {
		program += d;
	    }
	    
	    for (Declaration d: functions.values()) {
		program += d;
	    }
	    
	    program += "Program End\n";
	    
	    return program;
	}
}
