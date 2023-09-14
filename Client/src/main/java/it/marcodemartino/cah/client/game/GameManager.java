package it.marcodemartino.cah.client.game;

import java.util.UUID;

public class GameManager {

    private Game game;

    public void createGameWithUUID(UUID uuid) {
        game = new Game(uuid);
    }

    public Game getGame() {
        return game;
    }
}
