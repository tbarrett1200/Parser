package statement;

import main.Lexer;

import expression.Expression;

public class ExpressionStatement extends Statement {

	public Expression expression;
	
	public ExpressionStatement(Expression expression) {
		this.expression = expression;
	}
	
	public static ExpressionStatement parse(Lexer scan) throws Exception {
		Expression expression = Expression.parse(scan);
		if (expression == null) return null;
		expect(scan, ";", "Syntax Error: Expression Statement: Expecting ';'");
		return new ExpressionStatement(expression);	
	}
	
	@Override
	public String toString() {
		return expression.toString() + ";";
	}
	
}
