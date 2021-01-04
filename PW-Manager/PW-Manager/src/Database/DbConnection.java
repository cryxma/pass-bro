package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

import POJO.PasswordFile;
import POJO.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

public class DbConnection {
	public static ObservableList<User> GetDataFromDb() throws SQLException {
		// Liste f�r User anlegen
		ObservableList<User> userListe = FXCollections.observableArrayList();
		
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/testfh?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
			// SQL-Befehlsobjekt erstellen
			statement = connection.createStatement();
			
			// SQL Abfrage ausf�hren
			resultSet = statement.executeQuery("SELECT * FROM pwmanager.User");
			
			// Ergebnisse auswerten und in ObservableList speichern
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPasswort(resultSet.getString("passwort"));
				user.setCreationDate(resultSet.getTimestamp("creationDate")); // Date erfordert, LocalDateTime wird gegeben -> Fehler | Cast/ Konvertierung
				user.setHWID(resultSet.getString("hwid"));
				user.setSecurityQuestion(resultSet.getString("securityQuestion"));
				user.setsecurityAnswer(resultSet.getString("securityAnswer"));
				userListe.add(user);
			}
			// Test
			userListe.forEach((item) -> {
				System.out.println(item.toString());
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return userListe;
	}
	
	public static void SetDataInDb(User user) throws SQLException {
		//User user = new User();
		
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
				
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
		
			// SQL-Befehlsobjekt erstellen
			statement = connection.createStatement();
					
			// SQL Abfrage ausf�hren
			String sqlCommand = "INSERT INTO pwmanager.user (id, username, passwort, creationDate, hwid, securityQuestion, securityAnswer) "
					+ "VALUES (NULL, '" + user.getUsername() + "', '" + user.getPasswort() + "', '" + user.getCreationDate() + "', "
							+ "'" + user.getHwid() + "', '" + user.getSecurityQuestion() + "', '" + user.getSecurityAnswer() + "')";
			
			int countRow = statement.executeUpdate(sqlCommand);
		
			// Ergebnismenge auswerten und speichern
			System.out.println("Anzahl eingef�gter Tupel: " + countRow);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public static boolean CheckExistAccount(String username, String passwort) throws SQLException {
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
						
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
				
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
				
			// SQL-Befehlsobjekt erstellen
			statement = connection.prepareStatement("SELECT username, passwort FROM pwmanager.user WHERE username = ? and passwort = ?");
			statement.setString(1, username);
			statement.setString(2, passwort);
			
			resultSet = statement.executeQuery();
			
			if (!resultSet.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return false;
	}
	
	public static boolean CheckValidHwid(String username, String hwid) throws SQLException {
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
						
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
				
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
				
			// SQL-Befehlsobjekt erstellen
			statement = connection.prepareStatement("SELECT username, hwid FROM pwmanager.user WHERE username = ? and hwid = ?");
			statement.setString(1, username);
			statement.setString(2, hwid);
			
			resultSet = statement.executeQuery();
			
			if (!resultSet.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return false;
	}
	
	public static boolean CheckExistUsername(String username) throws SQLException {
		// INSERT IF NOT EXIST TESTEN
		
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
						
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
				
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
				
			// SQL-Befehlsobjekt erstellen
			statement = connection.prepareStatement("SELECT username FROM pwmanager.user WHERE username = ?");
			statement.setString(1, username);
			
			resultSet = statement.executeQuery();
			
			if (!resultSet.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return false;
	}
	
	public static boolean CheckExistHWID(String hwid) throws SQLException {
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
						
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
				
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
				
			// SQL-Befehlsobjekt erstellen
			statement = connection.prepareStatement("SELECT hwid FROM pwmanager.user WHERE hwid = ?");
			statement.setString(1, hwid);
			
			resultSet = statement.executeQuery();
			
			if (!resultSet.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return false;
	}
	
	public static boolean CheckExistAccount(String username, String password, String securityQuestion, String securityAnswer, String query) throws SQLException {
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
						
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
				
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
				
			// SQL-Befehlsobjekt erstellen
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, securityQuestion);
			statement.setString(4, securityAnswer);
			
			resultSet = statement.executeQuery();
			
			if (!resultSet.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return false;
	}
	
	
	
	
	
	public static void ResetAccountHwid(String newHWID, String username, String password, String securityQuestion, String securityAnswer) throws SQLException {
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
								
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
						
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
						
			// SQL-Befehlsobjekt erstellen
			statement = connection.prepareStatement("UPDATE pwmanager.user SET hwid = ? WHERE username = ? and passwort = ? and securityQuestion = ? and securityAnswer = ?");
			statement.setString(1, newHWID);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, securityQuestion);
			statement.setString(5, securityAnswer);
					
			statement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public static void ResetAccountPassword(String username, String newPassword, String securityQuestion, String securityAnswer) throws SQLException {
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
								
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
						
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
						
			// SQL-Befehlsobjekt erstellen
			statement = connection.prepareStatement("UPDATE pwmanager.user SET passwort = ? WHERE username = ? and securityQuestion = ? and securityAnswer = ?");
			statement.setString(1, newPassword);
			statement.setString(2, username);
			statement.setString(3, securityQuestion);
			statement.setString(4, securityAnswer);
					
			statement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static ObservableList<PasswordFile> GetPasswordFileFromDb() throws SQLException {
		// Liste f�r User anlegen
		ObservableList<PasswordFile> filePasswordListe = FXCollections.observableArrayList();
		
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/testfh?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
			// SQL-Befehlsobjekt erstellen
			statement = connection.createStatement();
			
			// SQL Abfrage ausf�hren
			resultSet = statement.executeQuery("SELECT * FROM pwmanager.filepassword");
			
			// Ergebnisse auswerten und in ObservableList speichern
			while (resultSet.next()) {
				PasswordFile passFile = new PasswordFile(0, dbPassword, dbPassword, dbPassword);
				passFile.setId(resultSet.getInt("id"));
				passFile.setWebsite(resultSet.getString("website"));
				passFile.setBeschriftung(resultSet.getString("beschriftung"));
				passFile.setPassword(resultSet.getString("password"));
				//passFile.setCreationDate(resultSet.getTimestamp("creationDateFilePassword")); // Date erfordert, LocalDateTime wird gegeben -> Fehler | Cast/ Konvertierung
				filePasswordListe.add(passFile);
			}
			// Test
			filePasswordListe.forEach((item) -> {
				System.out.println(item.toString());
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return filePasswordListe;
	}
	
	
	
	public static void SetPasswordFileInDb(PasswordFile passwordFile) throws SQLException {
		
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
				
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
		
			// SQL-Befehlsobjekt erstellen
			statement = connection.createStatement();
					
			// SQL Abfrage ausf�hren
			String sqlCommand = "INSERT INTO pwmanager.filepassword (id, beschriftung, website, password) "
					+ "VALUES (NULL, '" + passwordFile.getBeschriftung() + "', '" + passwordFile.getWebsite() + "', '" + passwordFile.getPasswort() + "')";
			
			int countRow = statement.executeUpdate(sqlCommand);
		
			// Ergebnismenge auswerten und speichern
			System.out.println("Anzahl eingef�gter Tupel: " + countRow);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	/*
	public static boolean CheckDataPassToDb(String beschriftung, String website, String password,String query) throws SQLException {
		// Datenbankverbindungseinstellungen definieren
		final String dbUrl = "jdbc:mysql://localhost:3306/pwmanager?autoReconnect=true&serverTimezone=UTC";
		final String dbUsername = "root";
		final String dbPassword = "";
						
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
				
		try {
			// Datenbankverbindung aufbauen
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
				
			// SQL-Befehlsobjekt erstellen
			statement = connection.prepareStatement(query);
			
			statement.setString(1, beschriftung);
			statement.setString(2, website);
			statement.setString(3, password);
			
			resultSet = statement.executeQuery();
			
			if (!resultSet.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return false;
	}
	*/
}
