package Cards;

import static org.junit.Assert.*;

import org.junit.Test;

import Cards.Card.CardFace;
import Cards.Card.Rank;
import Cards.Card.Suit;
import Cards.GameLogic.Outcome;
import Cards.GameLogic.PlayerAndDealer;
import Cards.Person.PersonAction;

public class BlackjackLogicTest {
	
	@Test
	//This test involves the special case
	//where the player has 3 aces, which
	//should give him a value of 13 (1*11+2*1)
	public void testPlayerCardsValueAllAces() {
		
		Person test = new Person("tester");
		BlackjackLogic logic = new BlackjackLogic();

		Card c1 = new Card(Suit.CLUB, Rank.ACE, CardFace.FaceUp);
		test.pushCurrentGameCard(c1);
		Card c2 = new Card(Suit.HEART, Rank.ACE, CardFace.FaceUp);
		test.pushCurrentGameCard(c2);
		Card c3 = new Card(Suit.DIAMOND, Rank.ACE, CardFace.FaceUp);
		test.pushCurrentGameCard(c3);

		assertEquals(logic.playerCardsValue(test), 13);
	}
	
	@Test
	//This test involves the case where there is an Ace, Queen and Ten
	public void testPlayerCardsValueForAceQueenTen() {
		
		Person test = new Person("tester");
		BlackjackLogic logic = new BlackjackLogic();

		Card c1 = new Card(Suit.CLUB, Rank.ACE, CardFace.FaceUp);
		test.pushCurrentGameCard(c1);
		Card c2 = new Card(Suit.HEART, Rank.QUEEN, CardFace.FaceUp);
		test.pushCurrentGameCard(c2);
		Card c3 = new Card(Suit.DIAMOND, Rank.TEN, CardFace.FaceUp);
		test.pushCurrentGameCard(c3);

		assertEquals(logic.playerCardsValue(test), 21);
	}
	
	@Test
	//This test is when the dealer gets a 20 a combination of 3 aces
	//and a 7 after a stand action from a player who has 19. The dealer
	//should win
	public void testDecideGameOutcomeStandAction() {
		
		Dealer d = new Dealer("dealer");
		BlackjackLogic logic = new BlackjackLogic();

		Card c1 = new Card(Suit.CLUB, Rank.ACE, CardFace.FaceUp);
		d.pushCurrentGameCard(c1);
		Card c2 = new Card(Suit.HEART, Rank.ACE, CardFace.FaceUp);
		d.pushCurrentGameCard(c2);
		Card c3 = new Card(Suit.DIAMOND, Rank.ACE, CardFace.FaceUp);
		d.pushCurrentGameCard(c3);
		Card c4 = new Card(Suit.DIAMOND, Rank.SEVEN, CardFace.FaceUp);
		d.pushCurrentGameCard(c4);
				
		Player p = new Player("player", 100);
		Card p1 = new Card(Suit.CLUB, Rank.QUEEN, CardFace.FaceUp);
		p.pushCurrentGameCard(p1);
		Card p2 = new Card(Suit.HEART, Rank.NINE, CardFace.FaceUp);
		p.pushCurrentGameCard(p2);
		
		PlayerAndDealer testPackage = new PlayerAndDealer(p, d);
		assertEquals(logic.decideGameOutcome(testPackage, PersonAction.STAND), Outcome.DealerWon);
	}
	
	@Test
	//This test is when the dealer gets a 19 a combination of 3 aces
	//and a 6 and the player who has 19 and hits stand. The game should continue
	public void testDecideGameOutcomeHitAction() {
		
		Dealer d = new Dealer("dealer");
		BlackjackLogic logic = new BlackjackLogic();

		Card c1 = new Card(Suit.CLUB, Rank.ACE, CardFace.FaceUp);
		d.pushCurrentGameCard(c1);
		Card c2 = new Card(Suit.HEART, Rank.ACE, CardFace.FaceUp);
		d.pushCurrentGameCard(c2);
		Card c3 = new Card(Suit.DIAMOND, Rank.ACE, CardFace.FaceUp);
		d.pushCurrentGameCard(c3);
		Card c4 = new Card(Suit.DIAMOND, Rank.SIX, CardFace.FaceUp);
		d.pushCurrentGameCard(c4);
				
		Player p = new Player("player", 100);
		Card p1 = new Card(Suit.CLUB, Rank.QUEEN, CardFace.FaceUp);
		p.pushCurrentGameCard(p1);
		Card p2 = new Card(Suit.HEART, Rank.NINE, CardFace.FaceUp);
		p.pushCurrentGameCard(p2);
		
		PlayerAndDealer testPackage = new PlayerAndDealer(p, d);
		assertEquals(logic.decideGameOutcome(testPackage, PersonAction.HIT), Outcome.Continue);
	}

}
