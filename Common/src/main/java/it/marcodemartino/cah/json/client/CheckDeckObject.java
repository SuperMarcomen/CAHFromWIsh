package it.marcodemartino.cah.json.client;

import it.marcodemartino.cah.json.JSONObject;

import java.util.UUID;

public class CheckDeckObject implements JSONObject {

    private final String method = "check_deck";
    private final String deckName;
    private final UUID playerUUID;
    private final UUID gameUUID;
    private final boolean selected;

    public CheckDeckObject(String deckName, UUID playerUUID, UUID gameUUID, boolean selected) {
        this.deckName = deckName;
        this.playerUUID = playerUUID;
        this.gameUUID = gameUUID;
        this.selected = selected;
    }

    @Override
    public String getMethod() {
        return method;
    }

    public String getDeckName() {
        return deckName;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    public boolean isSelected() {
        return selected;
    }
}
