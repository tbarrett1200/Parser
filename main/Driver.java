package main;
import java.io.FileInputStream;

public class Driver {
	

	public static void main(String[] args) {
			
		try {
			Lexer l = new Lexer(new FileInputStream("/Users/thomasbarrett/Desktop/new.txt"));
			Program p = new Program(l);
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
