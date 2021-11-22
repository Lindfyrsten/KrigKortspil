package service;
import gui.Player;
import javafx.scene.layout.GridPane;
import model.Card;
import model.Card.Rank;
import model.Card.Suit;
import model.Deck;
import model.Person;
public class Service {

	public static Player createPlayer(Person person, GridPane hbox) {
		return new Player(person, hbox);
	}

	public static Person createPerson(String name) {
		return new Person(name);
	}

	public static Player createNPC(GridPane hbox) {
		return new Player(new Person(), hbox);
	}

	public static Deck createFullDeck() {
		Deck d = new Deck();
		for (Suit s : Suit.values()) {
			if (s != Suit.JOKER) {
				Card c = null;
				for (Rank r : Rank.values()) {
					c = new Card(s, r);
					d.addCard(c);
				}
				d.removeCard(c);
			}
		}
		for (int i = 0; i < 3; i++) {
			d.addCard(new Card(Suit.JOKER, Rank.JOKER));
		}
		return d;
	}

	public static Deck createEmptyDeck() {
		return new Deck();
	}
}
