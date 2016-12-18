package expression;

import main.Scanner;
import operator.OperatorTable;

public class UnaryExpression extends Expression {
	
	Expression exp;
	String op;
	
	public UnaryExpression(Expression exp, String op) {
		this.exp = exp;
		this.op = op;
	}	
	
	public String toString() {
		return String.format("(%s %s)", exp.toString(), op);
	}
	
	/**
	 * Parses the precedence level of an expression that contains unary operators
	 * @param scan			the Parser to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 * @throws Exception
	 */
	public static Expression parse(Scanner scan, int precedence) throws Exception {
		switch(OperatorTable.getPrecedenceLevel(precedence).getFixity()) {
		case PREFIX: return parsePrefix(scan, precedence);
		case POSTFIX: return parsePostfix(scan, precedence);
		default: return null;
		}
	}
	
	/**
	 * Parses the precedence level of an expression that contains right-associative prefix operators
	 * @param scan			the Parser to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 * @throws Exception
	 */
	private static Expression parsePrefix(Scanner scan, int precedence) throws Exception {
		if (scan.hasNext(OperatorTable.getRegex(precedence))) { 
			String op = scan.next();
			Expression exp = parseAtPrecedence(scan, precedence-1);
			return new UnaryExpression(exp, op);
		}	
		return parseAtPrecedence(scan, precedence-1);
	}
	
	
	/**
	 * Parses the precedence level of an expression that contains left-associative postfix operators
	 * @param scan			the Parser to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 * @throws Exception
	 */
	private static Expression parsePostfix(Scanner scan, int precedence) throws Exception {
		Expression exp = parseAtPrecedence(scan, precedence-1);

		if (scan.hasNext(OperatorTable.getRegex(precedence))) { 
			String op = scan.next();
			return new UnaryExpression(exp, op);
		}
		
		return exp;
	}	
}