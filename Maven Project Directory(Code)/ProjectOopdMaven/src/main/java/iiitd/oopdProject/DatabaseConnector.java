package iiitd.oopdProject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnector {
	// Data member to store the name of the database(food.db in our case)
	private String dbName;
	
	//ENCAPSULATION implemented as:
	//Getter and setter for private variable: dbName
	public String get_db_name() {
		return this.dbName;

	}
	public void set_db_name(String name) {
		this.dbName = name;
	}
	
	//createNewDatabase-> to create a new database with the provided database name
	public static void createNewDatabase(String dbName) {

		String url = "jdbc:sqlite:" + dbName;

		try {
			Connection conn = DriverManager.getConnection(url);
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
			}
			conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
//createUserTable --> creates table with the database and table name as input.
	public static void createUsersTable(String dbName,String tableName) {
		String url = "jdbc:sqlite:" + dbName;

		String sql = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n" + " id text PRIMARY KEY,\n"
				+ "password text \n" +");";


		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

//insert --> to insert values in users table
	public static void insert(String dbName,String table ,String name, String pass) {
		String url = "jdbc:sqlite:" + dbName;
		String sql = "INSERT INTO "+table+" VALUES(?,?)";

		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pass);
			pstmt.executeUpdate();
			System.out.println("A new entry has been created.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());

		}
	}
//fetchPassword --> Fetches password for the given user
	public static String fetchPassword(String dbName, String tableName, String id) {
		String pass="";
		// SQLite connection string
		String url = "jdbc:sqlite:" + dbName;
		// SQL statement for selecting each entry form the given table name
		String sql = "SELECT * FROM "+tableName;


		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			while (rs.next()) {

				if(rs.getString("id").equals(id)) {
					pass = rs.getString("password");
					break;
				}
			}
			stmt.close();
			rs.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return pass;

	}

// Compile-time POLYMORPHISM has been implemented by using selectAllRestaurants function with different signatures as:
// 1. public static void selectAllRestaurants(String dbName)
// 2. public static void selectAllRestaurants(String dbName, String city_name, int prep_time_limit)
	
	
//Prints all restaurants from Restaurants table
	public static void selectAllRestaurants(String dbName) {
		// SQLite connection string
		String url = "jdbc:sqlite:" + dbName;
		// SQL statement for selecting each entry from restaurants table
		String sql = "SELECT * FROM Restaurants";

		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			int temp =1;
			while (rs.next()) {
				System.out.printf("%-5d%-20s%-5s%5s%n", temp,rs.getString("Restaurant"), rs.getString("Prep_Time"),"Minutes");
				temp++;

			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		}
//Prints all restaurants from Restaurants table with some conditions
	public static void selectAllRestaurants(String dbName, String city_name, int prep_time_limit) {
		// SQLite connection string
		String url = "jdbc:sqlite:" + dbName;
		// SQL statement for selecting each entry with some conditions
		String sql = "SELECT * FROM Restaurants WHERE city  = "+city_name+" and prep_time <="+prep_time_limit ;

		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			int temp =1;
			while (rs.next()) {
				System.out.printf("%-5d%-20s%-5s%5s%n", temp,rs.getString("Restaurant"), rs.getString("Prep_Time"),"Minutes");
				temp++;

			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		}
//printSelectedRestaurant--> prints the menu of the selected restaurant and returns it as an arraylist of arraylists
	public static  ArrayList<ArrayList<String>> printSelectedRestaurant(String dbName,int choice) {
			ArrayList<ArrayList<String> > mainList = new ArrayList<ArrayList<String> >();
			// SQLite connection string
			String url = "jdbc:sqlite:" + dbName;
			// SQL statement for selecting each entry
			String sql = "SELECT * FROM Restaurants";
            String r_name = "";
              
              
			try {
				Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				// loop through the result set
				int temp =1;
				
				while (rs.next()) {
					r_name = rs.getString("Restaurant");
					if (choice == temp){
						break;
					}

					temp++;

				}
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			String sql2 = "SELECT * FROM "+ r_name;
			try {
				Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);

				// loop through the result set
				int temp =1;
				ArrayList<String> items  = new ArrayList<String>();
				ArrayList<String> prices  = new ArrayList<String>();
				
				while (rs.next()) {
					System.out.println(temp + "  " + rs.getString("Item") +"\t"+ rs.getString("Price"));
					items.add(rs.getString("Item"));
					prices.add(rs.getString("Price"));
					temp++;

				}
				mainList.add(items);
				mainList.add(prices);
				
				stmt.close();
				rs.close();
				conn.close();
				
								
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return mainList;

		}
//get_latLonPrepTime -->> Fetches the latitude and longitude of selected restaurant
	public static  String[] get_latLonPrepTime(String dbName, int choice) {
		ArrayList<ArrayList<String> > mainList = new ArrayList<ArrayList<String> >();
		
		String url = "jdbc:sqlite:" + dbName;
		
		String sql = "SELECT * FROM Restaurants";
        String[] desiredValues =  new String[3];
          
          
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			
			int temp =1;
			
			while (rs.next()) {
				desiredValues[0] = rs.getString("Lattitude");
				desiredValues[1] = rs.getString("Longitute");
				desiredValues[2] = rs.getString("Prep_Time");
				
				if (choice == temp){
					break;
				}

				temp++;

			}
			stmt.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return desiredValues;
	}

}
