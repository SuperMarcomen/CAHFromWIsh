package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BlackCardElement;
import it.marcodemartino.cah.client.ui.elements.WhiteCardElement;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
        BlackCard blackCard = gameManager.getGame().getBlackCard();
        BlackCardElement blackCardElement = new BlackCardElement(blackCard.getText());

        HBox playersResults = new HBox();
        for (Entry<String, List<WhiteCard>> stringListEntry : gameManager.getGame().getAllPlayedCards().entrySet()) {
            VBox playerContainer = new VBox();
            String playerName = stringListEntry.getKey();
            List<WhiteCard> whiteCards = stringListEntry.getValue();

            Label playerPlayed = new Label(playerName + " played:");
            playerContainer.getChildren().add(playerPlayed);
            for (WhiteCard whiteCard : whiteCards) {
                WhiteCardElement whiteCardElement = new WhiteCardElement(whiteCard, gameManager);
                playerContainer.getChildren().add(whiteCardElement);
            }
            playersResults.getChildren().add(playerContainer);
        }

        Button continuePlaying = new Button("Continue playing");
        continuePlaying.setOnMouseClicked(e -> sceneController.activate("play_cards"));
        mainContainer.getChildren().addAll(blackCardElement, playersResults, continuePlaying);
        getChildren().add(mainContainer);

    }
}
