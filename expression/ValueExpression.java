package expression;

import main.Lexer;;

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
	public static ValueExpression parse(Lexer scan) throws Exception {
		if (scan.hasNext("[(]")) {
			scan.next();
			Expression e = Expression.parse(scan);
			if (e == null) throw new Exception("Syntax Error: Value Expression: Expecting Expression");
			expect(scan, "\\)", "Syntax Error: Value Expression: Expecting ')'");
			return new ValueExpression(e);
		} else if (scan.hasNext("[a-zA-Z$_][a-zA-Z1-9$_]*")) {
			return new ValueExpression(scan.next());
		} else if (scan.hasNext("[0-9]+")) {
			return new ValueExpression(scan.next());	
		} else return null;
	}
	
	public String toString() {
		return value.toString();
	}	
}
