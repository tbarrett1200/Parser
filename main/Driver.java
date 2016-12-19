package main;


public class Driver {

	public static void main(String[] args) {

		try {
			Scanner scan = new Scanner("func int a(var int a) { var int x; x = a(1,2,3) * b; };");
			Program p = Program.parse(scan);
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
