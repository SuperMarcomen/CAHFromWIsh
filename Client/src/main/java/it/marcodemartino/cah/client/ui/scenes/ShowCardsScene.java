package it.marcodemartino.cah.client.ui.scenes;

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

import java.util.List;
import java.util.Map.Entry;

public class ShowCardsScene extends InitPane {

    private final GameManager gameManager;
    private final SceneController sceneController;

    public ShowCardsScene(GameManager gameManager, SceneController sceneController, Stage primaryStage) {
        super(primaryStage);
        this.gameManager = gameManager;
        this.sceneController = sceneController;
    }

    @Override
    public void init() {
        getChildren().clear();

        VBox mainContainer = new VBox();
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(20));
        setId("background");

        BlackCard blackCard = gameManager.getGame().getOldBlackCard();
        BlackCardElement blackCardElement = new BlackCardElement(blackCard.getText(), widthProperty());
        HBox blackCardWrapper = new HBox(blackCardElement);
        blackCardWrapper.setAlignment(Pos.CENTER);

        HBox playersResults = createPlayerResults();

        Button continuePlaying = new BigWhiteButton("Continue playing", widthProperty(), heightProperty());
        continuePlaying.setOnMouseClicked(e -> sceneController.activate("play_cards"));
        HBox continuePlayingWrapper = new HBox(continuePlaying);
        continuePlayingWrapper.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(blackCardWrapper, playersResults, continuePlayingWrapper);
        getChildren().add(mainContainer);

    }

    private HBox createPlayerResults() {
        HBox playersResults = new HBox();
        playersResults.setAlignment(Pos.CENTER);
        playersResults.setSpacing(20);

        for (Entry<String, List<WhiteCard>> playedCardEntry : gameManager.getGame().getAllPlayedCards().entrySet()) {
            VBox playerContainer = createPlayerContainer(playedCardEntry);
            playersResults.getChildren().add(playerContainer);
        }
        return playersResults;
    }

    private VBox createPlayerContainer(Entry<String, List<WhiteCard>> stringListEntry) {
        VBox playerContainer = new VBox();
        String playerName = stringListEntry.getKey();
        List<WhiteCard> whiteCards = stringListEntry.getValue();

        Label playerPlayed = new Label(playerName + " played:");
        playerContainer.getChildren().add(playerPlayed);
        for (WhiteCard whiteCard : whiteCards) {
            WhiteCardElement whiteCardElement = new WhiteCardElement(whiteCard, gameManager, widthProperty(), true);
            playerContainer.getChildren().add(whiteCardElement);
        }
        return playerContainer;
    }
}
