package it.marcodemartino.cah.client.ui.elements;

import it.marcodemartino.cah.client.game.Game;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WhiteCardElement extends HBox {

    public WhiteCardElement(WhiteCard whiteCard, GameManager gameManager) {
        setPadding(new Insets(20));

        setOnMouseClicked(e -> {
            Game game = gameManager.getGame();
            if (game.isCardSelected(whiteCard)) {
                setStyle("-fx-background-color: #aca2a2;");
                game.unselectCard(whiteCard);
                return;
            }

            if (game.areEnoughCardsSelected()) return;

            setStyle("-fx-background-color: #d0be72;");
            game.selectCard(whiteCard);

        });
        setStyle("-fx-background-color: #aca2a2;");

        Label cardText = new Label(whiteCard.getText());
        cardText.setWrapText(true);
        getChildren().add(cardText);
    }
}
