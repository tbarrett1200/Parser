package declaration;

import java.util.Scanner;

public class VariableDeclaration extends Declaration {
    
    public VariableDeclaration(String type, String identifier) {
	super(type, identifier);
    }
    
    
    public static VariableDeclaration parse(Scanner scan) throws Exception {
	String type;
	String identifier;
		
	if (scan.hasNext("var")) {
	    type = scan.next();
	    if (scan.hasNext("[a-zA-Z][a-zA-Z0-9]*")) {
		identifier = scan.next();
		return new VariableDeclaration(type, identifier);
	    } else {
		throw new Exception("VariableDeclaration:syntax error");
	    }
	} else {
	    return null;
	}
	
	
    }
    
    @Override
    public String toString() {
        return type + " " + identifier + "\n"; 
    }
}
