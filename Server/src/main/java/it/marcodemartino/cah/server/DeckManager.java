package it.marcodemartino.cah.server;

import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.DeckBuilder;
import it.marcodemartino.cah.json.server.DeckInfoObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeckManager {

    private final Map<String, Deck> decks;
    private final Logger logger = LogManager.getLogger(DeckManager.class);

    public DeckManager(DeckBuilder deckBuilder) {
        Path path = Paths.get("cah-cards-full.json");
        this.decks = deckBuilder.build(path);
    }

    public List<DeckInfoObject> createDeckInfos() {
        List<DeckInfoObject> deckInfoObjectList = new ArrayList<>();
        for (Deck deck : decks.values()) {
            DeckInfoObject deckInfoObject = new DeckInfoObject(deck.getName(), deck.getWhiteCardsSize(), deck.getBlackCardsSize(), deck.isOfficial());
            deckInfoObjectList.add(deckInfoObject);
        }
        return deckInfoObjectList;
    }

    public Deck getDeckFromNames(List<String> deckNames) {
        Deck deck = new Deck();
        for (String deckName : deckNames) {
            Deck toBeAdded = decks.get(deckName);
            if (toBeAdded == null) {
                logger.warn("Received request to add deck {} to a custom deck, but it was not found!", deckName);
                continue;
            }

            deck.addAllWhiteCards(toBeAdded.getWhiteCards());
            deck.addAllBlackCards(toBeAdded.getBlackCards());
        }
        return deck;
    }
}
