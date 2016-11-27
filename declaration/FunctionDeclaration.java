/*
 * File: FunctionDeclaration.java
 * Author: Thomas Barrett
 * Created: Nov 23, 2016
 */
package declaration;

import java.util.ArrayList;
import java.util.Scanner;

import statement.BlockStatement;

public class FunctionDeclaration extends Declaration {
    
	ArrayList<VariableDeclaration> parameters = new ArrayList<VariableDeclaration>();
	BlockStatement definition;
	
    public FunctionDeclaration(String type, String identifier, ArrayList<VariableDeclaration> parameters, BlockStatement definition) {
    	super(type, identifier);
    	this.parameters = parameters;
    	this.definition = definition;
    }
    
    public static FunctionDeclaration parse(Scanner scan) throws Exception {
    	if (scan.hasNext("func")) {
    		String type  = scan.next();
    		
    		String identifier;
    		ArrayList<VariableDeclaration> parameters =  new ArrayList<VariableDeclaration>();
    		BlockStatement definition;
    		
		    if (scan.hasNext("[a-zA-Z][a-zA-Z0-9]*")) {
		    	identifier = scan.next();
		    } else throw new Exception("Syntax Error: Function Declaration: Expecting Identifier");
		    
		    if (scan.hasNext("\\(")) {
		    	scan.next();
		    } else throw new Exception("Syntax Error: Function Declaration: Expecting '('");
		    
		    try {
	    		parameters.add(VariableDeclaration.parse(scan));
		    } catch (Exception e) {
		    	if (!e.getMessage().equals("Parse Not Found")) throw e;
		    }
		    
		    while (scan.hasNext(",")) {
		    	scan.next();
		    			
		    	try {
		    		parameters.add(VariableDeclaration.parse(scan));
		    	} catch (Exception e) {
		    		if (e.getMessage().equals("Parse Not Found")) {
		    			throw new Exception("Syntax Error: Function Declaration: Expecting VariableDeclaration");
		    		}
		    	}
		    }
		    
		    
		    if (scan.hasNext("\\)")) {
		    	scan.next();
		    } else throw new Exception("Syntax Error: Function Declaration: Expecting ')'");
		    
		    try {
		    	definition = BlockStatement.parse(scan);
		    } catch(Exception e) {
		    	if (e.getMessage().equals("Parse Not Found")) throw new Exception("Syntax Error: Function Declaration: Expecting BlockStatement");
		    	else throw e;
		    }
		    
	    	return new FunctionDeclaration(type, identifier, parameters, definition);

		} else throw new Exception("Parse Not Found");
    }
    
    @Override
    public String toString() {
        return type + " " + identifier; 
    }
}
