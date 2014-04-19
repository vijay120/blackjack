package Cards;

public class Card {
	
	public enum Suit {
		DIAMOND, CLUB, HEART, SPADE 
	}
	
	public enum Rank {
		KING, QUEEN, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, ACE		
	}
	
	private Suit suit;
	private Rank rank;
	private Boolean down;
	
	public Card(Suit suit, Rank rank, Boolean down) {
		this.suit = suit;
		this.rank = rank;
		this.setFlipped(down);
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Boolean getFlipped() {
		return down;
	}

	public void setFlipped(Boolean down) {
		this.down = down;
	}

	@Override
	public String toString() {
		if(!this.down) {
			return this.suit.toString() + " " + this.rank.toString();
		}
		else {
			return "";
		}
	}
	
}
