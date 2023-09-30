package it.marcodemartino.cah.server;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.game.cards.DeckBuilder;
import it.marcodemartino.cah.game.cards.DiskDeckBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class GameManager {

    private final Map<UUID, Game> games;
    private final DeckBuilder deckBuilder;
    private final Logger logger = LogManager.getLogger(GameManager.class);

    public GameManager() {
        games = new HashMap<>();
        deckBuilder = new DiskDeckBuilder();
    }

    public Game getGame(UUID uuid) {
        return games.get(uuid);
    }

    public UUID createGame() {
        Game game = new Game(deckBuilder.build(getPathFromClassPath("test.txt")));
        UUID uuid = UUID.randomUUID();
        games.put(uuid, game);
        return uuid;
    }

    public void quitPlayer(UUID playerUUID) {
        for (Entry<UUID, Game> uuidGameEntry : games.entrySet()) {
            Game game = uuidGameEntry.getValue();
            UUID gameUUID = uuidGameEntry.getKey();
            game.removePlayer(playerUUID);
            if (game.isEmpty()) {
                games.remove(gameUUID);
                logger.info("Game with UUID {} was deleted since it was empty", gameUUID);
            }
        }
    }

    private Path getPathFromClassPath(String fileName) {
        try {
            return Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
