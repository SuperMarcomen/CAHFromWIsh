package it.marcodemartino.cah.client.ui.elements;

import it.marcodemartino.cah.client.game.Game;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WhiteCardElement extends HBox {

    public WhiteCardElement(WhiteCard whiteCard, GameManager gameManager, boolean disabled) {
        setPadding(new Insets(20));

        setOnMouseClicked(e -> {
            if (disabled) return;
            Game game = gameManager.getGame();
            if (game.isCardSelected(whiteCard)) {
                getStyleClass().remove("whiteCardActivated");
                getStyleClass().add("whiteCardDisabled");
                game.unselectCard(whiteCard);
                return;
            }

            if (game.areEnoughCardsSelected()) return;

            getStyleClass().add("whiteCardActivated");
            getStyleClass().remove("whiteCardDisabled");

            game.selectCard(whiteCard);

        });

        Label cardText = new Label(whiteCard.getText());
        cardText.setWrapText(true);
        getChildren().add(cardText);
    }
}
