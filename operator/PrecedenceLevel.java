package operator;

public class PrecedenceLevel {
	
	private Arity arity;
	private Associativity associativity;
	private Fixity fixity;
	private String operators[];
	
	
	public PrecedenceLevel(Arity arity, Associativity associativity, Fixity fixity, String... operators) {
		this.arity = arity;
		this.associativity = associativity;
		this.fixity = fixity;
		this.operators = operators;
	}
	
	public Arity getArity() {
		return arity;
	}
	
	public Associativity getAssociativity() {
		return associativity;
	}
	
	public Fixity getFixity() {
		return fixity;
	}
	
	public String[] getOperators() {
		return operators;
	}
}
