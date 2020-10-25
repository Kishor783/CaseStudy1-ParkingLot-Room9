package Floor;

public class CommonFloor{

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
	
	public CommonFloor(int compactSize, int largeSize, int electricSize, int handicappedSize, int motorcyclesize, int floor) {

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

	public boolean isAvailable(int preferenceType) {

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

	public void assignSlot(int index, int preference) {

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
	public void releaseSlot(int index, int preference) {

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

	public boolean checkIndex(int preference,int index) {
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
