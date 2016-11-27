package statement;

import java.util.Scanner;

public class Statement {

	public static Statement parse(Scanner scan) throws Exception {
		try {
			return DeclarationStatement.parse(scan);
		} catch (Exception e) {
			if (!e.getMessage().equals("Parse Not Found")) throw e;
		}

		try {
			return BlockStatement.parse(scan);
		} catch (Exception e) {
			if (!e.getMessage().equals("Parse Not Found")) throw e;
		}
		
		try {
			return ExpressionStatement.parse(scan);
		} catch (Exception e) {
			if (!e.getMessage().equals("Parse Not Found")) throw e;
		}
		
		
		throw new Exception("Parse Not Found");
	}
}
