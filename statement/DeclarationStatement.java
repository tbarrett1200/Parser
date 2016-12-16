package statement;

import main.Scanner;

import declaration.Declaration;

import static main.ParserUtils.expect;

public class DeclarationStatement extends Statement {
	private Declaration declaration;
	
	public DeclarationStatement(Declaration declaration) {
		this.declaration = declaration;
	}
	
	public static DeclarationStatement parse(Scanner scan) throws Exception {
		Declaration declaration = Declaration.parse(scan);
		if (declaration == null) return null;
		expect(scan, ";", "Syntax Error: Expression Statement: Expecting ';'");
		return new DeclarationStatement(declaration);	
	}
	
	@Override
	public String toString() {
		return declaration.toString() + "; ";
	}
}
