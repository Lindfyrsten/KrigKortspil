package model;
import java.util.ArrayList;
import gui.PlayCard;
import gui.Player;
public class Krig {

	private Player p1, p2, winner;
	private int drawcount = 0;
	private boolean finished = false;
	private ArrayList<PlayCard> pot;

	public Krig(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		pot = new ArrayList<>();
		winner = null;
	}

	public Player compare(PlayCard k1, PlayCard k2) {
		Card c1 = k1.getCard();
		Card c2 = k2.getCard();
		if (c1.compareTo(c2) > 0) {
			return p1;
		} else if (c1.compareTo(c2) < 0) {
			return p2;
		} else {
			threeCards();
		}
		return null;
	}

	public void oneCard() {
		PlayCard c1 = p1.drawSingle();
		PlayCard c2 = p2.drawSingle();
		pot.add(c1);
		pot.add(c2);
		compare(c1, c2);
	}

	// public void takeTurn() {
	// while (!finished) {
	// drawcount++;
	// System.out.println("\nTurn " + drawcount);
	// System.out.println("");
	// oneCard();
	// // System.out.println(winner + " wins: " + pot.toString());
	// winner.getPile().addAll(pot);
	// pot.clear();
	// System.out.println(p1.getCardsRemaining() + " : " + p2.getCardsRemaining());
	// if (p2.getCardsRemaining() == 0 || p1.getCardsRemaining() == 0) {
	// finished = true;
	// gameOver(winner);
	// }
	// }
	// }
	public void gameOver(Player winner) {
		System.out.println("GAME FINISHED!!!");
		System.out.println("Winner: " + winner.getPerson().getName());
		System.out.println("Total draws: " + drawcount);
	}

	public ArrayList<PlayCard> getPot() {
		return pot;
	}

	public void threeCards() {
		ArrayList<PlayCard> cards1 = p1.drawTripple();
		ArrayList<PlayCard> cards2 = p2.drawTripple();
		int i1 = (int) (Math.random() * (cards1.size()));
		int i2 = (int) (Math.random() * (cards2.size()));
		pot.addAll(cards1);
		pot.addAll(cards2);
		compare(cards1.get(i1), cards2.get(i2));
	}
}
