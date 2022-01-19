package iiitd.oopdProject;

import java.util.ArrayList;
import java.util.Scanner;


//INHERITANCE is implemented as:
//Payment class inherits from DatabaseConnector class
public class Payment extends DatabaseConnector {
	//paymentMain()--> Function to do the payment for items in the cart
	public float[] paymentMain(ArrayList<ArrayList<String>> cart) {
		Scanner sc = new Scanner(System.in);
		

		double lat = 28.53328632240886;
		double lon = 77.24170064171038;
		System.out.println("Your Lattitude is:  "+lat);
		System.out.println("Your Longitude is:  "+lon);
		

		String[] latLonPrepTime = get_latLonPrepTime("food.db",Order.rest_choice);

		float lat_r = Float.parseFloat(latLonPrepTime[0]);
		float lon_r = Float.parseFloat(latLonPrepTime[1]);
		int p_time = Integer.parseInt(latLonPrepTime[2]);
		int estimated_time;
		double dist = distance(lat, lon, lat_r, lon_r);
		dist = round(dist,2);
		System.out.print("The distance from restaurant is ");
		System.out.printf("%.2f%5s%n",dist,"Km");
		estimated_time = (int)((double)p_time + 5*dist);
		System.out.println("Estimated time of Delivery is :"+ estimated_time + "  Minutes");
		float base_bill =0 ; 
		for(int i =0; i<cart.get(0).size(); i++) {
			base_bill+=Float.parseFloat(cart.get(2).get(i));
		}

		if(base_bill<100) {
			System.out.println("The order can't not be processed as your base bill is less that 100 Rs");
            System.out.println("!!! Exiting");
			System.exit(0);
		}
		float Total_Bill = (float)(base_bill+ 20*dist);
		System.out.println("Your base bill is : "+base_bill+ " Rupees");
		System.out.println("Delivery charges :"+(20*dist)+" Rupees");
		System.out.println("Your Total bill is :"+ Total_Bill + " Rupees");
		System.out.println("Select a promocode from the following: \n 1. SAVE50 \n 2. SAVE20");
		int promo = sc.nextInt();
		if(promo == 1) {
			Total_Bill*=0.5;
		}
		else if (promo == 2) {
			Total_Bill*=0.8;
		}
		System.out.println("Your bill after Discount is :"+ Total_Bill + " Rupees");
		System.out.println("Select a Payment Method from the following:");
		System.out.println("1. BHIM UPI");
		System.out.println("2. PayTM");
		System.out.println("3. Credit Card/Debit Card");
		System.out.println("4. Net Banking");
		int ch3 = sc.nextInt();
		System.out.println("Enter the OTP sent to your Mobile no. ******20 : ");
		int otp = sc.nextInt();
		System.out.println("Your Order Has been Successfully Placed");
		
		float[] f = new float[2];
		f[0]=(float)dist;
		f[1]=(float)estimated_time;
		
		return f;
	}
//helper function to round off a value of type double.
public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
//helper function to convert degree to radian
public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
      }
//helper function to convert radian to degree
    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
      }
// distance() -> returns distance in kilometer using the lat, long values of 2 locations     
public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        
        dist = dist * 1.609344;
        
        return (dist);
      }
}
