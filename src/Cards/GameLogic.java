package Cards;

import Cards.Person.PersonAction;

public abstract class GameLogic {
	
	public enum Outcome {
		PlayerWon, DealerWon, Draw, Continue, Unknown
	}
	
	//Container class to force API is have player as first argument and dealer as second
	public static class PlayerAndDealer {
		
		public Player player;
		public Dealer dealer;
		
		public PlayerAndDealer(Player p, Dealer d) {
			player = p;
			dealer = d;
		}
	}
		
	abstract int playerCardsValue(Person p);
	
	abstract Outcome decideGameOutcome(PlayerAndDealer personAndDealer, PersonAction action);
	
}
