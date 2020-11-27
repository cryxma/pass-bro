package PWDG;

public class Generator {
	private ConcatString zeichenKette;
	private int passLaenge;

	public Generator() {
		// Konstrukor

	}

	public Generator(int passLaenge, ConcatString zeichenKette) {
		this.setZeichenKette(zeichenKette);
		this.setPassLaenge(passLaenge);
	}

	public ConcatString getZeichenKette() {
		return zeichenKette;
	}

	public void setZeichenKette(ConcatString zeichenKette) {
		this.zeichenKette = zeichenKette;
	}

	public int getPassLaenge() {
		return passLaenge;
	}

	public void setPassLaenge(int passLaenge) {
		this.passLaenge = passLaenge;
	}

	public static void test() {
		Generator g = new Generator(30, new ConcatString());
	}
}
