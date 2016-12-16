package declaration;

import main.Scanner;

import static main.ParserUtils.accept;
import static main.ParserUtils.expect;

public class VariableDeclaration extends Declaration {

    public VariableDeclaration(String type, String identifier) {
    	super(type, identifier);
    }

    public static VariableDeclaration parse(Scanner scan) throws Exception {
		if (accept(scan, "var") == null) return null;
		String type = expect(scan, "[a-zA-Z][a-zA-Z0-9]*", "Syntax Error: Variable Declaration: Expecting type");
		String identifier = expect(scan, "[a-zA-Z][a-zA-Z0-9]*", "Syntax Error: Variable Declaration: Expecting identifier");
		return new VariableDeclaration(type, identifier);
    }

    @Override
    public String toString() {
        return "var " + type + " " + identifier;
    }
}
