package Cards;

import java.util.ArrayList;

import Cards.Card.CardFace;
import Cards.Card.Rank;
import Cards.Card.Suit;

public class Deck {
	
	private ArrayList<Card> internalDeck;
	
	public Deck() {
		internalDeck = new ArrayList<Card>();
		
		for(Suit s : Suit.values()) {
			for(Rank r : Rank.values()) {
				Card card = new Card(s, r, CardFace.FaceUp);
				internalDeck.add(card);
			}	
		}
	}
	
	public ArrayList<Card> getDeck() {
		return this.internalDeck;
	}
		

}
