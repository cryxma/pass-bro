package PWDG;

public class Test {

	public static void main(String[] args) {
		int laenge = 40;
		Generator g = Generator.factory(laenge);
		g.addBezeichner(Bezeichner.NUMMERN);
		g.addBezeichner(Bezeichner.KLEINBUCHSTABEN);
		g.addBezeichner(Bezeichner.GROSSBUCHSTABEN);
		g.addBezeichner(Bezeichner.SONDERZEICHEN);
		System.out.println(g.pwd());
	}

}
