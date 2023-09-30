package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.PlayCardsAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BlackCardElement;
import it.marcodemartino.cah.client.ui.elements.WhiteCardElement;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
        setId("background");
        alreadyPlayed = false;
        VBox mainContainer = new VBox();
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(20));

        BlackCard blackCard = gameManager.getGame().getNewBlackCard();
        BlackCardElement blackCardElement = new BlackCardElement(blackCard.getText());
        blackCardElement.setId("blackCard");
        blackCardElement.prefWidthProperty().bind(widthProperty().divide(7));
        blackCardElement.prefHeightProperty().bind(blackCardElement.prefWidthProperty().multiply(1.3));
        blackCardElement.setMaxWidth(Region.USE_PREF_SIZE);
        blackCardElement.setMinWidth(Region.USE_PREF_SIZE);
        blackCardElement.setMinHeight(Region.USE_PREF_SIZE);
        blackCardElement.setMaxHeight(Region.USE_PREF_SIZE);
        VBox blackCardWrapper = new VBox(blackCardElement);
        blackCardWrapper.setAlignment(Pos.CENTER);

        String pluralS = blackCard.getNumberOfParameters() > 1 ? "s" : "";
        Label label = new Label(String.format("Choose %d card%s to play", blackCard.getNumberOfParameters(), pluralS));

        mainContainer.setSpacing(20);

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
        playCardsButton.prefWidthProperty().bind(widthProperty().divide(3));
        playCardsButton.prefHeightProperty().bind(heightProperty().divide(10));
        HBox playCardsButtonWrapper = new HBox(playCardsButton);
        playCardsButtonWrapper.setAlignment(Pos.CENTER);

        HBox cardsContainer = new HBox(blackCardWrapper, createWhiteCardsElements());
        cardsContainer.setSpacing(20);
        cardsContainer.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(createSpacer(), label, cardsContainer, playCardsButtonWrapper, createSpacer());
        getChildren().add(mainContainer);
    }

    private VBox createWhiteCardsElements() {
        VBox vBox = new VBox();
        List<WhiteCard> whiteCards = gameManager.getGame().getWhiteCards();
        int halfSize = whiteCards.size() / 2;

        HBox cardsContainer1 = new HBox();
        HBox cardsContainer2 = new HBox();
        cardsContainer1.setSpacing(20);
        cardsContainer1.setAlignment(Pos.CENTER);
        cardsContainer2.setSpacing(20);
        cardsContainer2.setAlignment(Pos.CENTER);
        for (int i = 0; i < halfSize; i++) {
            WhiteCardElement whiteCardElement1 = new WhiteCardElement(whiteCards.get(i), gameManager, false);
            whiteCardElement1.setId("whiteCard");
            whiteCardElement1.getStyleClass().add("whiteCardDisabled");
            whiteCardElement1.prefWidthProperty().bind(widthProperty().divide(7));
            whiteCardElement1.prefHeightProperty().bind(whiteCardElement1.prefWidthProperty().multiply(1.3));
            cardsContainer1.getChildren().add(whiteCardElement1);

            WhiteCardElement whiteCardElement2 = new WhiteCardElement(whiteCards.get(whiteCards.size() - 1 - i), gameManager, false);
            whiteCardElement2.setId("whiteCard");
            whiteCardElement2.getStyleClass().add("whiteCardDisabled");
            whiteCardElement2.prefWidthProperty().bind(widthProperty().divide(7));
            whiteCardElement2.prefHeightProperty().bind(whiteCardElement2.prefWidthProperty().multiply(1.3));
            cardsContainer2.getChildren().add(whiteCardElement2);
        }

        vBox.setSpacing(20);
        vBox.getChildren().addAll(cardsContainer1, cardsContainer2);
        return vBox;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.show();
    }

    private Node createSpacer() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
}
