package Cards;

import java.util.ArrayList;

public class Person {
	
	private String name;
	
	public enum PersonAction {
		HIT, STAND, LOGOUT, LOGIN;
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	private ArrayList<Card> currentGameCards = new ArrayList<Card>();
	
	public ArrayList<Card> getCurrentGameCards() {
		return currentGameCards;
	}

	public void pushCurrentGameCard(Card c) {
		this.currentGameCards.add(c);
	}
	
	public void resetDeck() {
		this.currentGameCards = new ArrayList<Card>();
	}

}
