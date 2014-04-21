package Cards;

import Cards.Card.CardFace;
import Cards.Person.PersonAction;
import Cards.GameLogic.PlayerAndDealer;


/*
 * This is an abstract class that can be extended to many similar cards games like blackjack, poker etc.
 * However, the main limitation is that this class solves single dealer-player games.
 */
public abstract class GameBoard {
			
	public Player player;
	public Dealer dealer;
	public Decks decks;
	public GameLogic logic;
		
	/*
	 * Constructor sets up the game board state for a dealer-player game and sets up the game logic
	 */
	public GameBoard(Player player, Decks decks, GameLogic logic) {
		this.player = player;	
		this.decks = decks;
		this.dealer = new Dealer("dealer");
		this.logic = logic;
	}
	
	/*
	 * This method is used for distributing the initial set of cards to the player and dealer
	 * in order to start a game.
	 */
	public abstract void initializeGame();
			
	
	/*
	 * This method is used to update the game state after an user action. This will involve manipulating
	 * the person's deck of cards after the action.
	 */
	public abstract void dealCardsBasedOnAction(PersonAction action);
		
	public void dealCardToPersonFaceUpOrDown(Person p, CardFace upOrDown) {
		Card c = this.decks.popCard();
		c.setCardFace(upOrDown);		
		p.pushCurrentGameCard(c);
	}
	
	public PlayerAndDealer getPlayerAndDealer() {
		return new PlayerAndDealer(this.player, this.dealer);
	}
}
