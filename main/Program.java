package main;

import java.util.ArrayList;
import java.util.List;

import statement.DeclarationStatement;
import statement.Statement;

public class Program {

	private List<DeclarationStatement> statements = new ArrayList<DeclarationStatement>();

	public Program(List<DeclarationStatement> statements) {
	    this.statements = statements;
	}
	
	/**
	 * Parses a program from the specified scanner
	 * @param scan the scanner to parse from
	 * @return a new program parsed from the specified scanner
	 * @throws Exception parse errors
	 */
	public static Program parse(Scanner scan) throws Exception {   
	    ArrayList<DeclarationStatement> statements = new ArrayList<DeclarationStatement>();
	    DeclarationStatement s;    
	    while ((s = DeclarationStatement.parse(scan)) != null) statements.add(s);
	    if (scan.hasNext()) throw new Exception("Syntax Error: Program: Expecting End Of File");  
	    return new Program(statements);
	}
	
	@Override
	public String toString() {
	    String text = ""; 
	    if (statements.size() == 0) return text;
	    for (Statement d: statements.subList(0, statements.size()-1)) text += d + "\n"; 
	    return text + statements.get(statements.size()-1);
	}
}
