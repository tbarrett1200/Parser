import java.util.Scanner;

public class Parser {

	public static String operatorTable[][] = {
			{"(\\.)","LEFT","INFIX"},
			{"(\\+\\+|--)","LEFT","POSTFIX"},
			{"(\\+|-|\\+\\+|--|!|~)","RIGHT","PREFIX"},
			{"[*/%]","LEFT","INFIX"},
			{"[+=]","LEFT","INFIX"},
			{"<|<=|>|>=","LEFT","INFIX"},
			{"==|!=","LEFT","INFIX"},
			{"&|\\^|\\|","LEFT","INFIX"},
			{"&&|\\|\\|","LEFT","INFIX"}
		};

	private static String associativityAtPrecendence(int precedence) {
		return operatorTable[precedence-1][1];
	}
	
	private static String fixityAtPrecendence(int precedence) {
		return operatorTable[precedence-1][2];
	}
	
	/**
	 * Parses an expression from the specified scanner, starting from the highest precedence in the operatorTable.
	 * This method recursively parses all lower precedences using data from the operatorTable to build the expression
	 * @param scan			the scanner to be used
	 * @return				the expression parsed from the specified scanner	
	 * @throws Exception
	 */
	public static Expression parse(Scanner scanner) throws Exception {
		return parseAtPrecedence(scanner,operatorTable.length);
	}
	
	/**
	 * Parses a sub-expression from the specified scanner, starting from the specified precedence.
	 * This method recursively parses all lower precedences using data from the operatorTable to build the expression
	 * @param scan			the scanner to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression at the specified precedence
	 * @throws Exception
	 */
	private static Expression parseAtPrecedence(Scanner scan, int precedence) throws Exception {
		//parses values once running out of operators
		if (precedence == 0) return parseValue(scan);
		
		//a string representation of the operator fixity and associativity
		String operatorType = fixityAtPrecendence(precedence) + associativityAtPrecendence(precedence);
		
		//parses expression operator type at precedence
		switch(operatorType) {
		case "INFIXLEFT": return parseInfixLeft(scan, precedence);
		case "PREFIXRIGHT": return parsePrefixRight(scan, precedence);
		case "POSTFIXLEFT": return parsePostfixLeft(scan, precedence);
		}
		
		//throws error if unable to parse operator
		throw new Exception("Unsupported Operator Type: " + operatorType);
	}
	
	
	/**
	 * Parses the precedence level of an expression that contains left-associative infix operators
	 * @param scan			the scanner to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 */
	public static Expression parseInfixLeft(Scanner scan, int precedence) throws Exception {
		Expression left = parseAtPrecedence(scan,precedence-1);
		return recursiveParseInfixLeft(scan, precedence, left);
	}

	/* helper method for parseInfixLeft */
	private static Expression recursiveParseInfixLeft(Scanner scan, int precedence, Expression left) throws Exception {
		if (scan.hasNext(operatorTable[precedence-1][0])) {
			String op = scan.next();
			Expression right = parseAtPrecedence(scan, precedence-1);
			return recursiveParseInfixLeft(scan, precedence, new BinaryExpression(left,op,right));
		} else return left;
	}
	
	/**
	 * Parses the precedence level of an expression that contains right-associative prefix operators
	 * @param scan			the scanner to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 * @throws Exception
	 */
	public static Expression parsePrefixRight(Scanner scan, int precedence) throws Exception {
		//return operations at current precedence if any
		if (scan.hasNext(operatorTable[precedence-1][0])) { 
			String op = scan.next();
			Expression exp = parseAtPrecedence(scan, precedence);
			return new UnaryExpression(exp, op);
		}
		
		//return operations at lower precedence
		return parseAtPrecedence(scan, precedence-1);
	}
	
	
	/**
	 * Parses the precedence level of an expression that contains left-associative postfix operators
	 * @param scan			the scanner to be used
	 * @param precedence	the precedence at which to start
	 * @return				the sub-expression
	 * @throws Exception
	 */
	public static Expression parsePostfixLeft(Scanner scan, int precedence) throws Exception {
		Expression exp = parseAtPrecedence(scan, precedence-1);
		return recursiveParsePostfixLeft(scan, precedence, exp);
	}
	
	/* helper method for parsePostfixLeft */ 
	public static Expression recursiveParsePostfixLeft(Scanner scan, int precedence, Expression child) {
		if (scan.hasNext(operatorTable[precedence-1][0])) {
			String op = scan.next();
			return recursiveParsePostfixLeft(scan, precedence, new UnaryExpression(child,op));
		}
		return child;
	}

	/**
	 * Parses a value expression from the specified scanner
	 * @param scan			the scanner to be used
	 * @param precedence	the precedence at which to start
	 * @return				the parsed value expression
	 * @throws Exception
	 */
	public static Expression parseValue(Scanner scan) throws Exception {
		if (scan.hasNext("[(]")) {
			scan.next("[(]");
			Expression e = Parser.parse(scan);
			scan.next("[)]");
			return new ValueExpression<Expression>(e);
		}
		if (scan.hasNext("[a-zA-Z$_][a-zA-Z1-9$_]*"))
			return new ValueExpression<String>(scan.next("[a-zA-Z$_][a-zA-Z1-9$_]*"));
		if (scan.hasNextInt())
			return new ValueExpression<Integer>(scan.nextInt());	
		throw new Exception();
	}
	
}

class Operator {
	enum Associativity {Left, Right, None;};
	enum Fixity {Prefix, Postfix, Infix};

	
}
