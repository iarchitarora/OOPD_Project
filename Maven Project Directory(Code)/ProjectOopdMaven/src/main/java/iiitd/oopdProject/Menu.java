package iiitd.oopdProject;

import java.util.*;
public class Menu {
	// ENCAPSULATION
	//Private Data Members to keep a check on each feature
	private boolean authenticate;
	private boolean ordered;
	private boolean paid;
	private boolean tracked;
	private ArrayList<ArrayList<String>> cart = new ArrayList<ArrayList<String>>();
	private float distance;
	private int estimated_time;
	
	//Default constructors to initialize private data members
	Menu(){
		this.authenticate = false;
		this.ordered = false;
		this.paid = false;
		this.tracked = false;
		this.distance = 0;
		this.estimated_time=0;
	}
	//Display function for different functionalities of Food Ordering App
	public void display() {
		do
			{
			System.out.println("1. Authentication");
			System.out.println("2. Exit");
			System.out.println("Enter your option(1/2): ");
			Scanner s = new Scanner(System.in);
			int n = s.nextInt();
				switch(n) {
				case 1: 
					Authentication auth = new Authentication();
					this.authenticate = auth.authenticate();
					break;
				case 2: System.exit(0); break;  
				default:System.out.println("!!! Please select correct option"); break;
				}
			}while(!this.authenticate);
		
		do
		{
		System.out.println("1. Order");
		System.out.println("2. Exit");
		System.out.println("Enter your option(1/2): ");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
			switch(n) {
			case 1: 
				Order ord = new Order();
				this.cart = ord.makeOrder();
				this.ordered = true;				
				break;
			case 2: System.exit(0); break;  
			default:System.out.println("!!! Please select correct option"); break;
			}
		}while(!this.ordered);
		
		do
		{
		System.out.println("1. Make Payment");
		System.out.println("2. Exit");
		System.out.println("Enter your option(1/2): ");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
			switch(n) {
			case 1: 
				Payment p = new Payment();
				float f[] = p.paymentMain(this.cart);
				this.distance = f[0];
				this.estimated_time = (int)f[1];
				this.paid = true;				
				break;
			case 2: System.exit(0); break;  
			default:System.out.println("!!! Please select correct option"); break;
			}
		}while(!this.paid);
		
		do
		{
		System.out.println("1. Track order");
		System.out.println("2. Exit");
		System.out.println("Enter your option(1/2): ");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
			switch(n) {
			case 1: 
				Tracking t = new Tracking();
				this.tracked = t.track(this.estimated_time);				
				break;
			case 2: System.exit(0); break;  
			default:System.out.println("!!! Please select correct option"); break;
			}
		}while(!this.tracked);
		
		System.out.println("_________ # # ___________");		
		
		
	}
		

}
