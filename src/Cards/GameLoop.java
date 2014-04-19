package Cards;

import java.util.Date;
import java.io.IOException;
import java.util.Scanner;

import Cards.GameBoard.GameState;
import Cards.Person.PersonAction;

public class GameLoop {

	private static PersonAction parseMove(String move) {
		
		if(move.equals("hit")) {
			return PersonAction.HIT;
		}
		else if(move.equals("stand")) {
			return PersonAction.STAND;
		}
		else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);		
		Date d = new Date();
		
		//lets play one round with one player
		Player Vijay = new Player("Vijay", 100);
		
		//lets make a few decks
		Decks new_deck = new Decks(10, 1000, d.getTime());
		
		//initialize the game
		GameBoard board = new GameBoard(Vijay, new_deck);
		board.initializeGame();
		
		PersonAction action;
		GameState state;
		do {
			System.out.println("What is your move?");
			String move = scanner.next();
			action = parseMove(move);
			state = board.dealAndIsGameOver(action);
			
		} while(state.equals(GameState.Continue));
		
		System.out.println(state.toString());
		
	}
	
	
}
