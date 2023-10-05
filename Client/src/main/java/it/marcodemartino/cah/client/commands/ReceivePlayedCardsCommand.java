package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.json.server.SendAllCardsObject;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import java.util.List;
import java.util.Map;

public class ReceivePlayedCardsCommand extends Command<SendAllCardsObject> {

    private static final Logger logger = LogManager.getLogger(ReceivePlayedCardsCommand.class);
    private final GameManager gameManager;
    private final SceneController sceneController;

    public ReceivePlayedCardsCommand(GameManager gameManager, SceneController sceneController) {
        super(SendAllCardsObject.class);
        this.gameManager = gameManager;
        this.sceneController = sceneController;
    }

    @Override
    public void execute(SendAllCardsObject object) {
        logger.info("Received the cards played from all players");
        Map<String, List<String>> cardsMap = object.getPlayedCards();

        gameManager.getGame().setAllPlayedCards(cardsMap);
        Platform.runLater(() -> sceneController.activate("show_all_cards"));
    }
}
