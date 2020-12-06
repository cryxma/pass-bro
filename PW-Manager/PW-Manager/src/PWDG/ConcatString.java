package PWDG;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Set;

public class ConcatString {
	private Dictionary<Bezeichner, String> zeichenSammlung;

	public ConcatString() {
		String kleinbuchstaben = "abcdefghijklmnopqrstuvwxyzäüö";
		String grossbuchstaben = kleinbuchstaben.toUpperCase();
		String nummern = "0123456789";
		String sonderzeichen = ".,:-_#+*/$€%()§ß";
		this.zeichenSammlung = new Hashtable<Bezeichner, String>() {
			private static final long serialVersionUID = 8258407800450060225L;
		{
			put(Bezeichner.KLEINBUCHSTABEN, kleinbuchstaben);
			put(Bezeichner.GROSSBUCHSTABEN, grossbuchstaben);
			put(Bezeichner.NUMMERN, nummern);
			put(Bezeichner.SONDERZEICHEN, sonderzeichen);
		}};
	}
	
	public String getZeichenkette(Set<Bezeichner> erwarteteZeichen) {
		String concatString = "";
		for (Bezeichner b : erwarteteZeichen) {
			concatString = concatString.concat(zeichenSammlung.get(b));
		}
		return concatString;
	}

}
