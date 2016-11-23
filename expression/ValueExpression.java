package expression;

import java.util.Scanner;

public class ValueExpression extends Expression {

	final Object value;
	
	public ValueExpression(Object value) {
		this.value = value;
	}
	
	/**
	 * Parses a value expression from the specified scanner
	 * @param scan			the scanner to be used
	 * @param precedence	the precedence at which to start
	 * @return				the parsed value expression
	 * @throws Exception
	 */
	public ValueExpression(Scanner scan) throws Exception {
		if (scan.hasNext("[(]")) {
			scan.next("[(]");
			value = ExpressionParser.parse(scan);
			scan.next("[)]");
		} else if (scan.hasNext("[a-zA-Z$_][a-zA-Z1-9$_]*")) {
			value = scan.next("[a-zA-Z$_][a-zA-Z1-9$_]*");
		} else if (scan.hasNextInt()) {
			value = scan.nextInt();	
		} else {
			throw new Exception();
		}
	}
	
	public String toString() {
		return value.toString();
	}	
}
