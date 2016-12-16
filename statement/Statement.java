package statement;

import main.Scanner;

public class Statement {

	public static Statement parse(Scanner scan) throws Exception {	
		Statement s = null;
		if (s == null) s = DeclarationStatement.parse(scan);
		if (s == null) s = BlockStatement.parse(scan);
		if (s == null) s = ExpressionStatement.parse(scan);
		return s;
	}
}
