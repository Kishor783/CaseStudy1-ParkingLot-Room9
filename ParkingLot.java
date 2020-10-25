import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;

import Floor.*;
import Customer.Customer;
import Payment.Payment;

interface floor {
	double payment(int hours);
	double payment(int hours, String creditCard);
	boolean isAvailable(int preferenceType);
	void displayBoard();
}

interface payment {
	
}

class Admin {

	Scanner fin;
	Scanner scan = new Scanner(System.in);

	int numberOfFloors;

	int numberOfTruckSpots;
	int numberofMotorCycleSpots;
	int numberOfElectricSpots;

	int numberOfLargeSpots;
	int numberOfHandicappedSpots;
	int numberOfCompactSpots;

	Map<Integer,String> vehicles;
	Map<Integer,Double> rates; // Currency = Bitcoin

	int id = 1;
	
	TruckFloor groundFloor;
	CommonFloor[] commonFloor;

	Vector<Customer> customers;
	
	Admin(){

		try {
            File file = new File("input.txt");
            fin = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
		
		getInput();

		vehicles = new HashMap<Integer,String>();
		vehicles.put(1,"Truck");
		vehicles.put(2,"MotorCycle");
		vehicles.put(3,"Electric Car");
		vehicles.put(4,"Van");
		vehicles.put(5,"Car");

		rates = new HashMap<Integer,Double>();
		rates.put(1,fin.nextDouble());
		rates.put(2,fin.nextDouble());
		rates.put(3,fin.nextDouble());
		rates.put(4,fin.nextDouble());
		rates.put(5,fin.nextDouble());
		rates.put(6,fin.nextDouble());

		groundFloor = new TruckFloor(numberOfTruckSpots);
		commonFloor = new CommonFloor[numberOfFloors - 1];
		
		//commonFloor[0] = 1st Floor

		for(int i=0; i<numberOfFloors - 1; i++) {
			commonFloor[i] = new CommonFloor(numberOfCompactSpots, numberOfLargeSpots, numberOfElectricSpots, numberOfHandicappedSpots, numberofMotorCycleSpots, i + 1);
		}

		customers = new Vector<Customer>();

	}
	
	private void getInput() {

		numberOfFloors = fin.nextInt();

		numberOfTruckSpots = fin.nextInt();
		numberofMotorCycleSpots = fin.nextInt();
		numberOfElectricSpots = fin.nextInt();

		numberOfLargeSpots = fin.nextInt();
		numberOfHandicappedSpots = fin.nextInt();
		numberOfCompactSpots = fin.nextInt();

	}
	
	boolean isPreferenceTypeFull(int preference) {
		if(preference != 1) {
			for(int i = 0; i < numberOfFloors - 1; i++)
				if(commonFloor[i].isAvailable(preference)) 
					return false;
		 
			return true;
		}
		else {
			return !groundFloor.isAvailable();
		}
	}
	
	void parkingDisplayBoard() {

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("ENTRY PANEL\n");
		System.out.println("What is your Vehicle Type : ");
		
		if(isPreferenceTypeFull(1))
			System.out.println("1 - Truck\t\t: price/hour\t\t: not available");
		else
			System.out.println("1 - Truck\t\t: price/hour\t\t: available");
		
		if(isPreferenceTypeFull(2))
			System.out.println("2 - MotorCycle\t\t: price/hour\t\t: not available");
		else
			System.out.println("2 - MotorCycle\t\t: price/hour\t\t: available");
		
		if(isPreferenceTypeFull(3))
			System.out.println("3 - Electric Car\t: price/hour\t\t: not available");
		else
			System.out.println("3 - Electric Car\t: price/hour\t\t: available");
		
		if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5))
			System.out.println("4 - Van >\t\t\t\t\t: not available");
		else
			System.out.println("4 - Van >\t\t\t\t\t: available");
		
		if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && isPreferenceTypeFull(6))
			System.out.println("5 - Car >\t\t\t\t\t: not available");
		else
			System.out.println("5 - Car >\t\t\t\t\t: available");
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

	}
	  
	void entrancePanelOfFloor(int floorNumber) { // WE NEVER CALLED THIS FUNCTION
		
		if(floorNumber == 0) {
			if(!groundFloor.isAvailable()) 
				System.out.println("Truck Spots\t\t\t: not available");
			else
				System.out.println("Truck Spots\t\t\t: available");
		}
		else {
			if(!commonFloor[floorNumber].isAvailable(2))
				System.out.println("MotorCycle Spots\t\t\t: not available");
			else
				System.out.println("MotorCycle Spots\t\t\t: available");
			
			if(!commonFloor[floorNumber].isAvailable(3))
				System.out.println("Electric Car Spots\t\t\t: not available");
			else
				System.out.println("Electric Car Spots\t\t\t: available");
			
			if(!commonFloor[floorNumber].isAvailable(4))
				System.out.println("Large Spots\t\t\t: not available");
			else
				System.out.println("Large Spots\t\t\t: available");
			
			if(!commonFloor[floorNumber].isAvailable(5))
				System.out.println("Handicapped Spots\t\t\t: not available");
			else
				System.out.println("Handicapped Spots\t\t\t: available");
			
			if(!commonFloor[floorNumber].isAvailable(6))
				System.out.println("Compact Spots\t\t\t: not available");
			else
				System.out.println("Compact Spots\t\t\t: available");
		}
	}

	void displayAvailableFloors(int preferenceType) {
		
		System.out.println("Which floor do you want..?");

		if(preferenceType == 1) {
			System.out.println("Ground Floor (0) : available");
		}

		else {
			for(int i = 0 ; i < numberOfFloors - 1; i++) {
				if(commonFloor[i].isAvailable(preferenceType))
					System.out.println("Floor " + (i + 1) + "\t: available");
			}
		}

	}
	
	Customer allotFloor(int vehicleType, Customer customer, int preference) {

		displayAvailableFloors(vehicleType);
		
		while(true) {

			int floorNum = scan.nextInt();

			if(vehicleType == 1 && floorNum != 0) {
				System.out.println("Above floor is currently not available for your truck");
			}

			else if(vehicleType == 1 && floorNum == 0) {
				
				customer.changeFloorNumber(floorNum);
				customer = allotSpotIndex(customer, preference, floorNum);
				break;
			}

			else {
				if( (floorNum < 1 || floorNum >= numberOfFloors) || !commonFloor[floorNum - 1].isAvailable(preference)) {
					System.out.println("Above floor is currently not available for your preference type");
					continue;
				}

				else {
					customer.changeFloorNumber(floorNum);
					customer = allotSpotIndex(customer, preference, floorNum);
					break;
				}
			}
			
		}

		//System.out.println("00000000000000000000000000000000");

		return customer;
	}

	Customer allotSpotIndex(Customer customer, int preference, int floorNum) {

		System.out.println("Which Spot do you want for your vehicle ?");

		if(preference == 1) {

			groundFloor.showSpots();

			while(true) {

				int index = scan.nextInt();

				if(groundFloor.checkIndex(index)) {

					groundFloor.assignSlot(index);
					customer.changeSlotIndex(index);
					customer.setEntryTime();
					System.out.println("Allotment successufull and collect your ticket");
					break;

				}

				else {
					System.out.println("Above index is not available, please choose again");
					continue;
				}

			}
		}

		else {

			commonFloor[floorNum - 1].showSpots(preference);

			while(true) {

				int index = scan.nextInt();

				if(commonFloor[floorNum - 1].checkIndex(preference, index)) {

					commonFloor[floorNum - 1].assignSlot(index, preference);
					customer.changeSlotIndex(index);
					
					if(preference == 3) {

						System.out.println("\nWould you like to opt for charging your car");
						System.out.println("0 - No");
						System.out.println("1 - Yes");

						while(true) {

							int chargeInput = scan.nextInt();

							if(chargeInput == 0) {
								break;
							}

							else if(chargeInput == 1) {
								customer.setCharge();
								break;
							}

							else {
								System.out.println("Please enter only 0 or 1");
							}

						}

					}

					customer.setEntryTime();
					System.out.println("\nAllotment successufull and collect your ticket");
					break;

				}

				else
					System.out.println("Please enter a valid index");
			}

		}

		//System.out.println("==================================");

		return customer;

	}
	
	public void start() {

		while(true) {
			
			// ============== NEW CUSTOMER IN ===============
			parkingDisplayBoard();

			int vehicleType = scan.nextInt();

			if(vehicleType == 1 || vehicleType == 2 || vehicleType == 3) {
				Customer customer;
				
				if(vehicleType == 1 && isPreferenceTypeFull(1)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				} 
				
				else if(vehicleType == 2 && isPreferenceTypeFull(2)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				} 
				
				else if(vehicleType == 3 && isPreferenceTypeFull(3)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}

				else {
					customer = new Customer(vehicles.get(vehicleType), id, vehicleType);
					id++;
					customer = allotFloor(vehicleType, customer, vehicleType);
				}
				
				customer.showTicket();
				customers.add(customer);
				//System.out.println("2222222222222222222222");
				
			}

			else if(vehicleType == 4 || vehicleType == 5) {

				if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && vehicleType == 4) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}
				else if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && isPreferenceTypeFull(6) && vehicleType == 5) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}
				
				askMultiPreferences(vehicleType);

			}

			else {
				System.out.println("Please enter a vaild Vehicle Type");
			}

			// ============== OLD CUSTOMER OUT ===============

			if(customers.size() >= 1) {

				Random rand = new Random();

				if(rand.nextInt(3) == 1) {

					int index = rand.nextInt(customers.size());

					customerIsPaying(customers.get(index));
					customers.remove(index);					

				}
			}

		}

	}

	public void customerIsPaying(Customer customer) {

		System.out.println("\n|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("TO ADMIN : Customer with customerID " + customer.getID() + " wants to leave");
		
		int preferenceType = customer.getPreferenceType();
		LocalDateTime exitTime = LocalDateTime.now();
		
		Payment payment = new Payment(rates.get(preferenceType), customer.getCharge(), customer.getEntryTime(), exitTime);
		payment.calculateDuration();
		
		System.out.println("\nIf the customer is paying by cash press 0");
		System.out.println("If the customer is paying by credit card press 1");
		while(true) {

			int input = scan.nextInt();

			if(input == 0) {
				double paymentFee = payment.calculateFee();
				paymentFee = Math.round(paymentFee * 1) / 1;
				customer.setPaymentFee(paymentFee + 1);
				break;
			}

			else if(input == 1) {
				double paymentFee = payment.calculateFee();
				paymentFee = Math.round(paymentFee * 100) / 100;
				customer.setPaymentFee(paymentFee);
				break;
			}

			else {
				System.out.println("Please enter only 0 or 1");
			}

		}

		if(preferenceType == 1) {
			int index = customer.getSlotIndex();
			groundFloor.releaseSlot(index);
		}
		else {
			int index = customer.getSlotIndex();
			int floorNum = customer.getFloorNumber();
			commonFloor[floorNum - 1].releaseSlot(index, preferenceType);
		}

		if(preferenceType == 3) { // FOR ELECTRIC CAR, AT ELECTRIC CAR PANEL
			System.out.println("\nWould you like to pay " + customer.getPaymentFee() +" at your exclusive panel");
			System.out.println("Press 0 to pay on your way out");
			System.out.println("Press 1 to pay");
			if(scan.nextInt() == 1) {
				customer.toPay();
			}
		}

		if(!customer.getPaymentStatus()) { // FOR ANY VEHICLE, AT FLOOR PANEL

			System.out.println("\nWould you like to pay " + customer.getPaymentFee() +" at the Floor panel");
			System.out.println("Press 0 to pay on your way out");
			System.out.println("Press 1 to pay");
			if(scan.nextInt() == 1) {
				customer.toPay();
			}
		}

		if(!customer.getPaymentStatus()) { // FOR ANY VEHICLE, AT EXIT POINT
			System.out.println("\nThis is the exit. Your total fee is " + customer.getPaymentFee());
			customer.toPay(); // Customer Paid
		}
		System.out.println("Thank You for using our Parking Lot");
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");

	}

	void preferencesDisplayBoard(int vehicleType) {

		System.out.println("--------------------------------------------------------------");

		if(vehicleType == 4)
			System.out.println("\nVAN\n");
		else
			System.out.println("\nCAR\n");

		System.out.println("What is your preference Type : ");

		if(isPreferenceTypeFull(4))
			System.out.println("1 - Large\t\t\t: price/hour\t\t\t: not available");
		else
			System.out.println("1 - Large\t\t\t: price/hour\t\t\t: available");

		if(isPreferenceTypeFull(5))
			System.out.println("2 - Handicapped\t\t\t: price/hour\t\t\t: not available");
		else
			System.out.println("2 - Handicapped\t\t\t: price/hour\t\t\t: available");

		if(vehicleType == 5) {

			if(isPreferenceTypeFull(6))
				System.out.println("3 - Compact\t\t\t: price/hour\t\t\t: not available");
			else
				System.out.println("3 - Compact\t\t\t: price/hour\t\t\t: available");
			
		}
		System.out.println("--------------------------------------------------------------");
		
	}
	
	private void askMultiPreferences(int vehicleType) {

		Customer customer;
		preferencesDisplayBoard(vehicleType);

		while(true) {
			int preferenceType = scan.nextInt();
			
			if( (preferenceType > 0 && preferenceType <= 2 ) && vehicleType == 4)  {
				
				if(!isPreferenceTypeFull(preferenceType + 3)) {
					customer = new Customer(vehicles.get(vehicleType), id, preferenceType + 3);
					id++;
					customer = allotFloor(vehicleType, customer, (preferenceType + 3) );
					break;
				}
				else {
					System.out.println("Sorry, above preference is not avialable");
				}
			}

			else if( (preferenceType > 0 && preferenceType <= 3 ) && vehicleType == 5)  {
				
				if(!isPreferenceTypeFull(preferenceType + 3)) { 
					customer = new Customer(vehicles.get(vehicleType), id, (preferenceType + 3));
					id++;
					customer = allotFloor(vehicleType, customer, preferenceType + 3);
					break;
				}
				else {
					System.out.println("Sorry, above preference is not avialable");
				}
			}

			else {
				System.out.println("Please enter a vaild Preference Type");
			}
		
		}

		customer.showTicket();
		customers.add(customer);

	}


}

public class parkingLot {

	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.start();
	}

}