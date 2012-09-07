package coolPackage;

public class CoolClass {
	private static String coolString = "This String is Cool";

	public static void main(String[] args) {
		new CoolClass().coolMethod();
	}

	private void coolMethod() {
		System.out.println(coolString);
	}

}
