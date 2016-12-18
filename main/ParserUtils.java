package main;

import java.util.List;

public class ParserUtils {
	
	public static String accept(Scanner scan, String pattern) {
		if (scan.hasNext(pattern)) {
			return scan.next(); 
		}
		return null;
	}
	
	public static String expect(Scanner scan, String pattern, String error) throws Exception {
		if (scan.hasNext(pattern)) {
			return scan.next(); 
		}
		throw new Exception(error);
	}
	

	public static boolean isSpecial(char c) {
		return "\\.[]{}()*+?^$|".contains(Character.toString(c));
	}
	
	public static String addEscapes(String s) {
		String escaped = "";
		for (char c: s.toCharArray()) {
			if(isSpecial(c)) {
				escaped += "\\";
			}
			escaped += c;
		}
		return escaped;
	}
	
	public static String generateRegex(List<String> operators) {
		if (operators.isEmpty()) return "";
		
		operators.sort((a, b) -> -a.compareTo(b));
		
		String regex = "";
		for (String s: operators) {
			regex += addEscapes(s) + "|";
		}
		
		regex = regex.substring(0, regex.length()-1);
		
		return regex;	
	}
}
