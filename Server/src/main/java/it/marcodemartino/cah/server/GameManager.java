package it.marcodemartino.cah.server;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.game.cards.DeckBuilder;
import it.marcodemartino.cah.game.cards.DiskDeckBuilder;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {

    private final Map<UUID, Game> games;
    private final DeckBuilder deckBuilder;

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

    private Path getPathFromClassPath(String fileName) {
        try {
            return Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
