package Cards;

import Cards.Card.CardFace;
import Cards.Person.PersonAction;

/*
 * This class is responsible for dealing the cards to the player and the dealer after an action
 */
public class BlackjackGameBoard extends GameBoard {

	public BlackjackGameBoard(Player player, Decks decks, GameLogic logic) {
		super(player, decks, logic);		
	}
	
	public void initializeGame() {
		
		//This system assumes american hole blackjack. If someone wants to implement
		//the european version of non-hole blackjack, just deal the first card face up.
		
		//first card is set down for the dealer
		dealCardToPersonFaceUpOrDown(this.dealer, CardFace.FaceDown);
		//second card is set face up for the dealer
		dealCardToPersonFaceUpOrDown(this.dealer, CardFace.FaceUp);
		
		for(int i=0; i<2; i++) {
			dealCardToPersonFaceUpOrDown(this.player, CardFace.FaceUp);
		}
		
		printGameState();
	}
		
	/*
	 * This method is responsible for dealing cards after a player has made a choice
	 */
	public void dealCardsBasedOnAction(PersonAction action) {
		
		if(action == PersonAction.HIT) {
			dealCardToPersonFaceUpOrDown(this.player, CardFace.FaceUp);
		}
		
		if(action == PersonAction.STAND) {
			//flip the dealer's first card
			this.dealer.getCurrentGameCards().get(0).setCardFace(CardFace.FaceUp);

			//Deal cards to the dealer until his/her deck value is greater than the player's.
			do {
				dealCardToPersonFaceUpOrDown(this.dealer, CardFace.FaceUp);
			} while(this.logic.playerCardsValue(this.dealer) < this.logic.playerCardsValue(this.player));
		}
		
		printGameState();						
	}	
	
	public void printGameState() {
		printPersonStats(this.dealer);
		printPersonStats(this.player);
	}
	
	public void printPersonStats(Person p) {
		System.out.println(p.toString());
		System.out.println("The face up cards are: ");
		
		for(Card c : p.getCurrentGameCards()) {
			System.out.println(c.toString());
		}
		
		System.out.println("Deck value is: " + this.logic.playerCardsValue(p));
	}	

}

