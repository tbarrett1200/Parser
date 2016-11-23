
public class Declaration {

	String type;
	String identifier;
	
	public Declaration(String type, String identifier) {
		this.type = type;
		this.identifier = identifier;
	}
	
	public static Declaration parse() throws Exception {
		return new Declaration(null,null);
	}
}
