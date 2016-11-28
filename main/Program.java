package main;
import java.util.ArrayList;

import statement.Statement;

public class Program {

	ArrayList<Statement> statements = new ArrayList<Statement>();

	public Program(Lexer scan) throws Exception {				
		Statement s = Statement.parse(scan);
		
		while (s != null) {
			statements.add(s);
			s = Statement.parse(scan);
		}
	
		if (scan.hasNext()) throw new Exception("Syntax Error: Program: Expecting End Of File");
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
