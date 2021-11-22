package gui;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.Card;
/**
 * @author Kristian Lindbjerg
 */
public class PlayCard extends StackPane {

	private Card card;
	private boolean isOpen;
	private ImageView open = new ImageView();
	private ImageView closed = new ImageView();
	private Text text;
	private Player playerGUI;

	public PlayCard(Card c) {
		card = c;
		isOpen = false;
		text = new Text();
		text.setText(c.toString());
		open.setImage(new Image("/cardImages/Cards/" + text.getText() + ".png/"));
		open.setVisible(false);
		open.setFitHeight(KrigApp.cardH);
		open.setPreserveRatio(true);
		closed.setImage(new Image("/cardImages/backred.jpg/"));
		closed.setFitHeight(KrigApp.cardH - 5);
		closed.setPreserveRatio(true);
		getChildren().addAll(text, closed, open);
	}

	public void setSpiller(Player playerGUI) {
		this.playerGUI = playerGUI;
	}

	public Player getSpiller() {
		return playerGUI;
	}

	public Card getCard() {
		return card;
	}

	public ImageView getClosed() {
		return closed;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
		flip();
	}

	public void flip() {
		if (isOpen) {
			open.setVisible(true);
		} else {
			open.setVisible(false);
		}
	}
}
