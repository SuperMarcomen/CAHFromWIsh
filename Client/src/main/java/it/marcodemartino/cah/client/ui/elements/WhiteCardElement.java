package it.marcodemartino.cah.client.ui.elements;

import it.marcodemartino.cah.client.game.Game;
import it.marcodemartino.cah.client.game.GameManager;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WhiteCardElement extends HBox {

    private boolean selected;

    public WhiteCardElement(String whiteCard, GameManager gameManager, ReadOnlyDoubleProperty widthProperty, boolean disabled) {
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

        String text = whiteCard.replace("\\n", System.lineSeparator());
        Label cardText = new Label(text);
        cardText.setWrapText(true);
        getChildren().add(cardText);

        setId("whiteCard");
        getStyleClass().add("whiteCardDisabled");
        prefWidthProperty().bind(widthProperty.divide(7));
        prefHeightProperty().bind(prefWidthProperty().multiply(1.3));
    }

    public void setUnPlayedClass() {
        removeClasses();
        getStyleClass().add("whiteCardDisabled");
        getStyleClass().add("whiteCardUnplayed");
    }

    public void setPlayedClass() {
        removeClasses();
        getStyleClass().add("whiteCardPlayed");
    }

    private void removeClasses() {
        getStyleClass().remove("whiteCardDisabled");
        getStyleClass().remove("whiteCardActivated");
    }

    public boolean isSelected() {
        return selected;
    }
}
