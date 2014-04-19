package Cards;

import Cards.Card.Rank;

public class BlackjackLogic extends GameLogic {
	
	private enum BlackjackMethod {
		HARD, SOFT
	}

	public int getCardValue(Card c, BlackjackMethod m) {
		
		if(c.getFlipped()) {
			return 0;
		}
		
		
		if(c.getRank() == Rank.ACE) {
			if(m == BlackjackMethod.HARD) {
				return 11;
			}
			else{
				return 1;
			}
		}
		else if(c.getRank() == Rank.KING || c.getRank() == Rank.QUEEN || c.getRank() == Rank.JACK) {
			return 10;	
		}
		
		// the enums were cleverly positioned
		else {
			return c.getRank().ordinal();
		}
		
	}

	public int playerValue(Person p, BlackjackMethod method) {
		int totalValue = 0;
		
		for(Card c : p.getCurrentGameCards()) {
			totalValue += getCardValue(c, method);
		}
		
		return totalValue;
	}

	public int playerCardsValue(Person p) {
		int hardValue = playerValue(p, BlackjackMethod.HARD);
		
		if(hardValue > 21) {
			return playerValue(p, BlackjackMethod.SOFT);
		}
		else {
			return hardValue;
		}
	}
	
	
}
