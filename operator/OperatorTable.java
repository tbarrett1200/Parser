package operator;

import java.util.ArrayList;
import java.util.Arrays;

import main.ParserUtils;

public class OperatorTable {

	private static final PrecedenceLevel[] levels = { new PrecedenceLevel(Arity.UNARY, Associativity.NONE, Fixity.PREFIX, "+", "-", "++", "--"),
										 			  new PrecedenceLevel(Arity.UNARY, Associativity.NONE, Fixity.POSTFIX, "--", "++"),
										 			  new PrecedenceLevel(Arity.BINARY, Associativity.LEFT, Fixity.INFIX, "*", "/", "%" ),
										 			  new PrecedenceLevel(Arity.BINARY, Associativity.LEFT, Fixity.INFIX, "+", "-"),
										 			  new PrecedenceLevel(Arity.BINARY, Associativity.LEFT, Fixity.INFIX, "==", "!=", "<", "<=", ">", ">="),
										 			  new PrecedenceLevel(Arity.BINARY, Associativity.LEFT, Fixity.INFIX, "=", "+=", "-=", "*=", "/=", "%=") };
	
	public static final int MAX_PRECEDENCE = levels.length;
	
	private static String precedenceRegex[] = new String[MAX_PRECEDENCE];
	private static String operatorRegex;
	
	public static PrecedenceLevel getPrecedenceLevel(int precedence) {
		return levels[precedence-1];
	}
	

	public static String getRegex(int precedence) {
		String existing = precedenceRegex[precedence-1];
		if (existing != null) return existing;

		String operators[] = getPrecedenceLevel(precedence).getOperators();
		String generated = ParserUtils.generateRegex(Arrays.asList(operators));
		
		precedenceRegex[precedence-1] = generated;
		System.out.println(generated);

		return generated;
	}
	
	public static String getOperatorRegex() {
		if (operatorRegex != null) return operatorRegex;
		
		ArrayList<String> operators = new ArrayList<String>();
		
		for (PrecedenceLevel l: levels) {
			operators.addAll(Arrays.asList(l.getOperators()));
		}
		
		String generated = ParserUtils.generateRegex(operators);

		operatorRegex = generated;
		System.out.println(generated);
		return generated;
	}
	

}
