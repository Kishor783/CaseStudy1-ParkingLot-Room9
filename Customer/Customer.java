package Customer;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
public class Customer{

	private String vehicleType;//vehicle type of the customer
	private int preference; //preference of the customer
	// Truck = 1; 
	// MotorCycle = 2;
	// Electric Car = 3;
	// Van = 4 Large = 4, Handicapped =5;
	// Car = 5 Large = 4, Handicapped = 5, Compact = 6
	private int floorNumber;//floor number of customer
	private int slotIndex;//slot index of customer
	private int ID;//ID of the customer
	private boolean paymentStatus = false;//payment status of the customer which is false initially
	private double paymentFee;//fee to be paid by the customer
	private boolean chargeOpted = false;//in case of electric vehicle, if customer opted for charging

	Map<Integer,String> preferences;//map to associate the preference type in integer to preference type in String form

	private LocalDateTime entryTime;//entry time of customer
	
	public Customer(String vehicleType, int ID, int preference) {//constructor to initialize the customer attributes
		
		this.vehicleType = vehicleType;//set the vehicle type
		this.ID = ID;//set the ID
		this.preference = preference;//set the preference

		preferences = new HashMap<Integer,String>();//map the integer to preferences
		preferences.put(1,"Truck");//1-Truck
		preferences.put(2,"MotorCycle");//2-MotorCycle
		preferences.put(3,"Electric Car");//3-Electric Car
		preferences.put(4,"Large");//4-Large
		preferences.put(5,"Handicapped");//5 - Handicapped
		preferences.put(6,"Compact");//6 - Compact

	}

	public int getPreferenceType() {
		return preference;//return the preference type of the customer
	}

	public void showTicket() {//display the ticket of the customer
		System.out.println("\n////////////////////////////////////////////");
		System.out.println("Customer ID: " + ID);
		System.out.println("Vehicle Type : " + vehicleType);
		System.out.println("Preference Type : " + preferences.get(preference));
		System.out.println("Slot Number: " + slotIndex);
		System.out.println("FloorNumber: " + floorNumber);
		System.out.println("PaymentStatus: " + ((paymentStatus) ? "Done" : "Not Done"));
		System.out.println("PaymentFee: " + ((paymentStatus) ? "Not Applicable yet" : paymentFee));
		System.out.print("Entry Time : ");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now)); 
		System.out.println("Charge : " + ((chargeOpted) ? "Opted" : "Not Opted"));
		System.out.println("////////////////////////////////////////////\n");
	}

	public int getID() {//get the ID of the customer
		return ID;
	}
	
	public void changeSlotIndex(int index) {//set the slot index for a customer
		slotIndex = index;
	}

	public int getSlotIndex() {//get the slot index of the customer
		return slotIndex;
	}
	
	public void changeFloorNumber(int num) {//set the floor number of a customer
		floorNumber = num;
	}

	public int getFloorNumber() {//get the floor number of a customer
		return floorNumber;
	}

	public void setEntryTime() {
		entryTime = LocalDateTime.now();//set the entry time of customer
	}

	public LocalDateTime getEntryTime() {//return the entry time of customer
		return entryTime;
	}

	public void setCharge() {//set the charging status in case customer has a a electric car 
        chargeOpted = true;
    }
    public boolean getCharge() {//if the customer has opted charging when he has electric car
        return chargeOpted;
	}
	
	public void setPaymentFee(double paymentFee) {//set the fee to be paid for the customer
		this.paymentFee = paymentFee;
	}

	public void toPay() {//change the payment status for a customer
		paymentStatus = true;
	}

	public boolean getPaymentStatus() {//check if the customer has already paid or not
		return paymentStatus;
	}

	public double getPaymentFee() {// get the payment fee of the customer
		return paymentFee;
	}
	
}