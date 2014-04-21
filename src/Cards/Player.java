package Cards;

public class Player extends Person {
		
	private int chips;
	
	public Player(String name, int number_of_chips) {
		super(name);
		this.chips = number_of_chips;
	}
	
	public int getChips() {
		return this.chips;
	}
	
	public void addChips(int chips) {
		this.chips += chips;
	}
	
	public void subtractChips(int chips) {
		this.chips -= chips;
	}
	
	@Override
	public String toString() {
		return getName() + " Number of chips: " + Integer.toString(this.chips);
	}

}
