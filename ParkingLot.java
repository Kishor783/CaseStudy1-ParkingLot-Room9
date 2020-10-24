import java.util.*;

interface floor {
	double payment(int hours);
	double payment(int hours, String creditCard);
	boolean isAvailable(int preferenceType);
	void displayBoard();
}

interface payment {
	
}

class Admin {

	Scanner scan = new Scanner(System.in);

	int numberOfFloors;
	int numberOfCompactSpots;
	int numberOfLargeSpots;
	int numberOfElectricSpots;
	int numberOfHandicappedSpots;
	int numberOfTruckSpots;
	int numberofMotorCycleSpots;

	int id = 1;
	
	TruckFloor groundFloor;
	CommonFloor[] commonFloor;

	Vector<Customer> customers;
	
	Admin(){
		
		getInput();

		groundFloor = new TruckFloor(numberOfTruckSpots);
		commonFloor = new CommonFloor[numberOfFloors - 1];
		
		for(int i=0; i<numberOfFloors - 1; i++) {
			commonFloor[i] = new CommonFloor(numberOfCompactSpots, numberOfLargeSpots, numberOfElectricSpots, numberOfHandicappedSpots, numberofMotorCycleSpots, i + 1);
		}

		customers = new Vector<Customer>();


	}
	
	private void getInput() {

		numberOfFloors = scan.nextInt();
		numberOfCompactSpots = scan.nextInt();
		numberOfLargeSpots = scan.nextInt();
		numberOfElectricSpots = scan.nextInt();
		numberOfHandicappedSpots = scan.nextInt();
		numberOfTruckSpots = scan.nextInt();

	}
	
	boolean isPreferenceTypeFull(int preference) {
		if(preference != 1) {
			for(int i = 0; i < numberOfFloors - 1; i++)
				if(!commonFloor[i].isAvailable(preference)) 
					return false;
		 
			return true;
		}
		else {
			return !groundFloor.isAvailable();
		}
	}
	
	void parkingDisplayBoard() {

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("\nENTRY PANEL\n");
		System.out.println("What is your Vehicle Type : ");
		
		if(isPreferenceTypeFull(1))
			System.out.println("1 - Truck         : price/hour         : not available");
		else
			System.out.println("1 - Truck         : price/hour         : available");
		
		if(isPreferenceTypeFull(2))
			System.out.println("2 - MotorCycle    : price/hour         : not available");
		else
			System.out.println("2 - MotorCycle    : price/hour         : available");
		
		if(isPreferenceTypeFull(3))
			System.out.println("3 - Electric Car  : price/hour  	   : not available");
		else
			System.out.println("3 - Electric Car  : price/hour  	   : available");
		
		if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5))
			System.out.println("4 - Van >         					   : not available");
		else
			System.out.println("4 - Van >         					   : available");
		
		if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && isPreferenceTypeFull(6))
			System.out.println("5 - Car >							   : not available");
		else
			System.out.println("5 - Car >							   : available");
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

	}
	  
	void entrancePanelOfFloor(int floorNumber) {
		
		if(floorNumber == 0) {
			if(!groundFloor.isAvailable()) 
				System.out.println("Truck Spots         : not available");
			else
				System.out.println("Truck Spots         : available");
		}
		else {
			if(!commonFloor[floorNumber].isAvailable(2))
				System.out.println("MotorCycle Spots    : not available");
			else
				System.out.println("MotorCycle Spots    : available");
			
			if(!commonFloor[floorNumber].isAvailable(3))
				System.out.println("Electric Car Spots  : not available");
			else
				System.out.println("Electric Car Spots  : available");
			
			if(!commonFloor[floorNumber].isAvailable(4))
				System.out.println("Large Spots         : not available");
			else
				System.out.println("Large Spots         : available");
			
			if(!commonFloor[floorNumber].isAvailable(5))
				System.out.println("Handicapped Spots   : not available");
			else
				System.out.println("Handicapped Spots   : available");
			
			if(!commonFloor[floorNumber].isAvailable(6))
				System.out.println("Compact Spots       : not available");
			else
				System.out.println("Compact Spots       : available");
		}
	}

	void displayAvailableFloors(int preferenceType) {
		
		System.out.println("Which floor do you want..?");
		if(preferenceType == 1) {
			System.out.println("Ground Floor : available");
		}
		else {
			for(int i=1; i<numberOfFloors -1; i++) {
				if(commonFloor[i].isAvailable(preferenceType))
					System.out.println("Floor " + i + "  : available");
			}
		}
	}
	
	void allotFloor(int vehicleType, Customer customer,int preference) {
		displayAvailableFloors(vehicleType);
		
		while(true) {
			int floorNum = scan.nextInt();
			if(vehicleType == 1 && floorNum != 0) {
				System.out.println("Above floor is not available for truck");
			}
			else if(vehicleType == 1 && floorNum == 0) {
				customer.changeFloorNumber(floorNum);
				groundFloor.showSpots();
				while(true) {
					int index = scan.nextInt();
					 if(groundFloor.checkIndex(index)) {
						 groundFloor.assignSlot(index);
						 customer.changeSlotIndex(index);
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
				if( floorNum < 1 || floorNum > numberOfFloors) {
					System.out.println("Above floor is not available for your vehicle type");
					continue;
				}
				if(commonFloor[floorNum].isAvailable(preference)) {
					customer.changeFloorNumber(floorNum);
					while(true) {
						int index = scan.nextInt();
						if(commonFloor[floorNum].checkIndex(preference,index) {
							commonFloor[floorNum].assignSlot(index, preference);
							System.out.println("Allotment successufull and collect your ticket");
						}
						else
							System.out.println("Please enter a valid index");
					}
				}
			}
			
			
		}
	}
	
	public void start() {

		while(true) {
			
			parkingDisplayBoard();

			int vehicleType = scan.nextInt();
			
			if(vehicleType == 1 ) {
				if(isPreferenceTypeFull(1)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
				}
				else {
					Customer customer = new Customer(vehicleType, id, 1);
				    id++;
				}
			}

			if(vehicleType == 2 ) {
				if(isPreferenceTypeFull(2)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
				}
				else {
					Customer customer = new Customer(vehicleType, id, 2);
				    id++;
				}
			}

			if(vehicleType == 3 ) {
				if(isPreferenceTypeFull(3)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
				}
				else {
					Customer customer = new Customer(vehicleType, id, 3);
				    id++;
				}
			}

			if(vehicleType == 4 ) {
				if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
				}
				else {
					askMultiPreferences(4);
				}
			}

			if(vehicleType == 5 ) {
				if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && isPreferenceTypeFull(6)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
				}
				else {
					askMultiPreferences(5);
				}
			}

			if(vehicleType == 1 || vehicleType == 2 || vehicleType == 3) {
				Customer customer;
				if(vehicleType == 1 && isPreferenceTypeFull(1)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				} 
				else {
					customer = new Customer(vehicleType, id, 1);
				    id++;
				}
				
				if(vehicleType == 2 && isPreferenceTypeFull(2)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				} 
				else {
					customer = new Customer(vehicleType, id, 2);
				    id++;
				}
				
				if(vehicleType == 3 && isPreferenceTypeFull(3)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}
				else {
					customer = new Customer(vehicleType, id, 3);
				    id++;
				}
				customers.add(customer);
				
			}

			else if(vehicleType == 4 || vehicleType == 5) {
				if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && vehicleType == 4) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}
				if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && isPreferenceTypeFull(6) && vehicleType == 5) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}
				askMultiPreferences(vehicleType);
			}

			else {
				System.out.println("Please enter a vaild Vehicle Type");
			}
				
		}

	}

//	private void askSinglePreferences(int vehicleType) {
//		
//		Customer customer;
//
//		if(vehicleType == 1) {
//			groundFloor.isAvailable();
//		}
//		else {
//			
//		}
//
//		customer = new Customer(vehicleType, id);
//		id++;
//
//	}
	void preferencesDisplayBoard(int vehicleType) {

		System.out.println("--------------------------------------------------------------");

		if(vehicleType == 4)
			System.out.println("\nVAN\n");
		else
			System.out.println("\nCAR\n");

		System.out.println("What is your preference Type : ");
		System.out.println("2 - Handicapped");

		if(isPreferenceTypeFull(4))
			System.out.println("1 - Large         : price/hour         : not available");
		else
			System.out.println("1 - Large	      : price/hour         : available");

		if(isPreferenceTypeFull(5))
			System.out.println("2 - Handicapped   : price/hour         : not available");
		else
			System.out.println("2 - Handicapped   : price/hour         : available");

		if(vehicleType == 5) {

			if(isPreferenceTypeFull(4))
				System.out.println("3 - Compact       : price/hour         : not available");
			else
				System.out.println("3 - Compact	      : price/hour         : available");
			
		}
		System.out.println("--------------------------------------------------------------");
		
	}
	
	private void askMultiPreferences(int vehicleType) {

		Customer customer;
		preferencesDisplayBoard(vehicleType);

		while(true) {
			int preferenceType = scan.nextInt();
			
			if( (preferenceType > 0 && preferenceType <= 2 ) && vehicleType == 4)  {
				
				if(!isPreferenceTypeFull(preferenceType)) {
					customer = new Customer(vehicleType, id, preferenceType + 3);
				    id++;
				}
				else {
					System.out.println("Sorry, above preference is not avialable");
				}
			}

			else if( (preferenceType > 0 && preferenceType <= 3 ) && vehicleType == 5)  {
				
				if(!isPreferenceTypeFull(preferenceType)) { 
					customer = new Customer(vehicleType, id, preferenceType + 3);
					id++;
				}
				else {
					System.out.println("Sorry, above preference is not avialable");
				}
			}

			else {
				System.out.println("Please enter a vaild Preference Type");
			}
		
		}

	}


	}
	
class Customer{

	private int vehicleType,preference;// Truck = 1; MotorCycle = 2; Electric Car = 3; Van = 4 Large = 4, Handicapped =5; Car = 5 Large = 4, Handicapped = 5, Compact = 6
	private boolean paymentStatus = false;
	private double paymentFee;
	private int floorNumber,ID,slotIndex;
	
	Customer(int vehicleType, int ID,boolean paymentStatus) {
		this.vehicleType = vehicleType;
		this.ID = ID;
		this.preference = vehicleType;
		this.paymentStatus = paymentStatus;
	}
	
	Customer(int vehicleType, int ID, int preference) {
		this.vehicleType = vehicleType;
		this.ID = ID;
		this.preference = preference;
	}
	void showTicket() {
		System.out.println("Customer ID: " + ID);
		System.out.println("Vehicle Type : " + vehicleType);
		System.out.println("Vehicle Type : " + preference);
		System.out.println("Slot Number: " + slotIndex);
		System.out.println("FloorNumber: " + floorNumber);
		System.out.println("PaymentStatus: " + ((paymentStatus) ? "Done" : "Not Done"));
		System.out.println("PaymentFee: " + ((paymentStatus) ? "Not Applicable yet" : paymentFee));
	}
	
	void changeSlotIndex(int index) {
		slotIndex = index;
	}
	
	void changeFloorNumber(int num) {
		floorNumber = num;
	}
	
}

//class Customer{
//
//	int vehicleType; 
//	int ID;
//	int preference; // Truck = 1; MotorCycle = 2; Electric Car = 3; Van = 4 Large = 4, Handicapped =5; Car = 5 Large = 4, Handicapped = 5, Compact = 6
//	boolean paymentStatus = false;
//	double paymentFee;
//	int floorNumber;
//	int slotIndex;
//	
//	Customer(int vehicleType, int ID) {
//		this.vehicleType = vehicleType;
//		this.ID = ID;
//		this.preference = vehicleType;
//	}
//
//	Customer(int vehicleType, int ID, int preference) {
//		this.vehicleType = vehicleType;
//		this.ID = ID;
//		this.preference = preference;
//	}
//	
//}

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

		motorcycleEmptySpots = motorcyclesize;
		electricEmptySpots = electricSize;
		largeEmptySpots = largeSize;
		handicappedEmptySpots = handicappedSize;
		compactEmptySpots = compactSize;

		floorNumber = floor;
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
		void assignSlot(int index,int preference) {
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
		void releaseSlot(int index,int preference) {
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
				if(index < 0 || index >= motorcyclesize || motorcycle[index] == true)
					return false;
				else
					return true;
			}
			else if(preference == 3) {
				if(index < 0 || index >= electricSize || electric[index] == true)
					return false;
				else
					return true;
			}
			else if(preference == 4) {
				if(index < 0 || index >= largeSize || large[index] == true)
					return false;
				else
					return true;
			}
			else if(preference == 5) {
				if(index < 0 || index >= handicappedSize || handicapped[index] == true)
					return false;
				else
					return true;
			}
			else {
				if(index < 0 || index >= compactSize || compact[index] == true)
					return false;
				else
					return true;
			}
		}
	
}

class TruckFloor {
	
	private boolean []truck;
	
	private int truckEmptySpots,capacity;
	TruckFloor(int capacity){
		truck = new boolean[capacity];
		this.truckEmptySpots = capacity;
		this.capacity = capacity;
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
		for(int i = 1; i <= capacity;++i) {
			if(truck[i-1] == false)
				System.out.print(i + " ");
		}
		System.out.println();
	}

	boolean checkIndex(int index) {
		if(--index >= 0 && index < capacity && truck[index] == false)
			return true;
		return false;
			
	}
	
	public boolean[] getTruckSpots() {
		return truck;
	}
}

class TruckFloor {
	
	private boolean []truck;
	
	int truckEmptySpots;
	
	TruckFloor(int truckSize){
		truck = new boolean[truckSize];
		this.truckEmptySpots = truckSize;
	}

	boolean isAvailable() {

		if(truckEmptySpots == 0)
			return false;
		else
			return true;

	}

//	void assignSlot(int index) {
//
//		truck[index - 1] = true;
//
//	}

}

public class ParkingLot {

	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.start();
	}

}