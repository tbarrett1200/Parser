package declaration;

import main.Scanner;

public class Declaration {

	public String type;
	public String identifier;
	
	public static String accept(Scanner scan, String pattern) {
		if (scan.hasNext(pattern)) {
			return scan.next(); 
		}
		return null;
	}
	
	public static String expect(Scanner scan, String pattern, String error) throws Exception {
		if (scan.hasNext(pattern)) {
			return scan.next(); 
		}
		throw new Exception(error);
	}
	
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