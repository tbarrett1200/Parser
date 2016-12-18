package main;


public class Driver {

	static int a = 4;

	public static void main(String[] args) {

		try {
			Scanner scan = new Scanner("func int a(var int a) { var int x; x = 1;};");
			Program p = Program.parse(scan);
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
