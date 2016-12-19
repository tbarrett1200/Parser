package expression;

import java.util.ArrayList;
import java.util.List;

import main.ParserUtils;
import main.Scanner;

public class FunctionCall {
	
	private String identifier;
	private List<Expression> arguments;
	
	public FunctionCall(String identifier, List<Expression> arguments) {
		this.identifier = identifier;
		this.arguments = arguments;
	}
	
	public static FunctionCall parse(Scanner scan) throws Exception {
		if (scan.hasNext("[a-zA-Z$_][a-zA-Z1-9]*")) {
			if (scan.hasNext("\\(", 1)) {
				String identifier = scan.next();
				scan.next();
				
				List<Expression> arguments = new ArrayList<Expression>();
				
				Expression e = Expression.parse(scan);
				
				while (e != null) {
					arguments.add(e);
					if (scan.hasNext("\\)")) break;
					else ParserUtils.expect(scan, ",", "Parser Error: Exprected ','");
					e = Expression.parse(scan);
				}
				
				ParserUtils.expect(scan, "\\)", "Parser Error: Exprected ')'");
				
				return new FunctionCall(identifier, arguments);
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String str = identifier + "(";
		for (Expression e: arguments) {
			str += e + ", ";
		}
		
		str = str.substring(0, str.length()-2);
		
		return str + ")";
	}
}
