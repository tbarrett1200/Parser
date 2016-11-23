/*
 * File: FunctionDeclaration.java
 * Author: Thomas Barrett
 * Created: Nov 23, 2016
 */
package declaration;

import java.util.Scanner;

public class FunctionDeclaration extends Declaration {
    
    public FunctionDeclaration(String type, String identifier) {
	super(type, identifier);
    }
    
    public static FunctionDeclaration parse(Scanner scan) throws Exception {
	String type;
	String identifier;
	

	if (scan.hasNext("function")) {
	    type = scan.next();
	    if (scan.hasNext("[a-zA-Z][a-zA-Z0-9]*")) {
		identifier = scan.next();
		return new FunctionDeclaration(type, identifier);
	    } else {
		throw new Exception("FunctionDeclaration:syntax error");
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
