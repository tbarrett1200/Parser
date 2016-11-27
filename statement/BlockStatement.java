package statement;

import java.util.ArrayList;
import java.util.Scanner;

public class BlockStatement extends Statement {

	ArrayList<Statement> statements = new ArrayList<Statement>();
	
	public BlockStatement(ArrayList<Statement> statements) {
		this.statements = statements;
	}
	
	public static BlockStatement parse(Scanner scan) throws Exception {
		if (scan.hasNext("\\{")) scan.next();
		else throw new Exception("Parse Not Found");
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		
		try {
			while(true) statements.add(Statement.parse(scan));
		} catch (Exception e) {
			if (e.getMessage().equals("Parse Not Found")) {
				 if (scan.hasNext("\\}")) {
					 scan.next();
					 return new BlockStatement(statements);
				 }
				 else throw new Exception("Syntax Error");
			} else throw e;
		}
		
	}
	
	@Override
	public String toString() {
		  String program = "{\n";
		    
		    for (Statement d: statements) {
		    	program += d + "\n";
		    }
		    
		    program += "}\n";
		    
		    return program;
	}
}
