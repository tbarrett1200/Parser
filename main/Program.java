package main;

import java.util.ArrayList;

import statement.DeclarationStatement;
import statement.Statement;

public class Program {

	private ArrayList<DeclarationStatement> statements;

	public Program(ArrayList<DeclarationStatement> statements) {
	    this.statements = statements;
	}
	
	public static Program parse(Scanner scan) throws Exception {
	    
	    ArrayList<DeclarationStatement> statements = new ArrayList<DeclarationStatement>();
	    
	    DeclarationStatement s = DeclarationStatement.parse(scan);
	    while (s != null) {
		statements.add(s);
		s = DeclarationStatement.parse(scan);
	    }
	
	    if (scan.hasNext()) throw new Exception("Syntax Error: Program: Expecting End Of File");
	    
	    return new Program(statements);
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
