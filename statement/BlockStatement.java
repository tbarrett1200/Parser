package statement;

import java.util.ArrayList;
import main.Scanner;

public class BlockStatement extends Statement {

	ArrayList<Statement> statements = new ArrayList<Statement>();
	
	public BlockStatement(ArrayList<Statement> statements) {
		this.statements = statements;
	}
	
	public static BlockStatement parse(Scanner scan) throws Exception {
		if (accept(scan,"\\{") == null) return null;
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		Statement s = Statement.parse(scan);
		
		while (s != null) {
			statements.add(s);
			s = Statement.parse(scan);
		}
		
		expect(scan, "\\}", "Syntax Error: Block Statement: Expecting '}'");
		
		return new BlockStatement(statements);
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
