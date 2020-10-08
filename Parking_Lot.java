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

