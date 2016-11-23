import java.util.HashMap;

public class Program {

	HashMap<String, Declaration> declarations = new HashMap<String, Declaration>();
	
	public Program() {				
		try {
			while (true) {
				Declaration d = Declaration.parse();
				declarations.put(d.identifier, d);
			}
		} catch (Exception e) {
			if (!declarations.containsKey("main")) {
				System.out.println("Error: no main method found");
			}
		}
	}
}
