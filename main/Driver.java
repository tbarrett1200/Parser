package main;


public class Driver {

	static int a = 4;

	public static void main(String[] args) {

		try {
			Scanner l = new Scanner("var int a;");
			Program p =Program.parse(l);
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
