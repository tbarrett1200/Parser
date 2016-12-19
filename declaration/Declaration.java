package declaration;

import main.Scanner;

public class Declaration {

	protected String type;
	protected String identifier;

	
	public Declaration(String type, String identifier) {
		this.type = type;
		this.identifier = identifier;
	}
	
	public static Declaration parse(Scanner scan) throws Exception {
		Declaration d = null;
		if (d == null) d = VariableDeclaration.parse(scan);
		if (d == null) d = FunctionDeclaration.parse(scan);
		return d;
		
	}
}