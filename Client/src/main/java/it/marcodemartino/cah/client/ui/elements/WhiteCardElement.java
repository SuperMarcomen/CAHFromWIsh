package it.marcodemartino.cah.client.ui.elements;

import it.marcodemartino.cah.client.game.Game;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WhiteCardElement extends HBox {

    private boolean selected;

    public WhiteCardElement(WhiteCard whiteCard, GameManager gameManager, ReadOnlyDoubleProperty widthProperty, boolean disabled) {
        setPadding(new Insets(20));
        selected = false;

        setOnMouseClicked(e -> {
            if (disabled) return;
            Game game = gameManager.getGame();
            if (game.hasPlayedCards()) return;

            if (game.isCardSelected(whiteCard)) {
                getStyleClass().remove("whiteCardActivated");
                getStyleClass().add("whiteCardDisabled");
                game.unselectCard(whiteCard);
                selected = false;
                return;
            }

            if (game.areEnoughCardsSelected()) return;

            getStyleClass().add("whiteCardActivated");
            getStyleClass().remove("whiteCardDisabled");
            selected = true;

            game.selectCard(whiteCard);

        });

        Label cardText = new Label(whiteCard.getText());
        cardText.setWrapText(true);
        getChildren().add(cardText);

        setId("whiteCard");
        getStyleClass().add("whiteCardDisabled");
        prefWidthProperty().bind(widthProperty.divide(7));
        prefHeightProperty().bind(prefWidthProperty().multiply(1.3));
    }

    public void setPlayedColorClass() {
        getStyleClass().remove("whiteCardDisabled");
        getStyleClass().remove("whiteCardActivated");
        getStyleClass().add("whiteCardPlayed");
    }

    public boolean isSelected() {
        return selected;
    }
}
