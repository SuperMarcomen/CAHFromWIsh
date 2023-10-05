package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.json.server.SendCardsObject;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ReceiveCardsCommand extends Command<SendCardsObject> {

    private static final Logger logger = LogManager.getLogger(ReceiveCardsCommand.class);
    private final GameManager gameManager;
    private final SceneController sceneController;
    private boolean firstRound;

    public ReceiveCardsCommand(GameManager gameManager, SceneController sceneController) {
        super(SendCardsObject.class);
        this.gameManager = gameManager;
        this.sceneController = sceneController;
        this.firstRound = true;
    }

    @Override
    public void execute(SendCardsObject object) {
        logger.info("Cards received");

        List<String> whiteCards = object.getWhiteCards();
        BlackCard blackCard = object.getBlackcard();

        gameManager.getGame().clearPlayedCards();
        gameManager.getGame().addWhiteCards(whiteCards);

        gameManager.getGame().setNewBlackCard(blackCard);

        if (firstRound) {
            Platform.runLater(() -> sceneController.activate("play_cards"));
            firstRound = false;
        }

    }
}
