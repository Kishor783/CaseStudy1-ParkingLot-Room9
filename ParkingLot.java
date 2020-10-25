import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;

interface floor {
	double payment(int hours);
	double payment(int hours, String creditCard);
	boolean isAvailable(int preferenceType);
	void displayBoard();
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
	Map<Integer,Customer> customers;
	//Vector<Customer> customers;
	
	Admin(){

		try {
            File file = new File("input.txt");
            fin = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
		
		getInput();
		customers = new HashMap<Integer,Customer>();
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

		//customers = new Vector<Customer>();

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
				else
					System.out.println("Floor " + (i + 1) + "\t: Not available");
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
					System.out.println("Allotment successfull and collect your ticket");
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
					System.out.println("\nAllotment successfull and collect your ticket");
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
			System.out.println("Press 0 if your vehicle is already parked else press 1");
			int x = scan.nextInt();
			if(x == 0) {
				System.out.println("Please enter your ID");
				while(true) {
					int index = scan.nextInt();
					if(customers.containsKey(index)) {
						customerIsPaying(customers.get(index));
						int floor = customers.get(index).getFloorNumber();
						int preference = customers.get(index).getPreferenceType();
						int slot = customers.get(index).getSlotIndex();
						if(preference == 1) {
							groundFloor.releaseSlot(slot);
						}
						else {
							commonFloor[floor-1].releaseSlot(slot, preference);
						}
						customers.remove(index);
						break;
					}
					else
						System.out.println("Please enter a valid ID");
				}
				continue;
			}
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
				//customers.add(customer);
				customers.put(customer.getID(),customer);
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

			/*if(customers.size() >= 1) {

				Random rand = new Random();

				if(rand.nextInt(3) == 1) {

					int index = rand.nextInt(customers.size());

					customerIsPaying(customers.get(index));
					customers.remove(index);					

				}
			}*/

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
					System.out.println("Sorry, above preference is not available");
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
					System.out.println("Sorry, above preference is not available");
				}
			}

			else {
				System.out.println("Please enter a vaild Preference Type");
			}
		
		}

		customer.showTicket();
		customers.put(customer.getID(),customer);

	}


	}
	
class Customer{

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
	
	Customer(String vehicleType, int ID, int preference) {
		
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

	void showTicket() {
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

	int getID() {
		return ID;
	}
	
	void changeSlotIndex(int index) {
		slotIndex = index;
	}

	int getSlotIndex() {
		return slotIndex;
	}
	
	void changeFloorNumber(int num) {
		floorNumber = num;
	}

	int getFloorNumber() {
		return floorNumber;
	}

	void setEntryTime() {
		entryTime = LocalDateTime.now();
	}

	LocalDateTime getEntryTime() {
		return entryTime;
	}

	void setCharge() {
        chargeOpted = true;
    }
    boolean getCharge() {
        return chargeOpted;
	}
	
	void setPaymentFee(double paymentFee) {
		this.paymentFee = paymentFee;
	}

	void toPay() {
		paymentStatus = true;
	}

	boolean getPaymentStatus() {
		return paymentStatus;
	}

	double getPaymentFee() {
		return paymentFee;
	}
}

class CommonFloor{

	boolean []motorcycle;
	boolean []electric;
	boolean []large;
	boolean []handicapped;
	boolean []compact;
	
	int motorcycleEmptySpots;
	int electricEmptySpots;
	int largeEmptySpots;
	int handicappedEmptySpots;
	int compactEmptySpots;

	int floorNumber;
	
	CommonFloor(int compactSize, int largeSize, int electricSize, int handicappedSize, int motorcyclesize, int floor) {

		motorcycle = new boolean[motorcyclesize];
		electric = new boolean[electricSize];
		large = new boolean[largeSize];
		handicapped = new boolean[handicappedSize];	
		compact = new boolean[compactSize];

		setArrays();

		motorcycleEmptySpots = motorcyclesize;
		electricEmptySpots = electricSize;
		largeEmptySpots = largeSize;
		handicappedEmptySpots = handicappedSize;
		compactEmptySpots = compactSize;

		floorNumber = floor;
	}

	void setArrays() {
		for(int i = 0; i < motorcycle.length; i++)
			motorcycle[i] = false;
		for(int i = 0; i < electric.length; i++)
			electric[i] = false;
		for(int i = 0; i < large.length; i++)
			large[i] = false;
		for(int i = 0; i < handicapped.length; i++)
			handicapped[i] = false;
		for(int i = 0; i < compact.length; i++)
			compact[i] = false;
	}

	boolean isAvailable(int preferenceType) {

		if(preferenceType == 2) {

			if(motorcycleEmptySpots == 0)
				return false;
			else
				return true;

		}

		else if(preferenceType == 3) {

			if(electricEmptySpots == 0)
				return false;
			else
				return true;

		}

		else if(preferenceType == 4) {

			if(largeEmptySpots == 0)
				return false;
			else
				return true;

		}

		else if(preferenceType == 5) {

			if(handicappedEmptySpots == 0)
				return false;
			else
				return true;

		}

		else {

			if(compactEmptySpots == 0)
				return false;
			else
				return true;

		}
	}

	void assignSlot(int index, int preference) {

		--index;

		if(preference == 2) {
			motorcycle[index] = true;
			--motorcycleEmptySpots;
		}
		if(preference == 3) {
			electric[index] = true;
			--electricEmptySpots;
		}
		if(preference == 4) {
			large[index] = true;
			--largeEmptySpots;
		}
		if(preference == 5) {
			handicapped[index] = true;
			--handicappedEmptySpots;
		}
		if(preference == 6) {
			compact[index] = true;
			--compactEmptySpots;
		}
		
	}
	void releaseSlot(int index, int preference) {

		--index;

		if(preference == 2) {
			motorcycle[index] = false;
			++motorcycleEmptySpots;
		}
		if(preference == 3) {
			electric[index] = false;
			++electricEmptySpots;
		}
		if(preference == 4) {
			large[index] = false;
			++largeEmptySpots;
		}
		if(preference == 5) {
			handicapped[index] = false;
			++handicappedEmptySpots;
		}
		if(preference == 6) {
			compact[index] = false;
			++compactEmptySpots;
		}
		
	}

	boolean checkIndex(int preference,int index) {
		--index;
		if(preference == 2) {
			if(index < 0 || index >= motorcycle.length || motorcycle[index] == true)
				return false;
			else
				return true;
		}
		else if(preference == 3) {
			if(index < 0 || index >= electric.length || electric[index] == true)
				return false;
			else
				return true;
		}
		else if(preference == 4) {
			if(index < 0 || index >= large.length || large[index] == true)
				return false;
			else
				return true;
		}
		else if(preference == 5) {
			if(index < 0 || index >= handicapped.length || handicapped[index] == true)
				return false;
			else
				return true;
		}
		else {
			if(index < 0 || index >= compact.length || compact[index] == true)
				return false;
			else
				return true;
		}
	}

	public void showSpots(int preference) {
		
		if(preference == 2) {
			for(int i = 0; i < motorcycle.length; i++) {
				if(motorcycle[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		else if(preference == 3) {
			for(int i = 0; i < electric.length; i++) {
				if(electric[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		else if(preference == 4) {
			for(int i = 0; i < large.length; i++) {
				if(large[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		else if(preference == 5) {
			for(int i = 0; i < handicapped.length; i++) {
				if(handicapped[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		else if(preference == 6) {
			for(int i = 0; i < compact.length; i++) {
				if(compact[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		System.out.println();
	}
	
}

class TruckFloor {
	
	private boolean []truck;
	
	private int truckEmptySpots,capacity;
	TruckFloor(int capacity) {

		truck = new boolean[capacity];
		this.truckEmptySpots = capacity;
		this.capacity = capacity;

		for(int i = 0; i < truck.length; i++)
			truck[i] = false;

	}

	boolean isAvailable() {

		if(truckEmptySpots == 0)
			return false;
		else
			return true;

	}

	public void assignSlot(int index) {

		truck[index - 1] = true;
		--truckEmptySpots;
	}
	
	public void releaseSlot(int index) {
		truck[index-1] = false;
		++truckEmptySpots;
	}
	
	public void showSpots() {
		for(int i = 1; i <= capacity; ++i) {
			if(truck[i-1] == false)
				System.out.print(i + " ");
		}
		System.out.println();
	}

	boolean checkIndex(int index) {
		--index;
		if(index >= 0 && index < capacity && truck[index] == false)
			return true;
		return false;
			
	}
	
	public boolean[] getTruckSpots() {
		return truck;
	}
}

class Payment {

	private double rate;
	private LocalDateTime entry;
	private LocalDateTime exit;
	private double duration;
	private boolean isCharge;
	private int charge = 5;

	Payment(double rate, boolean isCharge, LocalDateTime ent, LocalDateTime ext) {
		this.rate = rate;
		entry = ent;
		exit = ext;
		this.isCharge = isCharge;
	}

	void calculateDuration() {
		Duration dur = Duration.between(entry, exit);
		duration = (double) dur.getSeconds() / 3600;
	}

	double calculateFee() {
		double fee = 0;

		if(duration < 1) {
			fee += (rate * duration);
		}
		else {
			fee += (rate * 1);
			
			if(duration < 3) {
				fee += (rate/2 * (duration - 1));
			}
			else {
				fee += (rate/2 * 2);
				fee += (rate/4 * (duration - 3));
			}
		}
		if(isCharge) {
			fee += charge * duration;
		}
		return fee;
	}


}

public class ParkingLot {

	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.start();
	}

}