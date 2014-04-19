package Cards;

import java.util.ArrayList;

public class Person {
	
	private String name;
	
	public enum PersonAction {
		HIT, STAND;
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	private ArrayList<Card> currentGameCards = new ArrayList<Card>();
	
	public ArrayList<Card> getCurrentGameCards() {
		return currentGameCards;
	}

	public void pushCurrentGameCard(Card c) {
		this.currentGameCards.add(c);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	

}
