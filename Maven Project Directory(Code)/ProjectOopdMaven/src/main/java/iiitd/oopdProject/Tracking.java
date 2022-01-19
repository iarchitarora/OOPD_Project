package iiitd.oopdProject;

import java.util.Random;
import java.util.Scanner;


public class Tracking {
	
	//Tracking() --> Tracks the order using the ideal ETA and actual ETA(ETA = estimated arrival time)
	//returns true if tracking is done and order has been received.
	public boolean track(int est_time) {
		Random rand = new Random();
		Scanner opt = new Scanner(System.in);
	    float estimated_time = (float)est_time;
	    float actual_time_left  = estimated_time;
	    float time_elapsed =0;
	     System.out.println("Tracking details are as following:");
	    while(actual_time_left>0){
	        float temp = (float)(actual_time_left * 1.1);
	        System.out.printf("%-15s%2s%n","Ideal ETA","Actual ETA");
	        System.out.printf("%-15.2f%-10.2f%n",actual_time_left,estimated_time);
	        if(estimated_time > temp){
	            System.out.println("Current time exceeds the estimated time by 10% ");
	            System.out.println("Do u wish to cancel?"); 
	            System.out.println("1. Yes \n2. No"); 
	            
	            int k = opt.nextInt();
	            if(k == 1){
	                System.out.println("Your order has been cancelled.\nThank You!");
	                System.exit(0);
	            } 

	        }
	       
	        int rand_int1 = rand.nextInt(4);
	        actual_time_left = actual_time_left-5;
	        estimated_time = estimated_time-5 + rand_int1;
	        
	    }
	    System.out.println("Thank you for ordering, Enjoy your Food.");
	    System.out.println("Kindly rate our app on the scale of 5");
	    System.out.println("1 being the worst and 5 Being excellent.");
	    int rate = 0;
	    
	    System.out.println("Your rating: ");
	    rate = opt.nextInt();
	    System.out.println("Thanks for using FoodieBird\nWe wish to see you again");	    
	    
	        
	        
	    return true;		
	  
	}
	
	
}



