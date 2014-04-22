package Cards;

import java.util.ArrayList;

import Cards.Card.CardFace;
import Cards.Card.Rank;
import Cards.Person.PersonAction;

/*
 * This class implements the blackjack logic. 
 */
public class BlackjackLogic extends GameLogic {
		
	// This method calculates the card value depending on the type of hand given.
	public int getCardValue(Card card) {
		
		// If the card face is down, do not add the value of the card to the publicly advertised value.
		if(card.getCardFace() == CardFace.FaceDown) {
			return 0;
		}
		
		// Since ACE is a special card whose value depends on whether one has a hard or a soft hand.
		// We default the card to a soft hand until we exceed 21 for the deck. This logic for converting
		// to hard is handled in the playerCardsValue method.
		if(card.getRank() == Rank.ACE) {
			return 11;
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
	
	//This method defaults to valuating the cards to the "soft" value until the hard value is needed.
	public int playerCardsValue(Person p) {
		
		int finalValue = 0;
		
		for(Card c : p.getCurrentGameCards()) {
			finalValue += getCardValue(c);
		}
		
		if(finalValue > 21) {
			//since the final value is greater than 21, lets us negate 10 for each Ace in the deck
			int numOfAces = numberOfAcesInDeck(p.getCurrentGameCards());
			
			for(int i=1; i <= numOfAces; i++) {
				finalValue -= 10;
				
				//If the value is less than 21, then break out since this will be the optimum value
				if(finalValue <= 21) {
					break;
				}
			}
		}
		
		return finalValue;
	}
	
	public int numberOfAcesInDeck(ArrayList<Card> deck) {
		int aceCounter = 0;
		for(Card c : deck) {
			if(c.getRank() == Rank.ACE) {
				aceCounter++;
			}
		}
		return aceCounter;
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
		//and vice versa for the second case. We already know that the dealer's total card value will have to be above 17
		//since that in enforced by the game board.
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
