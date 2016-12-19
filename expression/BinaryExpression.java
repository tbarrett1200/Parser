package expression;

import main.Scanner;
import operator.OperatorTable;

public class BinaryExpression extends Expression {
	
	private Expression left;
	private String op;
	private Expression right;
	
	
	public BinaryExpression(Expression left, String op, Expression right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}	
	
	public String toString() {
		return String.format("(%s %s %s)", left.toString(),op,right.toString());
	}
	
	/**
	 * Parses the precedence level of an expression
	 * @param scan			the Parser to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 */
	public static Expression parse(Scanner scan, int precedence) throws Exception {
		switch(OperatorTable.getPrecedenceLevel(precedence).getAssociativity()) {
		case LEFT: return parseLeft(scan, precedence);
		case RIGHT: return parseRight(scan, precedence);
		case NONE: return parseNone(scan, precedence);
		default: return null;
		}
		
	}
	
	/**
	 * Parses the precedence level of an expression that contains left-associative infix operators
	 * @param scan			the Parser to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 */
	public static Expression parseLeft(Scanner scan, int precedence) throws Exception {
		Expression left = Expression.parseAtPrecedence(scan,precedence-1);
		
		while (scan.hasNext(OperatorTable.getRegex(precedence))) {
			String op = scan.next();
			Expression right = parseAtPrecedence(scan, precedence-1);
			left = new BinaryExpression(left, op, right);
		}

		return left;
	}

	/**
	 * Parses the precedence level of an expression that contains right-associative infix operators
	 * @param scan			the Parser to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 */
	public static Expression parseRight(Scanner scan, int precedence) throws Exception {
		
		Expression left = Expression.parseAtPrecedence(scan,precedence-1);
		
		while (scan.hasNext(OperatorTable.getRegex(precedence))) {
			String op = scan.next();
			Expression right = parseAtPrecedence(scan, precedence);
			return new BinaryExpression(right, op, left);
		}

		return left;
	}
	
	/**
	 * Parses the precedence level of an expression that contains non-associative infix operators
	 * @param scan			the Parser to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 */
	public static Expression parseNone(Scanner scan, int precedence) throws Exception {
		Expression left = Expression.parseAtPrecedence(scan,precedence-1);
		
		if (scan.hasNext(OperatorTable.getRegex(precedence))) {
			String op = scan.next();
			Expression right = parseAtPrecedence(scan, precedence-1);
			return new BinaryExpression(left, op, right);
		}
		
		return left;
	}
}


