package Cards;

/*
 * This class is responsible for keeping the state of a dealer
 */
public class Dealer extends Person {
	
	public Dealer(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
