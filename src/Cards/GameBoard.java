package Cards;

import java.util.ArrayList;

import Cards.Person.PersonAction;

public class GameBoard {
	
	public enum GameState {
		PlayerWon, DealerWon, Draw, Continue, Unknown
	}
	
	private Player player;
	private Dealer dealer;
	private Decks decks;
	private GameLogic logic;
	
	public GameBoard(Player player, Decks decks) {
		
		this.player = player;	
		this.decks = decks;
		this.dealer = new Dealer("dealer");
		this.logic = new BlackjackLogic();
		
		System.out.println("The deck size is" + Integer.toString(this.decks.allCards.size()));
		
	}
	
	public void initializeGame() {
		//first card is set down on the dealer
		dealPerson(this.dealer, true);
		dealPerson(this.dealer, false);
		
		for(int i=0; i<2; i++) {
			dealPerson(this.player, false);
		}
		
		printPersonState(this.dealer);
		printPersonState(this.player);
	}
	
	private void dealPerson(Person p, Boolean down) {
		Card c = this.decks.popCard();
		c.setFlipped(down);
		p.pushCurrentGameCard(c);
	}
	
	private void printPersonState(Person p) {
		System.out.print("Person name and states are: ");
		System.out.println(p.toString());
		for(Card c : p.getCurrentGameCards()) {
			System.out.println(c.toString());
		}
		//calculate person's cards
		System.out.println(this.logic.playerCardsValue(p));
	}
	
	
	public GameState dealAndIsGameOver(PersonAction action) {
		
		if(action == PersonAction.HIT) {
			Card c = this.decks.popCard();
			c.setFlipped(false);
			this.player.pushCurrentGameCard(c);
			
			printPersonState(this.dealer);
			printPersonState(this.player);
			
			if(this.logic.playerCardsValue(this.player) > 21 ) {
				return GameState.DealerWon;
			}
			else if(this.logic.playerCardsValue(this.player) == 21) {
				return GameState.PlayerWon;
			}
			else {
				return GameState.Continue;
			}
			
		}
		
		if(action == PersonAction.STAND) {

			//flip the dealer's first card
			this.dealer.getCurrentGameCards().get(0).setFlipped(false);

			do {
				System.out.println("inside this loop");
				Card c = this.decks.popCard();
				c.setFlipped(false);
				this.dealer.pushCurrentGameCard(c);
			} while(this.logic.playerCardsValue(this.dealer) < this.logic.playerCardsValue(this.player));
			
			printPersonState(this.dealer);
			printPersonState(this.player);

			if(this.logic.playerCardsValue(this.dealer) > 21) {
				return GameState.PlayerWon;
			} 
			else if(this.logic.playerCardsValue(this.dealer) == 21) {
				//this assumes the player would have already hit and won if he got a 21
				return GameState.DealerWon;
			}
			else if(this.logic.playerCardsValue(this.dealer) > this.logic.playerCardsValue(this.player)) {
				return GameState.DealerWon;
			}
			else {
				return GameState.PlayerWon;
			}
		}
		
		else {
			return GameState.Unknown;
		}
						
	}
	
	
	

}
