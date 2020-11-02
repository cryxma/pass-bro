package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import POJO.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

public class DbConnection {
	public static ObservableList<User> GetDataFromDb() throws SQLException {
		// Liste für User anlegen
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
			
			// SQL Abfrage ausführen
			resultSet = statement.executeQuery("SELECT * FROM pwmanager.User");
			
			// Ergebnisse auswerten und in ObservableList speichern
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPasswort(resultSet.getString("passwort"));
				user.setCreationDate(resultSet.getDate("creationDate")); // Date erfordert, LocalDateTime wird gegeben -> Fehler | Cast/ Konvertierung
				user.setHWID(resultSet.getInt("hwid"));
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
					
			// SQL Abfrage ausführen
			String sqlCommand = "INSERT INTO pwmanager.User (id, username, passwort, creationDate, hwid, securityQuestion, securityAnswer) "
					+ "VALUES (NULL, '" + user.getUsername() + "', '" + user.getPasswort() + "', '" + user.getCreationDate() + "', "
							+ "'" + user.getHwid() + "', '" + user.getSecurityQuestion() + "', '" + user.getSecurityAnswer() + "')";
		
			//int countRow = statement.executeUpdate(sqlCommand);
		
			// Ergebnismenge auswerten und speichern
			//System.out.println("Anzahl eingefügter Tupel: " + countRow);
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
}
