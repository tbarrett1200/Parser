package statement;

import main.Lexer;

import declaration.Declaration;

public class DeclarationStatement extends Statement {
	public Declaration declaration;
	
	public DeclarationStatement(Declaration declaration) {
		this.declaration = declaration;
	}
	
	public static DeclarationStatement parse(Lexer scan) throws Exception {
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
