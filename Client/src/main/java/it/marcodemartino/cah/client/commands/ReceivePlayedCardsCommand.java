package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceivePlayedCardsCommand extends Command {

    private static final Logger logger = LogManager.getLogger(ReceivePlayedCardsCommand.class);
    private final GameManager gameManager;
    private final SceneController sceneController;

    public ReceivePlayedCardsCommand(PrintWriter out, GameManager gameManager, SceneController sceneController) {
        super(out);
        this.gameManager = gameManager;
        this.sceneController = sceneController;
    }

    @Override
    public void execute(String input) {
        logger.info("Received the cards played from all players");
        JSONObject jsonObject = new JSONObject(input);
        JSONArray players = jsonObject.getJSONArray("cards");
        Map<String, List<WhiteCard>> cardsMap = new HashMap<>();
        for (Object playerBlock : players) {
            JSONObject obj = (JSONObject) playerBlock;
            String playerName = obj.getString("player_name");

            JSONArray cardsArray = obj.getJSONArray("played_cards");
            List<WhiteCard> whiteCards = new ArrayList<>();
            for (Object cardElement : cardsArray) {
                whiteCards.add(new WhiteCard((String) cardElement));
            }
            cardsMap.put(playerName, whiteCards);
        }
        gameManager.getGame().setAllPlayedCards(cardsMap);
        Platform.runLater(() -> sceneController.activate("show_all_cards"));
    }
}
