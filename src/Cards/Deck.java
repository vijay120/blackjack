package Cards;

import java.util.ArrayList;

import Cards.Card.Rank;
import Cards.Card.Suit;

public class Deck {
	
	private ArrayList<Card> internalDeck;
	
	public Deck() {
		internalDeck = new ArrayList<Card>();
		
		for(Suit s : Suit.values()) {
			for(Rank r : Rank.values()) {
				Card card = new Card(s, r, false);
				internalDeck.add(card);
			}	
		}
		assert(internalDeck.size() == 52);
	}
	
	public ArrayList<Card> getDeck() {
		return this.internalDeck;
	}
		

}
