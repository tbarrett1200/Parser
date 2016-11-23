package expression;
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
	

	
}