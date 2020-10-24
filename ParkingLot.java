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
		
		System.out.println("What is your Vehicle Type : ");
		System.out.println("1 - Truck         : price/hour");
		System.out.println("2 - MotorCycle    : price/hour");
		System.out.println("3 - Electric Car  : price/hour");
		System.out.println("4 - Van > ");
		System.out.println("5 - Car > ");
		
		if(isPreferenceTypeFull(1))
			System.out.println("Truck Spots         : not available");
		else
			System.out.println("Truck Spots         : available");
		
		if(isPreferenceTypeFull(2))
			System.out.println("MotorCycle Spots    : not available");
		else
			System.out.println("MotorCycle Spots    : available");
		
		if(isPreferenceTypeFull(3))
			System.out.println("Electric Car Spots  : not available");
		else
			System.out.println("Electric Car Spots  : available");
		
		if(isPreferenceTypeFull(4))
			System.out.println("Large Spots         : not available");
		else
			System.out.println("Large Spots         : available");
		
		if(isPreferenceTypeFull(5))
			System.out.println("Handicapped Spots   : not available");
		else
			System.out.println("Handicapped Spots   : available");
		
		if(isPreferenceTypeFull(6))
			System.out.println("Compact Spots       : not available");
		else
			System.out.println("Compact Spots       : available");
		
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

	public void start() {

		while(true) {
			
			parkingDisplayBoard();

			int vehicleType = scan.nextInt();

			if(vehicleType == 1 || vehicleType == 2 || vehicleType == 3) {
				
				if(vehicleType == 1 && isPreferenceTypeFull(1)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				} 
				else {
					Customer customer = new Customer(vehicleType, id, 1);
				    id++;
				}
				
				if(vehicleType == 2 && isPreferenceTypeFull(2)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				} 
				else {
					Customer customer = new Customer(vehicleType, id, 2);
				    id++;
				}
				
				if(vehicleType == 3 && isPreferenceTypeFull(3)) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}
				else {
					Customer customer = new Customer(vehicleType, id, 3);
				    id++;
				}
//				askSinglePreferences(vehicleType);
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

	private void askMultiPreferences(int vehicleType) {

		Customer customer;

		System.out.println("What is your prefernce : ");
		System.out.println("1 - Large");
		System.out.println("2 - Handicapped");

		if(vehicleType == 5) {
			System.out.println("3 - Compact");
		}

		int preferenceType = scan.nextInt();

		while(true) {

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

	int vehicleType; 
	int ID;
	int preference; // Truck = 1; MotorCycle = 2; Electric Car = 3; Van = 4 Large = 4, Handicapped =5; Car = 5 Large = 4, Handicapped = 5, Compact = 6
	boolean paymentStatus = false;
	double paymentFee;
	int floorNumber;
	int slotIndex;
	
	Customer(int vehicleType, int ID) {
		this.vehicleType = vehicleType;
		this.ID = ID;
		this.preference = vehicleType;
	}

	Customer(int vehicleType, int ID, int preference) {
		this.vehicleType = vehicleType;
		this.ID = ID;
		this.preference = preference;
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

	void assignSlot(int index) {

		truck[index - 1] = true;

	}

}

public class ParkingLot {

	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.start();
	}

}
