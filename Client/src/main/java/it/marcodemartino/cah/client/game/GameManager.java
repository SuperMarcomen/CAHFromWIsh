package it.marcodemartino.cah.client.game;

import it.marcodemartino.cah.game.Player;

import java.util.UUID;

public class GameManager {

    private Game game;
    private Player player;

    public void createGameWithUUID(UUID uuid) {
        game = new Game(uuid);
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public void createDummyPlayer(String name) {
        player = new DummyPlayer(UUID.randomUUID(), name);
    }
}
