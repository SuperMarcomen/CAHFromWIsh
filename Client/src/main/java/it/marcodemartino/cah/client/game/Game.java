package it.marcodemartino.cah.client.game;

import java.util.UUID;

public class Game {

    private final UUID uuid;

    public Game(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
