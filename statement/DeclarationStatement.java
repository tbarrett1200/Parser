package statement;

import java.util.Scanner;

import declaration.Declaration;

public class DeclarationStatement extends Statement {
	public Declaration declaration;
	
	public DeclarationStatement(Declaration declaration) {
		this.declaration = declaration;
	}
	
	public static DeclarationStatement parse(Scanner scan) throws Exception {
		Declaration declaration = Declaration.parse(scan);
			
		if (scan.hasNext(";")) {
			scan.next();
		} else throw new Exception("Syntax Error: Declaratation Statement: Expecting ';'");
			
		return new DeclarationStatement(declaration);	
	}
	
	@Override
	public String toString() {
		return declaration.toString() + "; ";
	}
}
