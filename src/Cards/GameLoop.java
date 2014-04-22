package Cards;

import java.util.Date;
import java.util.Scanner;

import Cards.GameLogic.Outcome;
import Cards.GameLogic.PlayerAndDealer;
import Cards.Person.PersonAction;


/*
 * This is main driver class for the application. Its responsibility is to get user input,
 * sanitize it, pass it to the game board and gather the outcome of the game. It is similar
 * to the logic of the dealer in the game, with the state of cards that the dealer has.
 */
public class GameLoop {
	
	private static final int NUMBEROFCHIPS = 100;
	private static final int NUMBEROFDECKS = 10;
	private static final int NUMBEROFSHUFFLES = 1000;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);		
		
		//lets play one round with one player
		System.out.println("What is your name?");
		String playerName = scanner.next();
		
		Player player = new Player(playerName, NUMBEROFCHIPS);
	
		while(player.getChips() > 0) {
			
			Date dateForRandomSeed = new Date();
			
			System.out.println("Do you want to play? (yes/no)");
			String wantToPlay = scanner.next();
			PersonAction wagerAction = parseMove(wantToPlay);
			if(wagerAction == PersonAction.LOGOUT) {
				break;
			}
			
			System.out.println("How much do you want to bet?");
			int bet = scanner.nextInt();
			
			while(bet > player.getChips()) {
				System.out.println("Bet is bigger than your chips' value. Try again.");
				bet = scanner.nextInt();
			}
			
			//remove chips from player
			player.subtractChips(bet);
			
			
			//lets make a few decks
			Decks new_deck = new Decks(NUMBEROFDECKS, NUMBEROFSHUFFLES, dateForRandomSeed.getTime());
			
			//set the logic to be blackjack
			GameLogic logic = new BlackjackLogic();
			
			//initialize the game
			GameBoard board = new BlackjackGameBoard(player, new_deck, logic);
			board.initializeGame();
			
			
			PersonAction action;
			Outcome outcome = null;
			
			do {
				System.out.println("What is your move (hit/stand) ?");
				String move = scanner.next();
				action = parseMove(move);
				
				if(action == PersonAction.LOGOUT) {
					break;
				}
				
				board.dealCardsBasedOnAction(action);
				PlayerAndDealer playerAndDealer = board.getPlayerAndDealer();
				outcome = logic.decideGameOutcome(playerAndDealer, action);
				
			} while(outcome.equals(Outcome.Continue) || outcome.equals(Outcome.Unknown));
			
			System.out.println(outcome.toString());
			
			if(outcome == Outcome.PlayerWon) {
				player.addChips(2*bet);
			}
			
			player.resetDeck();
			
		}
		
		scanner.close();
	}
	
	
	
	private static PersonAction parseMove(String move) {
		
		if(move.equals("hit")) {
			return PersonAction.HIT;
		}
		else if(move.equals("stand")) {
			return PersonAction.STAND;
		}
		else if(move.equals("yes")) {
			return PersonAction.LOGIN;
		}
		//I designed the system so that any other anamolous commands will exit the system
		else {
			return PersonAction.LOGOUT;
		}
		
	}
	
	
}
