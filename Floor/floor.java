package Floor;

public interface floor {//floor interface
	public boolean isAvailable(int preference);//checking if preference is available
	public void assignSlot(int index,int preference);//assigning the slot based on index and preference
	public void releaseSlot(int index,int preference);//releasing the slot based on index and preference
	public boolean checkIndex(int index,int preference);//checking if index for the preference is available or not
	public void showSpots(int preference);//show spots for a preference
}
