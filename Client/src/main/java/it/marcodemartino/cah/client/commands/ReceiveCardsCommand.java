package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiveCardsCommand extends Command {

    private static final Logger logger = LogManager.getLogger();
    private final GameManager gameManager;
    private final SceneController sceneController;
    private boolean firstRound;

    public ReceiveCardsCommand(PrintWriter out, GameManager gameManager, SceneController sceneController) {
        super(out);
        this.gameManager = gameManager;
        this.sceneController = sceneController;
        this.firstRound = true;
    }

    @Override
    public void execute(String input) {
        logger.info("Cards received");
        JSONObject jsonObject = new JSONObject(input);
        List<WhiteCard> whiteCards = Collections.emptyList();
        if (jsonObject.has("whitecards")) {
            JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("whitecards"));
            whiteCards = buildWhiteCards(jsonArray);
        }

        List<BlackCard> blackCards = Collections.emptyList();

        if (jsonObject.has("blackcards")) {
            JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("blackcards"));
            blackCards = buildBlackCards(jsonArray);
        }

        gameManager.getGame().addWhiteCards(whiteCards);
        if (!blackCards.isEmpty()) {
            gameManager.getGame().setNewBlackCard(blackCards.get(0));
        }

        if (firstRound) {
            Platform.runLater(() -> sceneController.activate("play_cards"));
            firstRound = false;
        }

    }

    private List<WhiteCard> buildWhiteCards(JSONArray jsonArray) {
        List<WhiteCard> whiteCards = new ArrayList<>();
        for (Object obj : jsonArray) {
            String text = (String) obj;
            whiteCards.add(new WhiteCard(text));
        }
        return whiteCards;
    }

    private List<BlackCard> buildBlackCards(JSONArray jsonArray) {
        List<BlackCard> blackCards = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            String text = jsonObject.getString("text");
            int parameters = jsonObject.getInt("parameters");
            blackCards.add(new BlackCard(text, parameters));
        }
        return blackCards;
    }
}
