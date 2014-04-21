package Cards;

public class Card {
	
	public enum Suit {
		DIAMOND, CLUB, HEART, SPADE 
	}
	
	// Warning: The numbered cards are positioned so that their ordinal values will give
	// their actual values. Do not change the ordering here.
	public enum Rank {
		KING, QUEEN, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, ACE		
	}
	
	public enum CardFace {
		FaceUp, FaceDown
	}
	
	private Suit suit;
	private Rank rank;
	private CardFace upOrDown;
	
	public Card(Suit suit, Rank rank, CardFace upOrDown) {
		this.suit = suit;
		this.rank = rank;
		this.setCardFace(upOrDown);
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public CardFace getCardFace() {
		return this.upOrDown;
	}

	public void setCardFace(CardFace upOrDown) {
		this.upOrDown = upOrDown;
	}

	@Override
	public String toString() {
		
		//If the card face is up, show the value. If not, dont show the value;
		if(this.upOrDown == CardFace.FaceUp) {
			return this.suit.toString() + " " + this.rank.toString();
		}
		else {
			return "Card Face Down";
		}
	}
	
}
