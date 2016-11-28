package statement;

import main.Lexer;

public class Statement {

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
	
	public static Statement parse(Lexer scan) throws Exception {	
		Statement s = null;
		if (s == null) s = DeclarationStatement.parse(scan);
		if (s == null) s = BlockStatement.parse(scan);
		if (s == null) s = ExpressionStatement.parse(scan);
		return s;
	}
}
