package Floor;

public class TruckFloor implements floor {//truck floor class
	
	private boolean []truck;//spots for parking trucks
	
	private int truckEmptySpots,capacity;//number of available spots and the capacity
	public TruckFloor(int capacity) {//call the constructor to initialize capacity

		truck = new boolean[capacity];
		this.truckEmptySpots = capacity;
		this.capacity = capacity;

		for(int i = 0; i < truck.length; i++)
			truck[i] = false;//all spots are empty at the beginning

	}
	public boolean isAvailable(int preference) {//if empty spot is available

		if(truckEmptySpots == 0)
			return false;
		else
			return true;

	}

	public void assignSlot(int index,int preference) {

		truck[index - 1] = true;//assign the slot
		--truckEmptySpots;//decrease the number of free spots
	}
	
	public void releaseSlot(int index,int preference) {
		truck[index-1] = false;//release the slot
		++truckEmptySpots;//increase the number of free spots
	}
	
	public void showSpots(int preference) {//show the empty spots for the truck
		for(int i = 1; i <= capacity; ++i) {
			if(truck[i-1] == false)
				System.out.print(i + " ");
		}
		System.out.println();
	}

	public boolean checkIndex(int index,int preference) {//check if entered index is valid to park a truck
		--index;
		if(index >= 0 && index < capacity && truck[index] == false)
			return true;
		return false;
			
	}
	
	public boolean[] getTruckSpots() {//return all the truck spots
		return truck;
	}
}
