package POJO;

import java.sql.Date;
import java.time.LocalDateTime;

public class User {
	private int id;
	private String username;
	private String passwort;
	private LocalDateTime creationDate;
	private int hwid;
	private String securityQuestion;
	private String securityAnswer;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @return the passwort
	 */
	public String getPasswort() {
		return this.passwort;
	}
	
	/**
	 * @return the creationDate
	 */
	public LocalDateTime getCreationDate() {
		return this.creationDate;
	}
	
	/**
	 * @return the hwid 
	 */
	public int getHwid() {
		return this.hwid;
	}
	
	/**
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return this.securityQuestion;
	}
	
	/**
	 * @return the securityAnswer
	 */
	public String getSecurityAnswer() {
		return this.securityAnswer;
	}
	
	/**
	 * @return database entry
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ID: " + getId() + "\t");
		sb.append("Username: " + getUsername() + "\t");
		sb.append("Passwort: " + getPasswort() + "\t");
		sb.append("CreationDate: " + getCreationDate() + "\t");
		sb.append("HWID: " + getHwid() + "\t");
		sb.append("SecurityQuestion: " + getSecurityQuestion() + "\t");
		sb.append("SecurityAnswer: " + getSecurityAnswer() + "\t");
		
		return sb.toString();
	}
	
	/**
	 * set id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * set username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * set passwort
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	/**
	 * set creationDate
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * set hwid
	 */
	public void setHWID(int hwid) {
		this.hwid = hwid;
	}
	
	/**
	 * set securityQuestion
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	
	/**
	 * set securityAnswer
	 */
	public void setsecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
}
