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
		
		new Admin().getInput();

		groundFloor = new TruckFloor(numberOfTruckSpots);
		commonFloor = new CommonFloor[numberOfFloors - 1];
		
		for(int i=0; i<numberOfFloors - 1; i++) {
			commonFloor[i] = new CommonFloor(numberOfCompactSpots, numberOfLargeSpots, numberOfElectricSpots, numberOfHandicappedSpots, numberofMotorCycleSpots, i + 1);
		}

		customers = new Vector<Customer>();

		new Admin().start();

	}
	
	private void getInput() {

		numberOfFloors = scan.nextInt();
		numberOfCompactSpots = scan.nextInt();
		numberOfLargeSpots = scan.nextInt();
		numberOfElectricSpots = scan.nextInt();
		numberOfHandicappedSpots = scan.nextInt();
		numberOfTruckSpots = scan.nextInt();

	}

	public void start() {

		while(true) {

			boolean isTruckFull = !groundFloor.isAvailable();
			boolean isMotorCycleFull = true;
			boolean isElectricCarFull = true;
			boolean isLargeFull = true;
			boolean isHadicappedFull = true;
			boolean isCompactFull = true;
			for(int i = 0; i < numberOfFloors - 1; i++) {

				if(!commonFloor[i].isAvailable(2)) {
					isMotorCycleFull = false;
				}
				
				if(!commonFloor[i].isAvailable(3)) {
					isElectricCarFull = false;
				}

				if(!commonFloor[i].isAvailable(4)) {
					isLargeFull = false;
				}

				if(!commonFloor[i].isAvailable(5)) {
					isHadicappedFull = false;
				}

				if(!commonFloor[i].isAvailable(6)) {
					isCompactFull = false;
				}

			}

			// System.out.println("Available Floors");
			// for(int i = 0; i < numberOfFloors - 1; i++) {
			// 	if(commonFloor[i].isAvailable(preferenceType))
			// 		System.out.println("Floor" + (i + 1) );
			// }

			System.out.println("What is your Vehicle Type : ");
			System.out.println("1 - Truck         : price/hour");
			System.out.println("2 - MotorCycle    : price/hour");
			System.out.println("3 - Electric Car  : price/hour");
			System.out.println("4 - Van > ");
			System.out.println("5 - Car > ");
			
			int vehicleType = scan.nextInt();

			if(vehicleType == 1 || vehicleType == 2 || vehicleType == 3) {
				askSinglePreferences(vehicleType);
			}

			else if(vehicleType == 4 || vehicleType == 5) {
				askMultiPreferences(vehicleType);
			}

			else {
				System.out.println("Please enter a vaild Vehicle Type");
			}
				
		}

	}

	private void askSinglePreferences(int vehicleType) {
		
		Customer customer;

		int preferenceType = vehicleType;

		if(preferenceType == 1) {
			groundFloor.isAvailable(preferenceType);
		}

		else {
			
		}

		customer = new Customer(vehicleType, id);
		id++;

	}

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
				customer = new Customer(vehicleType, id, preferenceType + 3);
				id++;
			}

			else if( (preferenceType > 0 && preferenceType <= 3 ) && vehicleType == 5)  {
				customer = new Customer(vehicleType, id, preferenceType + 3);
				id++;
			}

			else {
				System.out.println("Please enter a vaild Preference Type");
			}
		
		}

	}

	private boolean isAvailable(int vehicleType, int preferenceType) {

		if(vehicleType == 1) {

			int i = 0;

			for(i = 0; i < numberOfTruckSpots; i++) {

				

			}
			

		}

		else {

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

		if(preferenceType == 6) {

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

	}

}