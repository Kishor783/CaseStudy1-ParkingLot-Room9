package Customer;

import java.util.*;
import java.time.LocalDateTime;

public class Customer{

	private String vehicleType;
	private int preference; 
	// Truck = 1; 
	// MotorCycle = 2;
	// Electric Car = 3;
	// Van = 4 Large = 4, Handicapped =5;
	// Car = 5 Large = 4, Handicapped = 5, Compact = 6
	private int floorNumber;
	private int slotIndex;
	private int ID;
	private boolean paymentStatus = false;
	private double paymentFee;
	private boolean chargeOpted = false;

	Map<Integer,String> preferences;

	private LocalDateTime entryTime;
	
	public Customer(String vehicleType, int ID, int preference) {
		
		this.vehicleType = vehicleType;
		this.ID = ID;
		this.preference = preference;

		preferences = new HashMap<Integer,String>();
		preferences.put(1,"Truck");
		preferences.put(2,"MotorCycle");
		preferences.put(3,"Electric Car");
		preferences.put(4,"Large");
		preferences.put(5,"Handicapped");
		preferences.put(6,"Compact");

	}

	public int getPreferenceType() {
		return preference;
	}

	public void showTicket() {
		System.out.println("\n////////////////////////////////////////////");
		System.out.println("Customer ID: " + ID);
		System.out.println("Vehicle Type : " + vehicleType);
		System.out.println("Preference Type : " + preferences.get(preference));
		System.out.println("Slot Number: " + slotIndex);
		System.out.println("FloorNumber: " + floorNumber);
		System.out.println("PaymentStatus: " + ((paymentStatus) ? "Done" : "Not Done"));
		System.out.println("PaymentFee: " + ((paymentStatus) ? "Not Applicable yet" : paymentFee));
		System.out.println("Entry Time : " + entryTime);
		System.out.println("Charge : " + ((chargeOpted) ? "Opted" : "Not Opted"));
		System.out.println("////////////////////////////////////////////\n");
	}

	public int getID() {
		return ID;
	}
	
	public void changeSlotIndex(int index) {
		slotIndex = index;
	}

	public int getSlotIndex() {
		return slotIndex;
	}
	
	public void changeFloorNumber(int num) {
		floorNumber = num;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setEntryTime() {
		entryTime = LocalDateTime.now();
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setCharge() {
        chargeOpted = true;
    }
    public boolean getCharge() {
        return chargeOpted;
	}
	
	public void setPaymentFee(double paymentFee) {
		this.paymentFee = paymentFee;
	}

	public void toPay() {
		paymentStatus = true;
	}

	public boolean getPaymentStatus() {
		return paymentStatus;
	}

	public double getPaymentFee() {
		return paymentFee;
	}
	
}