import java.util.Scanner;

import expression.Expression;

public class Driver {
	
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
			
		try {
			Program p = new Program(scan);
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
