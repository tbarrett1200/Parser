package expression;
public class ValueExpression<Type> extends Expression {

	final Type value;
	
	public ValueExpression(Type value) {
		this.value = value;
	}
	
	public String toString() {
		return value.toString();
	}
	
}
