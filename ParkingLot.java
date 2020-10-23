import java.util.*;

interface floor {
	double payment(int hours);
	double payment(int hours, String creditCard);
	void displayBoard();
}

interface payment {
	
}

class Admin{

	Scanner scan = new Scanner(System.in);

	int numberOfFloors;
	int numberOfCompactSpots;
	int numberOfLargeSpots;
	int numberOfElectricSpots;
	int numberOfHandicappedSpots;
	int numberOfTruckSpots;
	
	TruckFloor groundFloor;
	CommonFloor[] commonFloor;
	
	Admin(){
		
		new Admin().getInput();

		groundFloor = new TruckFloor(numberOfTruckSpots);
		commonFloor = new CommonFloor[numberOfFloors - 1];
		
		for(int i=0; i<numberOfFloors - 1; i++) {
			commonFloor[i] = new CommonFloor(numberOfCompactSpots, numberOfLargeSpots, numberOfElectricSpots, numberOfHandicappedSpots);
		}

	}
	
	private void getInput() {

		numberOfFloors = scan.nextInt();
		numberOfCompactSpots = scan.nextInt();
		numberOfLargeSpots = scan.nextInt();
		numberOfElectricSpots = scan.nextInt();
		numberOfHandicappedSpots = scan.nextInt();
		numberOfTruckSpots = scan.nextInt();

	}
	
	
}

class Customer{

	int vehicleType; 
	int ID;
	int preference;
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

	boolean []compact;
	boolean []large;
	boolean []electric;
	boolean []handicapped;
	
	int compactEmptySpots;
	int largeEmptySpots;
	int electricEmptySpots;
	int handicappedEmptySpots;
	
	CommonFloor(int compactSize, int largeSize, int electricSize, int handicappedSize) {

		compact = new boolean[compactSize];
		large = new boolean[largeSize];
		electric = new boolean[electricSize];
		handicapped = new boolean[handicappedSize];	
		compactEmptySpots = compactSize;
		largeEmptySpots = largeSize;
		electricEmptySpots = electricSize;
		handicappedEmptySpots = handicappedSize;

	}
	
}

class TruckFloor{
	
	boolean []truck;
	
	int truckEmptySpots;
	
	TruckFloor(int truckSize){
		truck = new boolean[truckSize];
		this.truckEmptySpots = truckSize;
	}

}


class Parking {
    int numberOfFloors = 5; // Ground is reserved for Trucks (0); Others are for other vehicles (1 - n)
    Floor[] floors;
    Parking() {
        floors = new Floor[numberOfFloors];

        floors[0] = new Floor("Truck");

        for(int i = 1; i < numberOfFloors; i++) {
            floors[i] = new Floor("Common");
        }

    }

}


// we will decide the values later and we will create a boolean array to know wheter a spot is free or not
// we can also cluster same kinf of spots
// So if someone wants a large spot, he can go for -> 2-L1; 2 - floor number, l - large, 1 - 1st spot in large
// So we need floor number from customer, his preference and his selection of spot from available spots
// So we will just ask him further questions like a google form and check whether is it available

// We will have a boolean array and we will use its index to number it; we will figure it out later
// we will have seperate boolean arrays for each type of spot like large will have one
// Menu Driven Program

// Number of Truck Slots on Truck Floor = NTF

// Number of compact Slots on common Floor = NCCF
// Number of VIP Slots on common Floor = NVCF
// Number of MotorCycle Slots on common Floor = NMCF
// Number of Electric Slots on Truck common = NECF
// Number of large Slots on common Floor = NLCF

class Floor {

    // Floor should have payment panel
    //// I am thinking we do not need a customer class as we can just create a menu driven program
    //// that will ask the user at every step
    // 

    int floorNumber;
    boolean isTruck;

    Floor(String s) {
        if(s == "Truck") // We will define string compare later
            isTruck = true;
        else
            isTruck = false;
    }

}