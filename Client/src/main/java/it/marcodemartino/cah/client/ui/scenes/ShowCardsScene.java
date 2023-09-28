package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BlackCardElement;
import it.marcodemartino.cah.client.ui.elements.WhiteCardElement;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map.Entry;

public class ShowCardsScene extends InitPane {

    private final GameManager gameManager;
    private final SceneController sceneController;

    public ShowCardsScene(GameManager gameManager, SceneController sceneController) {
        this.gameManager = gameManager;
        this.sceneController = sceneController;
    }

    @Override
    public void init() {
        VBox mainContainer = new VBox();
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(20));
        setId("background");

        BlackCard blackCard = gameManager.getGame().getOldBlackCard();
        BlackCardElement blackCardElement = new BlackCardElement(blackCard.getText());
        blackCardElement.setId("blackCard");
        blackCardElement.prefWidthProperty().bind(widthProperty().divide(7));
        blackCardElement.prefHeightProperty().bind(blackCardElement.prefWidthProperty().multiply(1.3));
        blackCardElement.setMaxWidth(Region.USE_PREF_SIZE);
        blackCardElement.setMinHeight(Region.USE_PREF_SIZE);
        HBox blackCardWrapper = new HBox(blackCardElement);
        blackCardWrapper.setAlignment(Pos.CENTER);

        HBox playersResults = new HBox();
        playersResults.setAlignment(Pos.CENTER);
        playersResults.setSpacing(20);
        for (Entry<String, List<WhiteCard>> stringListEntry : gameManager.getGame().getAllPlayedCards().entrySet()) {
            VBox playerContainer = new VBox();
            String playerName = stringListEntry.getKey();
            List<WhiteCard> whiteCards = stringListEntry.getValue();

            Label playerPlayed = new Label(playerName + " played:");
            playerContainer.getChildren().add(playerPlayed);
            for (WhiteCard whiteCard : whiteCards) {
                WhiteCardElement whiteCardElement = new WhiteCardElement(whiteCard, gameManager, true);
                whiteCardElement.setId("whiteCard");
                whiteCardElement.getStyleClass().add("whiteCardDisabled");
                whiteCardElement.prefWidthProperty().bind(widthProperty().divide(7));
                whiteCardElement.prefHeightProperty().bind(whiteCardElement.prefWidthProperty().multiply(1.3));

                playerContainer.getChildren().add(whiteCardElement);
            }
            playersResults.getChildren().add(playerContainer);
        }

        Button continuePlaying = new Button("Continue playing");
        continuePlaying.setOnMouseClicked(e -> sceneController.activate("play_cards"));
        continuePlaying.prefWidthProperty().bind(widthProperty().divide(3));
        continuePlaying.prefHeightProperty().bind(heightProperty().divide(10));
        HBox continuePlayingWrapper = new HBox(continuePlaying);
        continuePlayingWrapper.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(blackCardWrapper, playersResults, continuePlayingWrapper);
        getChildren().add(mainContainer);

    }
}
