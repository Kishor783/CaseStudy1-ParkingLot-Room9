import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;

import Floor.*;//importing Floor package
import Customer.Customer;//importing Customer package
import Payment.Payment;//importing Payment package

class Admin {

	Scanner fin;
	Scanner scan = new Scanner(System.in);

	private int numberOfFloors;//number of floors

	private int numberOfTruckSpots;//truck capacity
	private int numberofMotorCycleSpots;//motorcycle spot capacity
	private int numberOfElectricSpots;//electric car spot capacity

	private int numberOfLargeSpots;// number of large spots
	private int numberOfHandicappedSpots;//number of handicapped spots
	private int numberOfCompactSpots;//number of compact spots

	Map<Integer,String> vehicles;// a key value pair for mapping integers to vehicle types
	Map<Integer,Double> rates; // a key value pair for mapping integers to rates of vehicles

	int id = 1;//id to assign to customers
	
	TruckFloor groundFloor;//truck floor object representing ground floor
	CommonFloor[] commonFloor;//array of common floor objects representing floors other than ground floor
	Map<Integer,Customer> customers;//a key value pair for mapping ID to customer objects
	
	Admin(){// constructor for administrator class

		try {
            File file = new File("input.txt");//writing the basic properties of parking lot such as number of floors,
            //number of spots for a vehicle etc.
            fin = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            System.out.println(e);//handling error in case input file is missing
        }
		
		getInput();//taking input

		vehicles = new HashMap<Integer,String>();//initializing the map to map vehicles 
		vehicles.put(1,"Truck");// Truck = 1; 
		vehicles.put(2,"MotorCycle");//MotorCycle = 2;
		vehicles.put(3,"Electric Car");//Electric Car = 3
		vehicles.put(4,"Van");//Van = 4
		vehicles.put(5,"Car");//Car = 5

		rates = new HashMap<Integer,Double>();
		rates.put(1,fin.nextDouble());//mapping cost of preference mapped to 1 which is truck
		rates.put(2,fin.nextDouble());//mapping cost of preference mapped to 2 which is motorcycle
		rates.put(3,fin.nextDouble());//mapping cost of preference mapped to 3 which is electric
		rates.put(4,fin.nextDouble());//mapping cost of preference mapped to 4 which is large
		rates.put(5,fin.nextDouble());//mapping cost of preference mapped to 5 which is handicapped
		rates.put(6,fin.nextDouble());//mapping cost of preference mapped to 6 which is compact
		customers = new HashMap<Integer,Customer>();//initializing the to store customers
		groundFloor = new TruckFloor(numberOfTruckSpots);//initializing ground floor object
		commonFloor = new CommonFloor[numberOfFloors - 1];//initializing common floor array
		//commonFloor[0] = 1st Floor

		for(int i = 0; i < numberOfFloors - 1; i++) {
			//initializing common floor array to store the entered number of different preference spots
			commonFloor[i] = new CommonFloor(numberOfCompactSpots, numberOfLargeSpots, numberOfElectricSpots, numberOfHandicappedSpots, numberofMotorCycleSpots, i + 1);
		}

	}
	//private function to take input
	private void getInput() {

		numberOfFloors = fin.nextInt();//inputting number of floors

		numberOfTruckSpots = fin.nextInt();//inputting truck spots
		numberofMotorCycleSpots = fin.nextInt();//inputting number of motorcycle spots
		numberOfElectricSpots = fin.nextInt();//inputting number of electric spots

		numberOfLargeSpots = fin.nextInt();//inputting number of large spots
		numberOfHandicappedSpots = fin.nextInt();//inputting number of handicapped spots
		numberOfCompactSpots = fin.nextInt();//inputting number of compact spots

	}
	//method to check if a preference type is available 
	boolean isPreferenceTypeFull(int preference) {
		if(preference != 1) {
			for(int i = 0; i < numberOfFloors - 1; i++)
				if(commonFloor[i].isAvailable(preference))//to check if a preference type on a floor other than ground floor is available 
					return false;
		 
			return true;
		}
		else {
			return !groundFloor.isAvailable(1);//checking if ground floor is available
		}
	}
	//a method to display parking board at entry panel
	void parkingDisplayBoard() {

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("ENTRY PANEL\n");
		System.out.println("What is your Vehicle Type : ");
		
		if(isPreferenceTypeFull(1))//checking if space for truck is available
			System.out.println("1 - Truck\t\t: " + rates.get(1) + "/hour\t\t: not available");
		else
			System.out.println("1 - Truck\t\t: " + rates.get(1) + "/hour\t\t: available");
		
		if(isPreferenceTypeFull(2))//checking if space for motorcycle is available
			System.out.println("2 - MotorCycle\t\t: " + rates.get(2) + "/hour\t\t: not available");
		else
			System.out.println("2 - MotorCycle\t\t: " + rates.get(2) + "/hour\t\t: available");
		
		if(isPreferenceTypeFull(3))//checking if space for electric car is available
			System.out.println("3 - Electric Car\t: " + rates.get(3) / 100 + "/hour\t\t: not available");
		else
			System.out.println("3 - Electric Car\t: " + rates.get(3) + "/hour\t\t: available");
		
		if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5))//checking if space for van is available
			System.out.println("4 - Van >\t\t\t\t\t: not available");
		else
			System.out.println("4 - Van >\t\t\t\t\t: available");
		
		if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && isPreferenceTypeFull(6))//checking if space for car is available
			System.out.println("5 - Car >\t\t\t\t\t: not available");
		else
			System.out.println("5 - Car >\t\t\t\t\t: available");
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

	}
	  
//method to display available floors for a preference type
	void displayAvailableFloors(int preferenceType) {
		
		System.out.println("Which floor do you want..?");

		if(preferenceType == 1) {// display for truck
			System.out.println("Ground Floor (0) : available");
		}

		else {
			for(int i = 0 ; i < numberOfFloors - 1; i++) {
				if(commonFloor[i].isAvailable(preferenceType))//display floor available for preference other than truck
					System.out.println("Floor " + (i + 1) + "\t: available");
			}
		}

	}
	//method to allot floor base on preference entered by customer
	Customer allotFloor(int vehicleType, Customer customer, int preference) {

		displayAvailableFloors(preference);//displaying floors available for a preference
		
		while(true) {

			int floorNum = scan.nextInt();//taking floor number preferred by user

			if(vehicleType == 1 && floorNum != 0) {//any floor other than ground floor is not available for a truck
				System.out.println("Above floor is currently not available for your truck");
			}

			else if(vehicleType == 1 && floorNum == 0) {//if floor chosen is ground floor for a truck
				
				customer.changeFloorNumber(floorNum);//setting floor number of customer to truck floor
				//calling a method to allot slot index based on preference given by user
				customer = allotSpotIndex(customer, preference, floorNum);
				break;//break when allotment is successful
			}

			else {//if vehicle is not a truck, we check the remaining floors
				if( (floorNum < 1 || floorNum >= numberOfFloors) || !commonFloor[floorNum - 1].isAvailable(preference)) {
					//if entered floor is invalid or is not available
					System.out.println("Above floor is currently not available for your preference type");
					continue;//we continue taking floor number from customer until a valid floor number is entered
				}

				else {
					customer.changeFloorNumber(floorNum);//allot the floor entered by customer
					//calling a method to allot slot index based on preference given by customer
					customer = allotSpotIndex(customer, preference, floorNum);
					break;//break when allotment is successful
				}
			}
			
		}

		return customer;//return the customer with updated attributes such as floor number and slot number
	}
//method to allot spot index chosen by user based on his preference
	Customer allotSpotIndex(Customer customer, int preference, int floorNum) {

		System.out.println("Which Spot do you want for your vehicle ?");

		if(preference == 1) {//if preference is a truck

			groundFloor.showSpots(1);//method to show empty spots for a truck

			while(true) {

				int index = scan.nextInt();//asking for the slot index preferred by user in ground floor

				if(groundFloor.checkIndex(index,1)) {//checking if a slot is available to park a truck

					groundFloor.assignSlot(index,1);//assign the slot 
					customer.changeSlotIndex(index);//update the slot in customer's information
					customer.setEntryTime();//enter the entry time for the customer
					System.out.println("Allotment successfull and collect your ticket");
					break;//break because allotment was successful

				}

				else {//continue asking to enter a valid slot for the truck
					System.out.println("Above index is not available, please choose again");
					continue;
				}

			}
		}

		else {//if vehicle other than truck is to be parked

			commonFloor[floorNum - 1].showSpots(preference);//showing spots for preferred type on floor number entered by user

			while(true) {

				int index = scan.nextInt();//asking for the slot index preferred by user in floor entered
				//checking if slot index is valid to park a vehicle of given preference
				if(commonFloor[floorNum - 1].checkIndex(preference, index)) {
					//assign the slot when it is valid 
					commonFloor[floorNum - 1].assignSlot(index, preference);
					customer.changeSlotIndex(index);//update the customer's information about slot
					
					if(preference == 3) {//in case the vehicle is electric car, we ask for charging option

						System.out.println("\nWould you like to opt for charging your car?");
						System.out.println("0 - No");
						System.out.println("1 - Yes");

						while(true) {

							int chargeInput = scan.nextInt();//taking input to know if customer prefers charging

							if(chargeInput == 0) {//break if user doesn't want charging
								break;
							}

							else if(chargeInput == 1) {//update the customer's information if he wants charge
								customer.setCharge();
								break;
							}

							else {
								System.out.println("Please enter only 0 or 1");//prompt to enter a valid option
							}

						}

					}

					customer.setEntryTime();//setting the customer's entry time
					System.out.println("\nAllotment successfull and collect your ticket");
					break;

				}

				else
					System.out.println("Please enter a valid index");//asking the customer to enter a valid slot index
			}

		}

		
		return customer;//return the customer with update information

	}
	
	public void start() {//starting of the parking lot by administrator 

		while(true) {//if customer wants to leave or a new customer wants to park his vehicle
			System.out.println("Press 0 if the vehicle is already parked else press any other number for new entry.");
			int x = scan.nextInt();//asking for a valid entry to perform the desired operations
			if(x == 0) {//if a customer wants to leave
				// ============== OLD CUSTOMER OUT ===============
				System.out.println("Please enter your ID");//ask for customer's ID
				while(true) {
					int index = scan.nextInt();//take in the ID
					if(customers.containsKey(index)) {//if a valid ID
						customerIsPaying(customers.get(index));//calculate and perform the payment operations before customer leaves
						customers.remove(index);//remove the customer from the list of customers who have their vehicles parked
						break;
					}
					else
						System.out.println("Please enter a valid ID");//ask for valid ID
				}
				continue;//move to the next iteration
			}
			//if new customer has come
			// ============== NEW CUSTOMER IN ===============
			parkingDisplayBoard();//display the parking board to the customer 

			int vehicleType = scan.nextInt();//ask for the vehicle type

			if(vehicleType == 1 || vehicleType == 2 || vehicleType == 3) {
				Customer customer;//if vehicle doesn't have any special preference available
				
				if(vehicleType == 1 && isPreferenceTypeFull(1)) {//if space for trucks is not available
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				} 
				
				else if(vehicleType == 2 && isPreferenceTypeFull(2)) {//if space for motorcycle is not available
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				} 
				
				else if(vehicleType == 3 && isPreferenceTypeFull(3)) {//if space for electric car is not available
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}

				else {//assign and update the customer information
					customer = new Customer(vehicles.get(vehicleType), id, vehicleType);
					id++;//increment the ID for customer which would come after
					customer = allotFloor(vehicleType, customer, vehicleType);//allot the floor based on preference by a method
				}
				
				customer.showTicket();//show the customer his ticket
				customers.put(customer.getID(), customer);//store the customer mapped to his ID in a map
				
			}

			else if(vehicleType == 4 || vehicleType == 5) {//if vehicle is a car or van
				// if neither large nor handicapped spots are available for van
				if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && vehicleType == 4) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}
				//if neither large,compact, handicapped spots are available for car
				else if(isPreferenceTypeFull(4) && isPreferenceTypeFull(5) && isPreferenceTypeFull(6) && vehicleType == 5) {
					System.out.println("Sorry, all preferences are filled for your vehicle type");
					continue;
				}
				
				askMultiPreferences(vehicleType);//method to assign the preference from multiple preference available

			}

			else {
				System.out.println("Please enter a valid Vehicle Type");//asking for valid vehicle type
			}

		}

	}

	public void customerIsPaying(Customer customer) {
		//payment portal for customer
		System.out.println("\n|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("TO ADMIN : Customer with customerID " + customer.getID() + " wants to leave");
		//prompt the administrator that a customer wants to leave
		int preferenceType = customer.getPreferenceType();//get the preference type of the customer
		LocalDateTime exitTime = LocalDateTime.now();//fetch the exiting time of the customer
		//constructor for Payment calculation through Payment class
		Payment payment = new Payment(rates.get(preferenceType), customer.getCharge(), customer.getEntryTime(), exitTime);
		payment.calculateDuration();//find the duration of stay of customer through a method
		
		System.out.println("\nIf the customer is paying by cash press 0");//if customer wants to pay through cash
		System.out.println("If the customer is paying by credit card press 1");//if wants to pay through credit card
		while(true) {

			int input = scan.nextInt();//input to get the payment method 

			if(input == 0) {
				double paymentFee = payment.calculateFee();//calculate the fee for payment through cash
				paymentFee = Math.ceil(paymentFee);//adjust the value
				customer.setPaymentFee(paymentFee);//update the customer's bill 
				break;//break when the payment was successful
			}

			else if(input == 1) {
				double paymentFee = payment.calculateFee();//calculate the fee for payment through cash
				paymentFee = Math.ceil(paymentFee);//adjust the value
				customer.setPaymentFee(paymentFee);//update the customer's bill
				break;//break when the payment was successful
			}

			else {
				System.out.println("Please enter only 0 or 1");//ask the user to enter valid payment method
			}

		}
		//release the slot occupied by the customer 
		if(preferenceType == 1) {
			int index = customer.getSlotIndex();//if the slot is truck
			groundFloor.releaseSlot(index,1);//release the slot
		}
		else {
			int index = customer.getSlotIndex();//get the slot index
			int floorNum = customer.getFloorNumber();//get floor number in in case vehicle is not a truck
			commonFloor[floorNum - 1].releaseSlot(index, preferenceType);//release the slot of based on the preference type
		}

		if(preferenceType == 3) { // FOR ELECTRIC CAR, AT ELECTRIC CAR PANEL
			System.out.println("\nWould you like to pay $" + Math.ceil(customer.getPaymentFee()) +" at your exclusive panel");
			System.out.println("Press 0 to pay on your way out");//if customer wants to pay at exit
			System.out.println("Press 1 to pay");//if customer wants to pay at the exclusive panel
			if(scan.nextInt() == 1) {
				customer.toPay();//pay through the exclusive panel 
			}
		}

		if(!customer.getPaymentStatus()) { // FOR ANY VEHICLE, AT FLOOR PANEL
			
			System.out.println("\nWould you like to pay $" + Math.ceil(customer.getPaymentFee()) +" at the Floor panel");
			System.out.println("Press 0 to pay on your way out");
			System.out.println("Press 1 to pay");
			if(scan.nextInt() == 1) {
				customer.toPay();//pay through floor panel
			}
		}

		if(!customer.getPaymentStatus()) { // FOR ANY VEHICLE, AT EXIT POINT
			System.out.println("\nThis is the exit. Your total fee is $" + Math.ceil(customer.getPaymentFee()));
			customer.toPay(); // Customer Paid
		}
		System.out.println("Thank You for using our Parking Lot");
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");

	}

	void preferencesDisplayBoard(int vehicleType) {
		//display board for vehicle types van and car
		System.out.println("--------------------------------------------------------------");

		if(vehicleType == 4)
			System.out.println("\nVAN\n");
		else
			System.out.println("\nCAR\n");

		System.out.println("What is your preference Type : ");

		if(isPreferenceTypeFull(4))
			System.out.println("1 - Large\t\t\t: " + rates.get(4) + "/hour\t\t\t: not available");
		else
			System.out.println("1 - Large\t\t\t: " + rates.get(4) + "/hour\t\t\t: available");

		if(isPreferenceTypeFull(5))
			System.out.println("2 - Handicapped\t\t\t: " + rates.get(5) + "/hour\t\t\t: not available");
		else
			System.out.println("2 - Handicapped\t\t\t: " + rates.get(5) + "/hour\t\t\t: available");

		if(vehicleType == 5) {

			if(isPreferenceTypeFull(6))
				System.out.println("3 - Compact\t\t\t: " + rates.get(6) * 100 + "/hour\t\t\t: not available");
			else
				System.out.println("3 - Compact\t\t\t: " + rates.get(6) + "/hour\t\t\t: available");
			
		}
		System.out.println("--------------------------------------------------------------");
		
	}
	
	private void askMultiPreferences(int vehicleType) {//when vehicle has multiple preferences

		Customer customer;
		preferencesDisplayBoard(vehicleType);//display board for the chosen vehicle type

		while(true) {
			int preferenceType = scan.nextInt();//get the preference
			
			if( (preferenceType > 0 && preferenceType <= 2 ) && vehicleType == 4)  {//if entered preference is valid for a van
				
				if(!isPreferenceTypeFull(preferenceType + 3)) {
					customer = new Customer(vehicles.get(vehicleType), id, preferenceType + 3);//update the customer information
					id++;//increment the id for next customer
					customer = allotFloor(vehicleType, customer, (preferenceType + 3) );//allot the floor by calling the method
					break;
				}
				else {
					System.out.println("Sorry, above preference is not available.");//if preference is not available
				}
			}

			else if( (preferenceType > 0 && preferenceType <= 3 ) && vehicleType == 5)  {//if entered preference is valid for car
				
				if(!isPreferenceTypeFull(preferenceType + 3)) { 
					customer = new Customer(vehicles.get(vehicleType), id, (preferenceType + 3));//update the customer information
					id++;//increment the id for next customer
					customer = allotFloor(vehicleType, customer, preferenceType + 3);//allot the floor by calling the method
					break;
				}
				else {
					System.out.println("Sorry, above preference is not available.");//if preference is not available
				}
			}

			else {
				System.out.println("Please enter a vaild Preference Type.");//if preference is invalid
			}
		
		}

		customer.showTicket();//print the customer ticket
		customers.put(customer.getID(),customer);//allot the customer and map him/her to the ID

	}


}

public class ParkingLot {
	final static String PIN = "GOD";//PIN which only administrator would know to start the operation of parking lot
	public static void main(String[] args) {
		Admin admin = new Admin();//make an administrator object
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your PIN,please");
		for(int i = 3; i >= 1;--i) {//give the user finite tries to enter correct PIN
			String pin = scan.nextLine();//take the pin entered
			if(pin.equals(PIN)) {
				System.out.println("Welcome Administrator: ");//if pin is correct
				admin.start();//start the parking lot
			}
			else if(i > 1)
				System.out.println("Please enter a valid pin. You have " + (i-1) + " tries remaining");//if pin was wrong
			else
				System.out.println("You tries are exhausted. Contact support.");//tries exhausted
		}
		scan.close();
	}

}