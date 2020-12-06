package PWDG;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Generator {
	private ConcatString zeichenKette;
	private Set<Bezeichner> erwarteteZeichen;
	private int passLaenge;

	private Generator() {
		this.zeichenKette = new ConcatString();
		this.erwarteteZeichen = new HashSet<Bezeichner>();
	}

	private Generator(int passLaenge) { 
		this();
		this.setPassLaenge(passLaenge);
	}

	public int getPassLaenge() {
		return passLaenge;
	}

	public void setPassLaenge(int passLaenge) {
		this.passLaenge = passLaenge;
	}

	public void addBezeichner(Bezeichner b) {
		this.erwarteteZeichen.add(b);
	}
	
	public void removeBezeichner(Bezeichner b) {
		this.erwarteteZeichen.remove(b);
	}
	
	public String pwd() {
		Random random = new Random();
		String passwort = "";
		String zeichen = this.zeichenKette.getZeichenkette(this.erwarteteZeichen);
		for (int i = 0; i < this.passLaenge; i++) {
			passwort = passwort.concat(
				String.valueOf(zeichen.charAt(random.nextInt(zeichen.length())))
			);
		}
		return passwort;
	}
	
	public static Generator factory(int passLaenge) {
		return new Generator(passLaenge);
	}
}