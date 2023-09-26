package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.PlayCardsAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BlackCardElement;
import it.marcodemartino.cah.client.ui.elements.WhiteCardElement;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class PlayCardsScene extends InitPane {

    private final GameManager gameManager;
    private final Invoker invoker;
    private boolean alreadyPlayed;

    public PlayCardsScene(GameManager gameManager, Invoker invoker) {
        this.gameManager = gameManager;
        this.invoker = invoker;
        this.alreadyPlayed = false;
    }

    @Override
    public void init() {
        getChildren().clear();
        alreadyPlayed = false;
        VBox mainContainer = new VBox();
        BlackCard blackCard = gameManager.getGame().getNewBlackCard();
        BlackCardElement blackCardElement = new BlackCardElement(blackCard.getText());

        String pluralS = blackCard.getNumberOfParameters() > 1 ? "s" : "";
        Label label = new Label(String.format("Choose %d card%s to play", blackCard.getNumberOfParameters(), pluralS));

        mainContainer.setSpacing(20);
        mainContainer.getChildren().addAll(blackCardElement, label, createWhiteCardsElements());
        getChildren().add(mainContainer);
    }

    private VBox createWhiteCardsElements() {
        VBox vBox = new VBox();
        List<WhiteCard> whiteCards = gameManager.getGame().getWhiteCards();
        int halfSize = whiteCards.size() / 2;

        HBox cardsContainer1 = new HBox();
        HBox cardsContainer2 = new HBox();
        cardsContainer1.setSpacing(20);
        cardsContainer2.setSpacing(20);
        for (int i = 0; i < halfSize; i++) {
            WhiteCardElement whiteCardElement1 = new WhiteCardElement(whiteCards.get(i), gameManager);
            whiteCardElement1.prefWidthProperty().bind(widthProperty().divide(6));
            whiteCardElement1.prefHeightProperty().bind(whiteCardElement1.prefWidthProperty().multiply(1.5));
            cardsContainer1.getChildren().add(whiteCardElement1);

            WhiteCardElement whiteCardElement2 = new WhiteCardElement(whiteCards.get(whiteCards.size() - 1 - i), gameManager);
            whiteCardElement2.prefWidthProperty().bind(widthProperty().divide(6));
            whiteCardElement2.prefHeightProperty().bind(whiteCardElement2.prefWidthProperty().multiply(1.5));
            cardsContainer2.getChildren().add(whiteCardElement2);
        }

        Button playCardsButton = new Button("Play cards");
        playCardsButton.setOnAction(e -> {
            if (alreadyPlayed) {
                showAlert("You already played in this round!");
                return;
            }

            if (!gameManager.getGame().areEnoughCardsSelected()) {
                showAlert("Can you count? You pig");
                return;
            }

            alreadyPlayed = true;
            PlayCardsAction playCardsAction = new PlayCardsAction(gameManager.getGame().getSelectedCards(), gameManager);
            invoker.execute(playCardsAction);
        });

        vBox.setSpacing(20);
        vBox.getChildren().addAll(cardsContainer1, cardsContainer2, playCardsButton);
        return vBox;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.show();
    }
}
