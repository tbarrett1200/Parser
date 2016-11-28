package main;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Lexer {

	private InputStream in;
	private ArrayList<String> tokens = new ArrayList<String>();
	private int index = 0;
	
	public Lexer(InputStream in) {
		
		this.in = in;
		String current = "";
		char c = 0;
		
		boolean getChar = true;
		
		try {
			while(true) {
				current = "";
				
				c = getChar? getChar() : c;
				getChar = true;
				
				if (Character.isWhitespace(c)) continue;
			
				
				if (Character.isJavaIdentifierStart(c)) {
					current += c;
					c = getChar();
					while (Character.isJavaIdentifierPart(c)) {
						current += c;
						c = getChar();
					}
					getChar = false;
					tokens.add(current);
					continue;
				}
				
				if (Character.isDigit(c)) {
					while (Character.isDigit(c)) {
						current += c;
						c = getChar();
					}
					getChar = false;
					tokens.add(current);
					continue;
				}
				
				current+=c;
				tokens.add(current);
				
			}
		} catch (EOFException e) {
			if (!current.isEmpty()) tokens.add(current);
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
	private char getChar() throws EOFException {
		int c = 0;
		try { 
			c = in.read();
		} catch (IOException e) { e.printStackTrace(); }
		if (c == -1) throw new EOFException();
		return (char)c;
	}
}
