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
		try {
			return VariableDeclaration.parse(scan);
		} catch (Exception e) {
			if (!e.getMessage().equals("Parse Not Found")) throw e;
		}
		
		try {
			return FunctionDeclaration.parse(scan);
		} catch (Exception e) {
			if (!e.getMessage().equals("Parse Not Found")) throw e;
		}
		
		throw new Exception("Parse Not Found");
	}
}