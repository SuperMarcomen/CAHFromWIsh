package it.marcodemartino.cah.json.client;

import it.marcodemartino.cah.json.JSONObject;

import java.util.UUID;

public class ChooseDecksObject implements JSONObject {

    private final String method = "choose_decks";
    private final UUID gameUUID;

    public ChooseDecksObject(UUID gameUUID) {
        this.gameUUID = gameUUID;
    }

    @Override
    public String getMethod() {
        return method;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }
}
