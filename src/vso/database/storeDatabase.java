package vso.database;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class storeDatabase {
	
	Connection connection = null;
	
	public storeDatabase(){
		databaseSetUp();
		createUserTable();
		createFilmsTable();
		insertFilms();
	}

	private void databaseSetUp() {
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/store?createDatabaseIfNotExist=true";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = (Connection) DriverManager.getConnection(connectionURL, "root", "1234");
			if (!connection.isClosed())
				System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
		} catch (Exception ex) {
			System.out.println("Unable to connect to database" + ex);
		}		
	}

	private void createUserTable() {
		try {
			Statement stmt = connection.createStatement();
			String sql = "CREATE TABLE users (" + "id SERIAL PRIMARY KEY NOT NULL," + "username TEXT NOT NULL,"
					+ "email TEXT NOT NULL," + "password TEXT NOT NULL," + "numFilms INT NOT NULL )";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.print("Table created");
		} catch (Exception e) {
			System.out.println("Table was not created");
		}		
	}
	
	private void createFilmsTable() {
		try {
			Statement stmt = connection.createStatement();
			String sql = "CREATE TABLE films (" + "id SERIAL PRIMARY KEY NOT NULL," + "name TEXT NOT NULL,"
					+ "year INT NOT NULL," + "owner TEXT NOT NULL," + "dateOfReturn TEXT NOT NULL )";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.print("Table created");
		} catch (Exception e) {
			System.out.println("Table was not created");
		}		
		
	}
	
	public boolean checkIsNameUnavailable(String username){
		boolean doesMatch = false;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM users;");

			while (rs.next()) {
				if (username.equals(rs.getString("username"))) {
					doesMatch = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doesMatch;
	}
	
	public boolean checkNameInDb(String username){
		boolean doesMatch = false;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username FROM users;");

			while (rs.next()) {
				if (username.equals(rs.getString("username"))) {
					doesMatch = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doesMatch;
	}
	
	public boolean checkPasswordInDb(String password){
		boolean doesMatch = false;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password FROM users;");

			while (rs.next()) {
				if (password.equals(rs.getString("password"))) {
					doesMatch = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doesMatch;
	}
	
	public void insertDataIntoUsers(String username, String email, String password, int numFilms) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "INSERT INTO users (username, email, password, numFilms) VALUES('" + username + "', '" + email
					+ "', '" + password + "', '" + numFilms + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertFilms() {
			insertFilm("Avatar", 2015 ,"none","03.10.2017");
			insertFilm("King Kong", 1933 ,"none","03.10.2017");
			insertFilm("Zootopia", 2016 ,"none","03.10.2017");
			insertFilm("Logan", 2017 ,"none","03.10.2017");
			insertFilm("Wonder Woman", 2017 ,"none","03.10.2017");
			insertFilm("The Dark Knight", 2008 ,"none","03.10.2017");
			insertFilm("Skyfall", 2012 ,"none","03.10.2017");
			insertFilm("Start Trek", 2009 ,"none","03.10.2017");
			insertFilm("The LEGO Movie", 2014 ,"none","03.10.2017");
			insertFilm("Moana", 2016 ,"none","03.10.2017");
			insertFilm("Apocalypse Now", 1979 ,"none","03.10.2017");
			insertFilm("Iron Man", 2008 ,"none","03.10.2017");
			insertFilm("The French Connection", 1971 ,"none","03.10.2017");
			insertFilm("True Grit", 2010 ,"none","03.10.2017");
			insertFilm("Baby Driver", 2017 ,"none","03.10.2017");

		}

	public void insertFilm(String name, int year, String owner, String dateOfReturn) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "INSERT INTO films (name, year, owner, dateOfReturn) VALUES('" + name + "', '" + year + "', '" + owner
					+ "', '" + dateOfReturn + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("The film was inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
