package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.cards.BlackCard;
import it.marcodemartino.cah.game.cards.WhiteCard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiveCardsCommand extends Command {

    private final GameManager gameManager;

    public ReceiveCardsCommand(PrintWriter out, GameManager gameManager) {
        super(out);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(String input) {
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
            gameManager.getGame().setBlackCard(blackCards.get(0));
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
