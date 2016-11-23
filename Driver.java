import java.util.Scanner;

import expression.Expression;
import expression.ExpressionParser;

public class Driver {
	
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
			
		try {
			Expression f = ExpressionParser.parse(new Scanner(scan.nextLine()));
			System.out.println(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
