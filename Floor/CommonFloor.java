package Floor;

public class CommonFloor implements floor{//the class implements the method of floor interface

	private boolean []motorcycle;//boolean array to show motorcycle spots
	private boolean []electric;//boolean array to show electric car spots
	private boolean []large;//boolean array to show large vehicle type spots
	private boolean []handicapped;//boolean array to show handicapped vehicle spots
	private boolean []compact;//boolean array to show compact vehicle type spots
	
	private int motorcycleEmptySpots;//number of empty spots for motorcycle
	private int electricEmptySpots;//number of empty spots for electric car
	private int largeEmptySpots;//number of empty spots for large vehicle type
	private int handicappedEmptySpots;//number of empty spots for handicapped vehicle type
	private int compactEmptySpots;//number of empty spots for compact vehicle type

	
	public CommonFloor(int compactSize, int largeSize, int electricSize, int handicappedSize, int motorcyclesize, int floor) {
		//initializing the corresponding boolean arrays
		motorcycle = new boolean[motorcyclesize];
		electric = new boolean[electricSize];
		large = new boolean[largeSize];
		handicapped = new boolean[handicappedSize];	
		compact = new boolean[compactSize];

		setArrays();
//initializing the corresponding spots
		motorcycleEmptySpots = motorcyclesize;
		electricEmptySpots = electricSize;
		largeEmptySpots = largeSize;
		handicappedEmptySpots = handicappedSize;
		compactEmptySpots = compactSize;
	}

	void setArrays() {//set all the spots to false or unoccupied at the start
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

	public boolean isAvailable(int preferenceType) {// if slots for preference type are available

		if(preferenceType == 2) {// check if empty spots for motorcycle are available

			if(motorcycleEmptySpots == 0)
				return false;
			else
				return true;

		}

		else if(preferenceType == 3) {// check if empty spots for electric car are available

			if(electricEmptySpots == 0)
				return false;
			else
				return true;

		}

		else if(preferenceType == 4) {// check if empty spots for large vehicle are available

			if(largeEmptySpots == 0)
				return false;
			else
				return true;

		}

		else if(preferenceType == 5) {// check if empty spots for handicapped vehicle type are available

			if(handicappedEmptySpots == 0)
				return false;
			else
				return true;

		}

		else {// check if empty spots for compact vehicles are available

			if(compactEmptySpots == 0)
				return false;
			else
				return true;

		}
	}

	public void assignSlot(int index, int preference) {//method to assign the slot based on preference

		--index;

		if(preference == 2) {//assign the slot for motorcycle
			motorcycle[index] = true;
			--motorcycleEmptySpots;//decrease the number of available spots
		}
		if(preference == 3) {//assign the slot for electric car
			electric[index] = true;
			--electricEmptySpots;//decrease the number of available spots
		}
		if(preference == 4) {//assign the slot for large vehicle
			large[index] = true;
			--largeEmptySpots;//decrease the number of available spots
		}
		if(preference == 5) {//assign the slot for handicapped vehicle
			handicapped[index] = true;
			--handicappedEmptySpots;//decrease the number of available spots
		}
		if(preference == 6) {//assign the slot for compact vehicle
			compact[index] = true;
			--compactEmptySpots;//decrease the number of available spots
		}
		
	}
	public void releaseSlot(int index, int preference) {//method to release the slot based on preference

		--index;

		if(preference == 2) {//release the slot for motorcycle
			motorcycle[index] = false;
			++motorcycleEmptySpots;//increase the number of available spots
		}
		if(preference == 3) {//release the slot for electric car
			electric[index] = false;
			++electricEmptySpots;//increase the number of available spots
		}
		if(preference == 4) {//release the slot for large vehicle
			large[index] = false;
			++largeEmptySpots;//increase the number of available spots
		}
		if(preference == 5) {//release the slot for handicapped vehicle
			handicapped[index] = false;
			++handicappedEmptySpots;//increase the number of available spots
		}
		if(preference == 6) {//release the slot for compact vehicle
			compact[index] = false;
			++compactEmptySpots;//increase the number of available spots
		}
		
	}

	public boolean checkIndex(int preference,int index) {// check if given index for a preference is available or not
		--index;
		if(preference == 2) {// check if given index for a motor cycle is available or not
			if(index < 0 || index >= motorcycle.length || motorcycle[index] == true)
				return false;
			else
				return true;
		}
		else if(preference == 3) {// check if given index for a electric car is available or not
			if(index < 0 || index >= electric.length || electric[index] == true)
				return false;
			else
				return true;
		}
		else if(preference == 4) {// check if given index for a large vehicle is available or not
			if(index < 0 || index >= large.length || large[index] == true)
				return false;
			else
				return true;
		}
		else if(preference == 5) {// check if given index for a handicapped vehicle is available or not
			if(index < 0 || index >= handicapped.length || handicapped[index] == true)
				return false;
			else
				return true;
		}
		else {// check if given index for a compact vehicle is available or not
			if(index < 0 || index >= compact.length || compact[index] == true)
				return false;
			else
				return true;
		}
	}
	public void showSpots(int preference) {//show the available spots for a preference
		
		if(preference == 2) {
			for(int i = 0; i < motorcycle.length; i++) {//show the available spots for motorcycle 
				if(motorcycle[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		else if(preference == 3) {//show the available spots for electric car
			for(int i = 0; i < electric.length; i++) {
				if(electric[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		else if(preference == 4) {//show the available spots for large vehicle
			for(int i = 0; i < large.length; i++) {
				if(large[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		else if(preference == 5) {
			for(int i = 0; i < handicapped.length; i++) {//show the available spots for handicapped
				if(handicapped[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		else if(preference == 6) {
			for(int i = 0; i < compact.length; i++) {//show the available spots for compact vehicle
				if(compact[i] == false)
					System.out.print((i + 1) + " ");
			}
		}

		System.out.println();
	}
	
}
