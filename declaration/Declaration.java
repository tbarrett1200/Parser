package declaration;

import java.util.Scanner;

public class Declaration {

	public String type;
	public String identifier;
	
	public Declaration(String type, String identifier) {
		this.type = type;
		this.identifier = identifier;
	}
	
	public static Declaration parse(Scanner scan) throws Exception {
		Declaration d = VariableDeclaration.parse(scan);
		if (d!=null) return d;
		d = FunctionDeclaration.parse(scan);
		if (d!=null) return d;
		throw new Exception();
	}
}
