package expression;
public class BinaryExpression extends Expression {
	
	Expression left;
	String op;
	Expression right;
	
	
	public BinaryExpression(Expression left, String op, Expression right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}	
	
	public String toString() {
		return String.format("(%s %s %s)", left.toString(),op,right.toString());
	}
	
	
}


