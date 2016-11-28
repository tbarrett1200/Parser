package declaration;

import main.Lexer;

public class Declaration {

	public String type;
	public String identifier;
	
	public static String accept(Lexer scan, String pattern) {
		if (scan.hasNext(pattern)) {
			return scan.next(); 
		}
		return null;
	}
	
	public static String expect(Lexer scan, String pattern, String error) throws Exception {
		if (scan.hasNext(pattern)) {
			return scan.next(); 
		}
		throw new Exception(error);
	}
	
	public Declaration(String type, String identifier) {
		this.type = type;
		this.identifier = identifier;
	}
	
	public static Declaration parse(Lexer scan) throws Exception {
		Declaration d = null;
		if (d == null) d = VariableDeclaration.parse(scan);
		if (d == null) d = FunctionDeclaration.parse(scan);
		return d;
		
	}
}