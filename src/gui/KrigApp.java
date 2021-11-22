package gui;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import model.Person;
import service.Service;
public class KrigApp extends Application {

	public static final int W = 800;
	public static final int H = 600;
	public static final int cardH = H / 4 - 10;
	public static final int cardW = 150;
	private static GridPane root;
	private GridPane actionPane, p1Pane, p2Pane, p1HQ, p2HQ;
	private StackPane pile;
	private HBox p1Cards, p2Cards;
	private ArrayList<PlayCard> kort = new ArrayList<>();
	private ArrayList<PlayCard> pot = new ArrayList<>();
	private static Player p1;
	private static Player p2;
	private static Player winner;
	private Button btnNew, btnDraw;
	private Scene scene;
	private Background table, war;
	private static PlayCard tripleChoiceOne = null, tripleChoiceTwo = null;
	private String st = "-fx-border-style: solid inside;" + "-fx-border-width: 1;" + "-fx-border-radius: 10;"
			+ "-fx-border-color: black;";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		scene = new Scene(createContent());
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Krig");
	}

	private Parent createContent() {
		// p1 Panes
		p1HQ = new GridPane();
		p1Cards = new HBox();
		p1Cards.setPrefHeight(cardH);
		p1Cards.setAlignment(Pos.CENTER);
		p1Cards.setSpacing(30);
		p1Pane = new GridPane();
		p1Pane.setPrefHeight(H / 2);
		// p1Pane.setGridLinesVisible(true);
		p1Pane.setStyle(st);
		p1Pane.add(p1HQ, 0, 0);
		p1Pane.add(p1Cards, 0, 1);
		p1Pane.setAlignment(Pos.CENTER);
		p1Pane.setVgap(10);
		GridPane.setHalignment(p1Pane, HPos.CENTER);
		// p2 Panes
		p2HQ = new GridPane();
		p2Cards = new HBox();
		p2Cards.setPrefHeight(cardH);
		p2Cards.setAlignment(Pos.CENTER);
		p2Cards.setSpacing(30);
		p2Pane = new GridPane();
		p2Pane.setStyle(st);
		p2Pane.setPrefHeight(H / 2);
		p2Pane.add(p2Cards, 0, 0);
		p2Pane.add(p2HQ, 0, 1);
		p2Pane.setVgap(10);
		// p2Pane.setGridLinesVisible(true);
		GridPane.setHalignment(p2HQ, HPos.CENTER);
		// action pane
		pile = new StackPane();
		btnNew = new Button("New Game");
		btnNew.setPrefSize(80, 50);
		btnNew.setOnAction(event -> startGame());
		btnDraw = new Button("Draw");
		btnDraw.setOnAction(event -> singleDraw());
		btnDraw.setPrefSize(80, 50);
		// btnDraw.setVisible(false);
		actionPane = new GridPane();
		actionPane.setPrefSize(cardW, H);
		actionPane.setVgap(50);
		actionPane.setAlignment(Pos.CENTER);
		actionPane.setStyle(st);
		actionPane.add(btnNew, 0, 0);
		actionPane.add(pile, 0, 1);
		actionPane.add(btnDraw, 0, 2);
		// root
		root = new GridPane();
		BackgroundImage tableImage = new BackgroundImage(new Image("/cardImages/table.jpg/", W, H, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		BackgroundImage warImage = new BackgroundImage(new Image("/cardImages/warBack.gif/", W, H, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		table = new Background(tableImage);
		war = new Background(warImage);
		root.setBackground(table);
		root.add(actionPane, 0, 0, 1, 2);
		root.add(p1Pane, 1, 0, 2, 1);
		root.add(p2Pane, 1, 1, 2, 1);
		root.setPrefSize(W, H);
		GridPane.setHalignment(btnDraw, HPos.CENTER);
		GridPane.setHalignment(btnNew, HPos.CENTER);
		Deck deck = Service.createFullDeck();
		for (Card c : deck.getDeck()) {
			PlayCard k = new PlayCard(c);
			kort.add(k);
		}
		pile.getChildren().addAll(kort);
		return root;
	}

	public void startGame() {
		// Alert alert = new Alert(AlertType.CONFIRMATION);
		// alert.setContentText("How many players?");
		// alert.initStyle(StageStyle.UNDECORATED);
		// ButtonType btnOne = new ButtonType("One");
		// ButtonType btnTwo = new ButtonType("Two");
		// ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		// alert.getButtonTypes().setAll(btnOne, btnTwo, btnCancel);
		// Optional<ButtonType> btnResult = alert.showAndWait();
		// if (btnResult.get() == btnCancel) {
		// return;
		// }
		// Dialog<String> dialogp1 = new TextInputDialog();
		// dialogp1.setTitle("Enter Name");
		// dialogp1.setHeaderText("Player 1");
		// Optional<String> resultp1 = dialogp1.showAndWait();
		// String p1Name = "";
		// if (resultp1.isPresent()) {
		// p1Name = resultp1.get();
		// if (p1Name.length() > 0) {
		// p1 = Service.createPlayer(Service.createPerson(p1Name), p1HQ);
		// }
		// }
		// if (btnResult.get() == btnTwo) {
		// Dialog<String> dialogp2 = new TextInputDialog();
		// dialogp2.setTitle("Enter Name");
		// dialogp2.setHeaderText("Player 2");
		// String p2Name = "";
		// Optional<String> resultp2 = dialogp2.showAndWait();
		// if (resultp2.isPresent()) {
		// p2Name = resultp2.get();
		// if (p2Name.length() > 0) {
		// p2 = Service.createPlayer(Service.createPerson(p2Name), p2HQ);
		// }
		// }
		// } else {
		// p2 = Service.createNPC(p2HQ);
		// }
		// if (p2 != null && p1 != null) {
		// }
		p1 = Service.createPlayer(new Person("Top"), p1HQ);
		p2 = Service.createPlayer(new Person(), p2HQ);
		deal(kort);
	}

	public void deal(ArrayList<PlayCard> kort) {
		btnDraw.setOnAction(event -> takeTurn());
		Collections.shuffle(kort);
		int i = 0;
		for (PlayCard k : kort) {
			if (i % 2 == 0) {
				p1.addCardToDeck(k);
				k.setSpiller(p1);
			} else {
				p2.addCardToDeck(k);
				k.setSpiller(p2);
			}
			i++;
		}
	}

	public void singleDraw() {
		PlayCard k1 = p1.drawSingle();
		PlayCard k2 = p2.drawSingle();
		pot.add(k1);
		pot.add(k2);
		k1.setOpen(true);
		k2.setOpen(true);
		p1Cards.getChildren().add(k1);
		p2Cards.getChildren().add(k2);
		compare(k1, k2);
		// FadeTransition ft = new FadeTransition(Duration.seconds(1), actionPane);
		// ft.setFromValue(0);
		// ft.setToValue(1);
		// ft.setCycleCount(1);
		// ft.setAutoReverse(true);
		// ft.play();
		// Path path = new Path();
		// path.getElements().add(new MoveTo(W / 2 - 20, 80));
		// path.getElements().add(new LineTo(50, -100));
		// path.setSmooth(true);
		// path.getElements().add(new MoveTo(p1Pane.getLayoutX(), p1Pane.getLayoutY()));
		// path.getElements().add(new CubicCurveTo(40f, 10f, 390f, 240f, 1904, 50f));
		// PathTransition pt1 = new PathTransition();
		// pt1.setDuration(Duration.seconds(3));
		// pt1.setPath(path);
		// pt1.setNode();
		// pt1.setOrientation(OrientationType.NONE);
		// pt1.setCycleCount(1);
		// pt1.play();
		// pt1.setOnFinished(event -> battleP1(c1, pt1));
		// p1Pane.add();
		// p2Pane.add(Service.battleSingle(p2), 1, 0);
	}

	private void choiceP1(PlayCard choice, ArrayList<PlayCard> cards) {
		choice.setOpen(true);
		tripleChoiceOne = choice;
		for (PlayCard c : cards) {
			c.setOnMouseClicked(null);
		}
		if (tripleChoiceTwo != null) {
			compare(tripleChoiceOne, tripleChoiceTwo);
			if (winner != null) {
				War w = new War();
				w.showAndWait();
				// p2HQ.setBackground(war);
				root.setBackground(table);
				btnDraw.setDisable(false);
				tripleChoiceOne = null;
				tripleChoiceTwo = null;
				takeTurn();
			} else {
				tripleDraw();
			}
		}
	}

	private void choiceP2(PlayCard choice, ArrayList<PlayCard> cards) {
		choice.setOpen(true);
		tripleChoiceTwo = choice;
		for (PlayCard c : cards) {
			c.setOnMouseClicked(null);
		}
		if (tripleChoiceOne != null) {
			compare(tripleChoiceOne, tripleChoiceTwo);
			if (winner != null) {
				War w = new War();
				w.showAndWait();
				root.setBackground(table);
				btnDraw.setDisable(false);
				takeTurn();
			} else {
				tripleDraw();
			}
			tripleChoiceOne = null;
			tripleChoiceTwo = null;
		}
	}

	private void tripleDraw() {
		ArrayList<PlayCard> cards1 = p1.drawTripple();
		ArrayList<PlayCard> cards2 = p2.drawTripple();
		pot.addAll(cards1);
		pot.addAll(cards2);
		p1Cards.getChildren().addAll(cards1);
		p2Cards.getChildren().addAll(cards2);
		for (PlayCard c : cards1) {
			c.setOnMouseClicked(event -> choiceP1(c, cards1));
		}
		for (PlayCard c : cards2) {
			c.setOnMouseClicked(event -> choiceP2(c, cards2));
		}
	}

	private void takeTurn() {
		if (winner != null) {
			for (PlayCard c : pot) {
				if (c.isOpen()) {
					c.setOpen(false);
				}
			}
			winner.addCardsToPile(pot);
			pot.clear();
			winner = null;
		} else {
			singleDraw();
			if (winner == null) {
				root.setBackground(war);
				btnDraw.setDisable(true);
				tripleDraw();
			}
		}
	}

	private static void compare(PlayCard k1, PlayCard k2) {
		int result = k1.getCard().compareTo(k2.getCard());
		if (result < 0) {
			winner = p2;
		} else if (result > 0) {
			winner = p1;
		}
	}

	public void restart() {
		createContent();
	}
	class War extends Stage {

		private Button btnOK;

		public War() {
			initModality(Modality.APPLICATION_MODAL);
			StackPane pane = new StackPane();
			Scene scene = new Scene(pane);
			setScene(scene);
			ImageView vic = new ImageView();
			vic.setImage(new Image("/cardImages/victory.gif/"));
			btnOK = new Button(winner.getPerson().getName() + " won!!");
			btnOK.setOnAction(event -> okAction());
			StackPane.setAlignment(btnOK, Pos.CENTER);
			pane.getChildren().addAll(vic, btnOK);
		}

		private void okAction() {
			close();
		}
	}
}
