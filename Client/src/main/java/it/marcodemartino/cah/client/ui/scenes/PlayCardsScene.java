package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.PlayCardsAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BigWhiteButton;
import it.marcodemartino.cah.client.ui.elements.BlackCardElement;
import it.marcodemartino.cah.client.ui.elements.WhiteCardElement;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PlayCardsScene extends InitPane {

    private final GameManager gameManager;
    private final Invoker invoker;
    private final List<WhiteCardElement> whiteCardElements;
    private boolean alreadyPlayed;

    public PlayCardsScene(GameManager gameManager, Invoker invoker, Stage primaryStage) {
        super(primaryStage);
        this.gameManager = gameManager;
        this.invoker = invoker;
        this.alreadyPlayed = false;
        whiteCardElements = new ArrayList<>();
    }

    @Override
    public void init() {
        getChildren().clear();
        whiteCardElements.clear();

        setId("background");
        alreadyPlayed = false;

        VBox mainContainer = new VBox();
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(20));

        BlackCard blackCard = gameManager.getGame().getNewBlackCard();
        BlackCardElement blackCardElement = new BlackCardElement(blackCard.getText(), widthProperty());

        String pluralS = blackCard.getNumberOfParameters() > 1 ? "s" : "";
        Label label = new Label(String.format("Choose %d card%s to play", blackCard.getNumberOfParameters(), pluralS));

        mainContainer.setSpacing(20);

        Button playCardsButton = new BigWhiteButton("Play cards", widthProperty(), heightProperty());
        playCardsButton.setOnAction(e -> handlePlayCardsButtonClick());

        HBox playCardsButtonWrapper = new HBox(playCardsButton);
        playCardsButtonWrapper.setAlignment(Pos.CENTER);

        HBox cardsContainer = new HBox(blackCardElement, createWhiteCardsElements());
        cardsContainer.setSpacing(20);
        cardsContainer.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(createSpacer(), label, cardsContainer, playCardsButtonWrapper, createSpacer());

        getChildren().add(mainContainer);
    }

    private void handlePlayCardsButtonClick() {
        if (alreadyPlayed) {
            showPopup("You already played in this round!");
            return;
        }

        if (!gameManager.getGame().areEnoughCardsSelected()) {
            showPopup("Can you count? You pig");
            return;
        }

        alreadyPlayed = true;
        changeColorOfPlayedCards();
        PlayCardsAction playCardsAction = new PlayCardsAction(gameManager.getGame().getSelectedCards(), gameManager);
        invoker.execute(playCardsAction);
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
            WhiteCardElement whiteCardElement1 = new WhiteCardElement(whiteCards.get(i), gameManager, widthProperty(), false);
            cardsContainer1.getChildren().add(whiteCardElement1);
            whiteCardElements.add(whiteCardElement1);

            WhiteCardElement whiteCardElement2 = new WhiteCardElement(whiteCards.get(whiteCards.size() - 1 - i), gameManager, widthProperty(), false);
            cardsContainer2.getChildren().add(whiteCardElement2);
            whiteCardElements.add(whiteCardElement2);
        }

        vBox.setSpacing(20);
        vBox.getChildren().addAll(cardsContainer1, cardsContainer2);
        return vBox;
    }

    private void changeColorOfPlayedCards() {
        for (WhiteCardElement whiteCardElement : whiteCardElements) {
            if (!whiteCardElement.isSelected()) continue;
            whiteCardElement.setPlayedColorClass();
        }
    }
}
