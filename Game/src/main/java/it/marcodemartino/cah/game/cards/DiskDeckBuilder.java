package it.marcodemartino.cah.game.cards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DiskDeckBuilder implements DeckBuilder {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Deck build(Path path) {
        try {
            String content = new String(Files.readAllBytes(path));
            JSONObject jsonObject = new JSONObject(content);

            List<WhiteCard> whiteCards = parseWhiteCards(jsonObject);
            List<BlackCard> blackCards = parseBlackCards(jsonObject);

            return new Deck(whiteCards, blackCards);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Deck(Collections.emptyList(), Collections.emptyList());
    }

    private List<WhiteCard> parseWhiteCards(JSONObject jsonObject) {
        logger.info("Parsing white cards from disk");
        List<WhiteCard> whiteCards = new LinkedList<>();
        JSONArray whiteArray = jsonObject.getJSONArray("white");
        for (int i = 0; i < whiteArray.length(); i++) {
            whiteCards.add(new WhiteCard(whiteArray.getString(i)));
        }
        return whiteCards;
    }

    private List<BlackCard> parseBlackCards(JSONObject jsonObject) {
        logger.info("Parsing black cards from disk");
        List<BlackCard> blackCards = new LinkedList<>();
        JSONArray blackArray = jsonObject.getJSONArray("black");
        for (int i = 0; i < blackArray.length(); i++) {
            JSONObject blackObject = blackArray.getJSONObject(i);
            blackCards.add(new BlackCard(blackObject.getString("text") ,blackObject.getInt("pick")));
        }
        return blackCards;
    }
}
