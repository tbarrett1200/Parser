package expression;

import main.Scanner;
import operator.OperatorTable;

public abstract class Expression {	
	

	/**
	 * Parses an expression from the specified Lexer, starting from the highest precedence in the operatorTable.
	 * This method recursively parses all lower precedences using data from the operatorTable to build the expression
	 * @param scan			the Lexer to be used
	 * @return				the expression parsed from the specified Lexer	
	 * @throws Exception
	 */
	public static Expression parse(Scanner Lexer) throws Exception {
		return parseAtPrecedence(Lexer,OperatorTable.MAX_PRECEDENCE);
	}
	
	/**
	 * Parses a sub-expression from the specified Lexer, starting from the specified precedence.
	 * This method recursively parses all lower precedences using data from the operatorTable to build the expression
	 * @param scan			the Lexer to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression at the specified precedence
	 * @throws Exception
	 */
	protected static Expression parseAtPrecedence(Scanner scan, int precedence) throws Exception {
		//parses values once running out of operators
		if (precedence == 0) return ValueExpression.parse(scan);
		
		switch(OperatorTable.getPrecedenceLevel(precedence).getArity()) {
		case UNARY: return UnaryExpression.parse(scan, precedence);
		case BINARY: return BinaryExpression.parse(scan, precedence);
		}
		
		return null;
	}
	
}

