package iiitd.oopdProject;

import java.util.ArrayList;
import java.util.Scanner;

// INHERITANCE is implemented as :
// Order inherits from the DatabaseConnector class
public class Order extends DatabaseConnector {
	// a public static variable to store the restaurant selected
	public static int rest_choice =0;
	
	//printRestaurant --> Function to print all the restaurants from Restaurant Table
	public void printRestaurant() {
		System.out.println("Select From the following Restaurants:");
		System.out.printf("%-5s%-20s%-5s%n","SNO.",  "Restaurant",  "Preparation Time");
		selectAllRestaurants("food.db");
		
	}
	// makeOrder() --> Function to make order and returns the items and prices(cart) as an arraylist of arraylist.
	public ArrayList<ArrayList<String>> makeOrder() {
		ArrayList<ArrayList<String>> cart = new ArrayList<ArrayList<String>>();
		ArrayList<String> items = new ArrayList<>();
		ArrayList<String> quantity = new ArrayList<>();
		ArrayList<String> price = new ArrayList<>();
		
		
		this.printRestaurant();
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		sc.nextLine();
		rest_choice = choice;
		

		System.out.println("Select From the following Items followed by their Quantity(Space Seperated):");
		ArrayList<ArrayList<String> > mainList = printSelectedRestaurant("food.db",choice);
		int token =1;
		do {
			System.out.println("Item Number");
			int sno = sc.nextInt();
			System.out.println("Quantity");
			int qty = sc.nextInt();
	
			
			String[] temp = new String[2];	
			for (int i = 0; i < mainList.size(); i++) {
	            for (int j = 0; j < mainList.get(i).size(); j++) {
	            	if(j == sno -1) {
	            		temp[i] = (mainList.get(i).get(j));
	            	}
	            }
	        }
			items.add(temp[0]);
			quantity.add(Integer.toString(qty));
			float k = Float.parseFloat(temp[1])*qty;
			price.add(Float.toString(k));
			
			System.out.println("Enter 0 to complete order else press 1");
			token = sc.nextInt();
		}while(token!=0);
		
		
		cart.add(items);
		cart.add(quantity);
		cart.add(price);
		
		int check = 0;
		while(check==0) {
			System.out.println("Your cart:");
			System.out.printf("%-20s%-2s%10s%n","Item","Quantity","Price");
			for (int i = 0; i < cart.get(0).size(); i++) {

				System.out.printf("%-20s%-2s%10s%n",cart.get(0).get(i),cart.get(1).get(i),cart.get(2).get(i));
	        }
			System.out.println("1. Remove few items from cart");
			System.out.println("2. Add items to Wishlist");
			System.out.println("3. Proceed to Payment");
			System.out.println("4. Discard this order");
			int ch1 = sc.nextInt();
			switch(ch1) {
			case 1:cart = removeItemsFromCart(cart);
				System.out.println("Your cart:");
				System.out.printf("%-20s%-2s%10s%n","Item","Quantity","Price");
				for (int i = 0; i < cart.get(0).size(); i++) { 
					System.out.printf("%-20s%-2s%10s%n",cart.get(0).get(i),cart.get(1).get(i),cart.get(2).get(i));
				}
				break;
			case 2: ArrayList<ArrayList<String>> wishlist = makeWishlist(choice);
					System.out.println("Your wishlist:");
					System.out.printf("%-20s%-2s%n","Item","Price");
					for (int i = 0; i < wishlist.get(0).size(); i++) {
 
						System.out.printf("%-20s%-2s%n",wishlist.get(0).get(i),wishlist.get(1).get(i));
			        }
				
					break;
			case 3: check=1;break;
			case 4: System.out.println("Order discarded! \nThank you for using FoodieBird");
				System.exit(0);
				break;
			}
		
		}
		return cart;
	}

	//makeWishList() --> Function to create wish list and returns the items and prices(wish list) as an arraylist of arraylist.
	public ArrayList<ArrayList<String>> makeWishlist(int choice){
		ArrayList<ArrayList<String>> cart = new ArrayList<ArrayList<String>>();
		ArrayList<String> items = new ArrayList<>();

		ArrayList<String> price = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);

	
		System.out.println("Select From the following Items: ");
		ArrayList<ArrayList<String> > mainList = printSelectedRestaurant("food.db",choice);
		int token =1;
		do {
			System.out.println("Item Number");
			int sno = sc.nextInt();

			
			String[] temp = new String[2];	
			for (int i = 0; i < mainList.size(); i++) {
	            for (int j = 0; j < mainList.get(i).size(); j++) {
	            	if(j == sno -1) {
	            		temp[i] = (mainList.get(i).get(j));
	            	}
	            }
	        }
			items.add(temp[0]);
			float k = Float.parseFloat(temp[1]);
			price.add(Float.toString(k));
			
			System.out.println("Enter 0 to stop adding to the wishlist else press 1");
			token = sc.nextInt();
		}while(token!=0);
		
		
		cart.add(items);

		cart.add(price);
		
		return cart;
		
	}
	
	//removeItemsFromCart() --> Function to delete some items from the cart and returns the updated cart(arraylist of arraylist)
	public ArrayList<ArrayList<String>> removeItemsFromCart(ArrayList<ArrayList<String>> cart){
		
		int token =1;
		
		while(token ==1) {
			
			int sno = 1;
			Scanner sc  = new Scanner(System.in);
			System.out.println("Your cart:");
			System.out.printf("%-5s%-20s%-8s%10s%n","SNo","Item","Quantity","Price");
			for (int i = 0; i < cart.get(0).size(); i++) { 
				System.out.printf("%-5d%-20s%-8s%10s%n",sno,cart.get(0).get(i),cart.get(1).get(i),cart.get(2).get(i));
				sno+=1;
			}
			
			

			int n = 1;
			int sno2[] = new int[n];
			System.out.println("Now enter the item number");
			for(int i =0;i<sno2.length;i++) {
				sno2[i] = sc.nextInt();
			}
			for (int i = 0 ;i<sno2.length;i++) {
				if(sno2[i]!=0) {
					cart.get(0).remove(sno2[i]-1);
					cart.get(1).remove(sno2[i]-1);
					cart.get(2).remove(sno2[i]-1);	
				}
				
			}
			
			
			System.out.println("Delete more items?(1=Yes; 0=No)");
			token = sc.nextInt();			
		}		
		
		
		return cart;
		
	}
	
	

}
