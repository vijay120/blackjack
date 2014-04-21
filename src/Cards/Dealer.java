package Cards;

public class Dealer extends Person {
	
	public Dealer(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
