package Cards;

import java.util.ArrayList;
import java.util.Random;

public class Decks {

	ArrayList<Card> allCards = new ArrayList<Card>();
	
	public Decks(int number_of_decks, int number_of_shuffles, Long seed_of_random) {
		
		for(int i=0; i<number_of_decks; i++) {
			Deck d = new Deck();
			for(Card c : d.getDeck()) {
				allCards.add(c);
			}
		}
		
		shuffleDeck(number_of_shuffles, seed_of_random);
	}
	
	public Card popCard() {
		Card c = allCards.get(0);
		allCards.remove(0);
		return c;
	}
		
	private void shuffleDeck(int number_of_shuffles, Long seed) {
		Random r = new Random(seed);
		for(int i=0; i<number_of_shuffles; i++) {
			int first_index = Math.abs(r.nextInt() % allCards.size());
			int second_index = Math.abs(r.nextInt() % allCards.size());
			swap(first_index, second_index);
		}
	}
	
	private void swap(int first_index, int second_index) {
		Card first_val = allCards.get(first_index);
		Card second_val = allCards.get(second_index);
		allCards.set(first_index, second_val);
		allCards.set(second_index, first_val);
	}
	
	
	
}
