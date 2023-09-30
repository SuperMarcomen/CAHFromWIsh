package it.marcodemartino.cah.game.cards;

import it.marcodemartino.cah.game.collections.RandomArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DiskDeckBuilder implements DeckBuilder {

    private static final Logger logger = LogManager.getLogger(DiskDeckBuilder.class);

    @Override
    public Deck build(Path path) {
        try {
            String content = new String(Files.readAllBytes(path));
            JSONObject jsonObject = new JSONObject(content);

            RandomArrayList<WhiteCard> whiteCards = parseWhiteCards(jsonObject);
            RandomArrayList<BlackCard> blackCards = parseBlackCards(jsonObject);

            return new Deck(whiteCards, blackCards);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Deck(new RandomArrayList<>(), new RandomArrayList<>());
    }

    private RandomArrayList<WhiteCard> parseWhiteCards(JSONObject jsonObject) {
        logger.info("Parsing white cards from disk");
        RandomArrayList<WhiteCard> whiteCards = new RandomArrayList<>();
        JSONArray whiteArray = jsonObject.getJSONArray("white");
        for (int i = 0; i < whiteArray.length(); i++) {
            whiteCards.add(new WhiteCard(whiteArray.getString(i)));
        }
        return whiteCards;
    }

    private RandomArrayList<BlackCard> parseBlackCards(JSONObject jsonObject) {
        logger.info("Parsing black cards from disk");
        RandomArrayList<BlackCard> blackCards = new RandomArrayList<>();
        JSONArray blackArray = jsonObject.getJSONArray("black");
        for (int i = 0; i < blackArray.length(); i++) {
            JSONObject blackObject = blackArray.getJSONObject(i);
            blackCards.add(new BlackCard(blackObject.getString("text") ,blackObject.getInt("pick")));
        }
        return blackCards;
    }
}
