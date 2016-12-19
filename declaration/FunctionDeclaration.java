package declaration;
import java.util.ArrayList;
import main.Scanner;

import statement.BlockStatement;

import static main.ParserUtils.accept;
import static main.ParserUtils.expect;

public class FunctionDeclaration extends Declaration {

	private ArrayList<VariableDeclaration> parameters = new ArrayList<VariableDeclaration>();
	private BlockStatement definition;

    public FunctionDeclaration(String type, String identifier, ArrayList<VariableDeclaration> parameters, BlockStatement definition) {
    	super(type, identifier);
    	this.parameters = parameters;
    	this.definition = definition;
    }

    public static FunctionDeclaration parse(Scanner scan) throws Exception {
    	if (accept(scan,"func") == null) return null;

    	String type = expect(scan, "[a-zA-Z][a-zA-Z0-9]*", "Syntax Error: Function Declaration: Expecting Type");
    	String identifier = expect(scan, "[a-zA-Z][a-zA-Z0-9]*", "Syntax Error: Function Declaration: Expecting Identifier");

		expect(scan, "\\(", "Syntax Error: Function Declaration: Expecting '('");

    	ArrayList<VariableDeclaration> parameters =  new ArrayList<VariableDeclaration>();

    	VariableDeclaration p = VariableDeclaration.parse(scan);

    	if (p != null) {
    		parameters.add(p);
    		while (accept(scan,",") != null) {
    			p = VariableDeclaration.parse(scan);
    			if (p == null) throw new Exception("Syntax Error: Function Declaration: Expecting Parameter");
    			parameters.add(p);
    		}
    	}

		expect(scan, "\\)", "Syntax Error: Function Declaration: Expecting ')'");

		BlockStatement block = BlockStatement.parse(scan);
		if (block == null) throw new Exception("Syntax Error: Function Declaration: Expecting Block Statement");

	    return new FunctionDeclaration(type, identifier, parameters, block);
    }

    @Override
    public String toString() {
        String text = "func " + type + " " + identifier + "(";
        if (parameters.size() == 0) {
        	text += ") ";
        } else {
        	for (VariableDeclaration d: parameters.subList(0, parameters.size()-1)) {
        		text += d + ", ";
        	}
        	text += parameters.get(parameters.size()-1) + ") ";
        }
        text += definition;
        return text;
    }
}
