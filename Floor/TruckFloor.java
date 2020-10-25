package Floor;

public class TruckFloor {
	
	private boolean []truck;
	
	private int truckEmptySpots,capacity;
	public TruckFloor(int capacity) {

		truck = new boolean[capacity];
		this.truckEmptySpots = capacity;
		this.capacity = capacity;

		for(int i = 0; i < truck.length; i++)
			truck[i] = false;

	}

	public boolean isAvailable() {

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
		for(int i = 1; i <= capacity; ++i) {
			if(truck[i-1] == false)
				System.out.print(i + " ");
		}
		System.out.println();
	}

	public boolean checkIndex(int index) {
		--index;
		if(index >= 0 && index < capacity && truck[index] == false)
			return true;
		return false;
			
	}
	
	public boolean[] getTruckSpots() {
		return truck;
	}
}
