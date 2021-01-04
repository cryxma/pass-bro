package POJO;

import GUI.DatensatztEinfuegenView;

public class PasswordFile {

	private int id ;
	private String password;
	private String website;
	private String beschriftung;
		
	public PasswordFile(int id, String password, String website, String beschriftung) {
		super();
		this.id = id;
		this.password = password;
		this.website = website;
		this.beschriftung = beschriftung;
	}
	//asd
	
	public int getId() {
		return id;
	}
	
	public String getPasswort() {
		return password;
	}
	public String getWebsite() {
		return website;
	}
	public String getBeschriftung() {
		return beschriftung;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public void setBeschriftung(String beschriftung) {
		this.beschriftung = beschriftung;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ID: " + getId() + "\t");
		sb.append("beschriftung: " + getBeschriftung() + "\t");
		sb.append("website: " + getWebsite() + "\t");
		sb.append("CryptedPasswort: " + getPasswort() + "\t");
		//sb.append("CreationDate: " + getCreationDate() + "\t");
		//sb.append("password: " + getPasswort() + "\t");
		
		return sb.toString();
	}
	
}
