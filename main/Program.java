package main;

import java.util.ArrayList;
import java.util.List;

import statement.DeclarationStatement;
import statement.Statement;

public class Program {

	private List<DeclarationStatement> declarations;

	/**
	 * Constructor
	 * @param statements all global DeclarationStatements in the program
	 */
	private Program(ArrayList<DeclarationStatement> statements) {
	    this.declarations = statements;
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
	    if (declarations.size() == 0) return text;
	    for (Statement d: declarations.subList(0, declarations.size()-1)) text += d + "\n"; 
	    return text + declarations.get(declarations.size()-1);
	}
}
