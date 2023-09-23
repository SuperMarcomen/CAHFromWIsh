package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.WhiteCardElement;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.scene.layout.HBox;

public class PlayCardsScene extends InitPane {

    private final GameManager gameManager;
    private final Invoker invoker;

    public PlayCardsScene(GameManager gameManager, Invoker invoker) {
        this.gameManager = gameManager;
        this.invoker = invoker;
    }

    @Override
    public void init() {
        HBox mainContainer = new HBox();
        for (WhiteCard whiteCard : gameManager.getGame().getWhiteCards()) {
            mainContainer.getChildren().add(new WhiteCardElement(whiteCard.getText()));
        }
        getChildren().add(mainContainer);
    }
}
