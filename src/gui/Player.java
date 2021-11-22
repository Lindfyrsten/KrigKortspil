package gui;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Person;
/**
 * @author Kristian Lindbjerg
 */
public class Player {

	private StackPane deckPane, pilePane, deckView, pileView;
	private GridPane hq;
	private int deckSize, pileSize;
	private Person person;
	private Label lblDeck, lblPile;
	private String st = "-fx-border-style: solid;" + " -fx-border-width: 1;" + " -fx-border-padding: 0;"
			+ " -fx-border-radius: 20;" + " -fx-border-color: black darkred;";

	public Player(Person person, GridPane hq) {
		deckSize = 0;
		pileSize = 0;
		this.hq = hq;
		this.person = person;
		set();
	}

	public void set() {
		// ImageView frame = new ImageView();
		// frame.setImage(new Image("/cardImages/frameVic.jpg/"));
		// frame.setFitHeight(KrigApp.cardH + 50);
		// frame.setPreserveRatio(true);
		deckPane = new StackPane();
		lblDeck = new Label();
		lblDeck.setFont(Font.font("IMPACT", FontWeight.BOLD, 38));
		lblDeck.setTextFill(Color.BLACK);
		pilePane = new StackPane();
		lblPile = new Label();
		lblPile.setFont(Font.font("IMPACT", FontWeight.BOLD, 38));
		lblPile.setTextFill(Color.BLACK);
		deckView = new StackPane();
		// deckView.getChildren().add(frame);
		deckView.getChildren().add(deckPane);
		deckView.getChildren().add(lblDeck);
		deckView.setStyle(st);
		deckView.setPrefSize(KrigApp.cardW, KrigApp.cardH);
		pileView = new StackPane();
		pileView.getChildren().add(pilePane);
		pileView.getChildren().add(lblPile);
		pileView.setStyle(st);
		pileView.setPrefSize(KrigApp.cardW, KrigApp.cardH);
		Label lblName = new Label(person.getName());
		lblName.setFont(Font.font("IMPACT", FontWeight.BOLD, 38));
		lblName.setTextFill(Color.BLACK);
		hq.add(lblName, 0, 0);
		hq.add(deckView, 1, 0);
		hq.add(pileView, 2, 0);
		GridPane.setHalignment(lblName, HPos.CENTER);
		GridPane.setHalignment(deckView, HPos.CENTER);
		GridPane.setHalignment(pileView, HPos.CENTER);
		GridPane.setMargin(deckView, new Insets(0, 50, 0, 0));
		double w = KrigApp.W - KrigApp.cardW;
		hq.setMinWidth(w);
		hq.getColumnConstraints().add(new ColumnConstraints(w / 3 + 33));
	}

	public PlayCard drawSingle() {
		PlayCard k = null;
		if (deckSize > 0) {
			k = (PlayCard) deckPane.getChildren().get(0);
			deckPane.getChildren().remove(0);
			deckSize--;
			deckLabel();
		} else if (pileSize > 0) {
			ArrayList<PlayCard> cards = new ArrayList<>();
			for (Node c : pilePane.getChildren()) {
				if (c instanceof PlayCard) {
					cards.add((PlayCard) c);
				}
			}
			Collections.shuffle(cards);
			deckSize = pileSize;
			pileSize = 0;
			deckLabel();
			pileLabel();
			deckPane.getChildren().addAll(cards);
			pilePane.getChildren().clear();
			return drawSingle();
		}
		return k;
	}

	public ArrayList<PlayCard> drawTripple() {
		ArrayList<PlayCard> cards = new ArrayList<>();
		for (int i = 0; i <= 2; i++) {
			cards.add(drawSingle());
		}
		return cards;
	}

	public void addCardToDeck(PlayCard k) {
		deckPane.getChildren().add(k);
		deckSize++;
		deckLabel();
	}

	public void addCardsToPile(ArrayList<PlayCard> cards) {
		pileSize += cards.size();
		pilePane.getChildren().addAll(cards);
		deckLabel();
		pileLabel();
	}

	public Person getPerson() {
		return person;
	}

	private void deckLabel() {
		lblDeck.setText("Deck\n" + deckSize);
	}

	private void pileLabel() {
		lblPile.setText("Pile\n" + pileSize);
	}
}