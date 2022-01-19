package iiitd.oopdProject;

import java.util.Scanner;

//INHERITANCE is implemented as:
// Authentication class inherits from DatabaseConnector class
public class Authentication extends DatabaseConnector{
	//Private Data Members for authentication functionality
	private int choice;
	private boolean verified;

	//constructor for Authentication
	Authentication(){
		this.choice =0;
		this.verified = false;
	}

//authenticate() -> returns true of the user id and password has been verified 
//                  using the 'users' table from the database
	public boolean authenticate() {
		super.set_db_name("food.db");
		createNewDatabase(super.get_db_name());
		createUsersTable(super.get_db_name(),"users");
		
		this.verified = false;

		while(this.verified == false) {
			System.out.print("1. Login \n2. Sign up \nEnter 1 or 2:");
			Scanner s = new Scanner(System.in);
			this.choice = s.nextInt();
			s.nextLine();

			if(choice == 1) {
				System.out.print("User id:");
				String user_id =  s.nextLine();
				System.out.print("Password: ");
				String pass = s.nextLine();
				String temp =  fetchPassword("food.db", "users", user_id);
				
				
				if(pass.equals(temp)) {
					System.out.println("Successfully Logged in !");
					this.verified = true;
				}
				
				if(!pass.equals(temp)) {
					System.out.println("!!! Incorrect username or password");
					System.out.println("Have you signed up?");
				}


			}
			if(choice ==2) {
				System.out.print("User id:");
				String user_id =  s.nextLine();
				String pass = fetchPassword("food.db", "users", user_id);
				
				if (pass.length() == 0) {
					//user_id not in data base
					System.out.print("Password: ");
					String pass1 = s.nextLine();
					String[] temp = {user_id, pass1};
					insert("food.db","users", user_id, pass1);
					System.out.println("successfully signed up");
				}
				if (pass.length() > 0) {
					System.out.println("!!! Already exists");
				}

			}

		}
		return this.verified;
	}
}
