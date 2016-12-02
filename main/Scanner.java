package main;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scanner {

	private ArrayList<String> tokens = new ArrayList<String>();
	private int index = 0;
	
	private Pattern identifier = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
	private Pattern number = Pattern.compile("[0-9]+");
	private Pattern operator = Pattern.compile("[+-/%=;]");
	private Pattern space = Pattern.compile(" ");
	private Pattern[] patterns = {identifier, number, space, operator};
	
	public Scanner(String file) {
	    Matcher match = Pattern.compile("").matcher(file);
	    int start = 0;
	    loop: while(true) {
		if (start==file.length()) break;
		for (Pattern pattern: patterns) {
		    match.usePattern(pattern);
		    if (match.find(start) && match.start()==start) {
			if (match.pattern()!=space) tokens.add(match.group());
			start = match.end();
			continue loop;
		    }
		}
		System.out.println("Lexer Error");
		break;
	    }
	}
	
	public void list() {
		for (String s: tokens) {
			System.out.println(s);
		}
	}
	
	public boolean hasNext(String s) {
		if (index == tokens.size()) return false;
		return tokens.get(index).matches(s);
		
	}
	
	public boolean hasNext() {
		if (index == tokens.size()) return false;
		return true;
	}
	
	public String next() {
		return tokens.get(index++);
	}
	
}
