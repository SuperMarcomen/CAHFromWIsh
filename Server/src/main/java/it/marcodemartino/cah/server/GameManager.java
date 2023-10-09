package it.marcodemartino.cah.server;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.game.cards.Deck;
import it.marcodemartino.cah.game.cards.DiskDeckBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class GameManager {

    private final Map<UUID, Game> games;
    private final DeckManager deckManager;
    private final Logger logger = LogManager.getLogger(GameManager.class);

    public GameManager() {
        games = new HashMap<>();
        deckManager = new DeckManager(new DiskDeckBuilder());
    }

    public Game getGame(UUID uuid) {
        return games.get(uuid);
    }

    public UUID createGame() {
        UUID uuid = UUID.randomUUID();
        Game game = new Game(uuid);
        games.put(uuid, game);
        return uuid;
    }

    public void setDeck(Game game, List<String> decksNames) {
        Deck deck = deckManager.getDeckFromNames(decksNames);
        game.setDeck(deck);
    }

    public void quitPlayer(UUID playerUUID) {
        List<UUID> toBeRemoved = new ArrayList<>();
        for (Entry<UUID, Game> uuidGameEntry : games.entrySet()) {
            Game game = uuidGameEntry.getValue();
            UUID gameUUID = uuidGameEntry.getKey();
            game.removePlayer(playerUUID);
            if (game.isEmpty()) {
                toBeRemoved.add(gameUUID);
                logger.info("Game with UUID {} was deleted since it was empty", gameUUID);
            }
        }
        for (UUID uuid : toBeRemoved) {
            games.remove(uuid);
        }
    }

    public DeckManager getDeckManager() {
        return deckManager;
    }
}
