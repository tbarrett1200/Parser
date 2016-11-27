package declaration;

import java.util.Scanner;

public class VariableDeclaration extends Declaration {
    
    public VariableDeclaration(String type, String identifier) {
    	super(type, identifier);
    }
      
    public static VariableDeclaration parse(Scanner scan) throws Exception {
		if (scan.hasNext("var")) {
		    String type = scan.next();
		    if (scan.hasNext("[a-zA-Z][a-zA-Z0-9]*")) {
		    	String identifier = scan.next();
		    	return new VariableDeclaration(type, identifier);
		    } else throw new Exception("Syntax Error");
		} else throw new Exception("Parse Not Found");	
    }
    
    @Override
    public String toString() {
        return type + " " + identifier; 
    }
}
