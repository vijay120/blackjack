package Cards;

import Cards.Card.CardFace;
import Cards.Card.Rank;
import Cards.Person.PersonAction;

/*
 * This class implements the blackjack logic. 
 */
public class BlackjackLogic extends GameLogic {
		
	private enum TypeOfHand {
		HARD, SOFT
	}
		
	// This method calculates the card value depending on the type of hand given.
	public int getCardValue(Card card, TypeOfHand faceUpOrDown) {
		
		// If the card face is down, do not add the value of the card to the publicly advertised value.
		if(card.getCardFace() == CardFace.FaceDown) {
			return 0;
		}
		
		// Since ACE is a special card whose value depends on whether one has a hard or a soft hand,
		// this logic is needed.
		if(card.getRank() == Rank.ACE) {
			if(faceUpOrDown == TypeOfHand.HARD) {
				return 11;
			}
			else{
				return 1;
			}
		}
		// All the other royals get a value of 10 
		else if(card.getRank() == Rank.KING || card.getRank() == Rank.QUEEN || card.getRank() == Rank.JACK) {
			return 10;	
		}
		
		// The number cards are positioned in the enum construct in a way that getting their value is easy.
		// This might make the code more fragile if someone changes the position of the values in the enum,
		// however, I categorize that as a low risk change since one will hardly be manipulating the structure
		// of an enum. I have also made a warning in the enum struct.
		else {
			return card.getRank().ordinal();
		}
		
	}
	
	// This method calculates the player's deck's total value assuming he/she is using a hard hand. If the hard hand
	// is greater than 21, the soft hand approach is used. 
	public int playerCardsValue(Person p) {
		int hardValue = playerValue(p, TypeOfHand.HARD);
		
		if(hardValue > 21) {
			return playerValue(p, TypeOfHand.SOFT);
		}
		else {
			return hardValue;
		}
	}

	private int playerValue(Person p, TypeOfHand faceUpOrDown) {
		int totalValue = 0;
		
		for(Card c : p.getCurrentGameCards()) {
			totalValue += getCardValue(c, faceUpOrDown);
		}
		
		return totalValue;
	}
	
	public Outcome decideGameOutcome(PlayerAndDealer playerAndDealer, PersonAction action) {
		
		Person player = playerAndDealer.player;
		Person dealer = playerAndDealer.dealer;
		
		//BLACKJACK!
		if(playerCardsValue(player) == 21) {
			return Outcome.PlayerWon;
		}	
		// This is the second strongest case ie, if the player gets a 21, before the dealer, the player wins.
		else if(playerCardsValue(dealer) == 21) {
			return Outcome.DealerWon;
		}
		else if(playerCardsValue(player) > 21 &&  playerCardsValue(dealer) <= 21) {
			return Outcome.DealerWon;
		}
		else if(playerCardsValue(player) <= 21 &&  playerCardsValue(dealer) > 21) {
			return Outcome.PlayerWon;
		}
		//These two cases below are less stronger conditions when the dealer outperforms the player within the 21 bound
		//and vice versa for the second case.
		else if(playerCardsValue(dealer) > playerCardsValue(player)) {
			if(action == PersonAction.HIT) {
				return Outcome.Continue;				
			}
			else if(action == PersonAction.STAND) {
				return Outcome.DealerWon;
			}
			else {
				return Outcome.Unknown;
			}
		}
		else if(playerCardsValue(dealer) <= playerCardsValue(player)) {
			if(action == PersonAction.HIT) {
				return Outcome.Continue;				
			}
			else if(action == PersonAction.STAND) {
				return Outcome.PlayerWon;
			}
			else {
				return Outcome.Unknown;
			}
		}
		// This case should not happen! At any time, either the player or dealer exclusively can be above 21. This is 
		// an invariant property of blackjack.
		else if(playerCardsValue(player) > 21 &&  playerCardsValue(dealer) > 21) {
			return Outcome.Unknown;
		}
		else {
			return Outcome.Continue;
		}
		
	}
		
}
