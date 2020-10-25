# CaseStudy1-ParkingLot-Room9

Please fork the repository by clicking on the fork button on the top-right corner on the page
The new repository created will be similar looking but will have your username/reponame instead of the main one.

Then click on the green "code" button and copy the url

Open your cmd prompt and traverse to the directory where you want to have your repo for your local machine.
Then use the command - 

git clone Copied-URL

And you will get your clone

# For Sir:

Parking Lot Case Study

Team members :

GADDAM KISHOR RAVI          CS19B014
MUPPANA GUNA SRI NITESH     CS19B028
NAMAN SHARMA                CS19B029
SINGH SAGAR BHUPENDRA       CS19B038
M HARISH PRADYOT            CS19B047

File dependency : Directory should contain file named "input.txt" containing number of spots and rates for each spot.
	If the admin wishes to make any change to the system please modify input.txt file.
	Order of input inside "input.txt" : numberOfFloors
					    numberOfTruckSpots
					    numberofMotorCycleSpots
					    numberOfElectricSpots
					    numberOfLargeSpots
					    numberOfHandicappedSpots
					    numberOfCompactSpots
		                            rate for truckSpots
                                            rate for motorCycles
                                            rate for electric car
                                            rate for large
                                            rate for handicapped
                                            rate for compact
	Rate is dynamic based on parking hours.
	-> rate after one hour    = rate/2
	-> rate after three hours = rate/4
                                             
	You can compile the program normally using "javac ParkinLot.java" but if
any classnotDef exception is encountered then please compile each package separately 
and then compile the ParkinLot.java file normally.

To start program admin should enter correct PIN (PIN : "GOD").

If admin wants to add a new customer than he should give input as 1 and should take necessary
information from customer.

If any customer wants to leave than admin should give input as 0 and customer gives his ID to admin so that
he can claculate his payment and collect it.

Parking Lot allows presently five type of vehicles.
Ground floor is allowed to park only trucks.
All the common floors are available for remaining 4 types of vehicle.

Ground floor has presently 1 preference type for parking truck.
Commom floors has presently 5 preference types for parking vehicles in each floor.

vehicle type                  preferences availble
Truck                         truck
MotorCycle                    motorCycle
Electric Car                  electric (provision is made available for charging)
Van                           large, handicapped
Car                           large, handicapped, compact.

Parking Lot have multiple entry and exit points, but since they are identical we treat them as same.

Customer has flexibility of choosing preference type, floor number and slot index if available.
For each new customer system assigns a unique ID which is used for payment etc.

Customer can pay through cash or credit.

We have purposely not provided any termination statement in the program.
To Terminate the program use Ctrl + c



# Example input-output format :


Enter your PIN,please
God
Please enter a valid pin. You have 2 tries remaining
GOD
Welcome Administrator:
Press 0 if the vehicle is already parked else press any other number for new entry.
1
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
ENTRY PANEL

What is your Vehicle Type :
1 - Truck               : 40.0/hour             : available
2 - MotorCycle          : 10.0/hour             : available
3 - Electric Car        : 20.0/hour             : available
4 - Van >                                       : available
5 - Car >                                       : available
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
3
Which floor do you want..?
Floor 1 : available
Floor 2 : available
1
Which Spot do you want for your vehicle ?
1
1

Would you like to opt for charging your car?
0 - No
1 - Yes
0

Allotment successfull and collect your ticket

////////////////////////////////////////////
Customer ID: 1
Vehicle Type : Electric Car
Preference Type : Electric Car
Slot Number: 1
FloorNumber: 1
PaymentStatus: Not Done
PaymentFee: 0.0
Entry Time : 2020/10/25 20:50:26
Charge : Not Opted
////////////////////////////////////////////

Press 0 if the vehicle is already parked else press any other number for new entry.
0
Please enter your ID
3
Please enter a valid ID
2
Please enter a valid ID
-9
Please enter a valid ID
1

|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
TO ADMIN : Customer with customerID 1 wants to leave

If the customer is paying by cash press 0
If the customer is paying by credit card press 1
1

Would you like to pay $20.0 at your exclusive panel
Press 0 to pay on your way out
Press 1 to pay
1
Thank You for using our Parking Lot
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

Press 0 if the vehicle is already parked else press any other number for new entry.
1
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
ENTRY PANEL

What is your Vehicle Type :
1 - Truck               : 40.0/hour             : available
2 - MotorCycle          : 10.0/hour             : available
3 - Electric Car        : 20.0/hour             : available
4 - Van >                                       : available
5 - Car >                                       : available
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
3
Which floor do you want..?
Floor 1 : available
Floor 2 : available
2
Which Spot do you want for your vehicle ?
1
1

Would you like to opt for charging your car?
0 - No
1 - Yes
1

Allotment successfull and collect your ticket

////////////////////////////////////////////
Customer ID: 2
Vehicle Type : Electric Car
Preference Type : Electric Car
Slot Number: 1
FloorNumber: 2
PaymentStatus: Not Done
PaymentFee: 0.0
Entry Time : 2020/10/25 20:51:35
Charge : Opted
////////////////////////////////////////////

Press 0 if the vehicle is already parked else press any other number for new entry.
1
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
ENTRY PANEL

What is your Vehicle Type :
1 - Truck               : 40.0/hour             : available
2 - MotorCycle          : 10.0/hour             : available
3 - Electric Car        : 20.0/hour             : available
4 - Van >                                       : available
5 - Car >                                       : available
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
4
--------------------------------------------------------------

VAN

What is your preference Type :
1 - Large                       : 30.0/hour                     : available
2 - Handicapped                 : 15.0/hour                     : available
--------------------------------------------------------------
2
Which floor do you want..?
Floor 1 : available
Floor 2 : available
1
Which Spot do you want for your vehicle ?
1
1

Allotment successfull and collect your ticket

////////////////////////////////////////////
Customer ID: 3
Vehicle Type : Van
Preference Type : Handicapped
Slot Number: 1
FloorNumber: 1
PaymentStatus: Not Done
PaymentFee: 0.0
Entry Time : 2020/10/25 20:51:44
Charge : Not Opted
////////////////////////////////////////////

Press 0 if the vehicle is already parked else press any other number for new entry.
0
Please enter your ID
2

|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
TO ADMIN : Customer with customerID 2 wants to leave

If the customer is paying by cash press 0
If the customer is paying by credit card press 1
1

Would you like to pay $21.0 at your exclusive panel
Press 0 to pay on your way out
Press 1 to pay
1
Thank You for using our Parking Lot
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||