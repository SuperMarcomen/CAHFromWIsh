package it.marcodemartino.cah.json.client;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

import java.util.List;
import java.util.UUID;

public class PlayCardsObject implements JSONObject {

    @SerializedName("method")
    private static final String METHOD = "play_cards";
    @SerializedName("played_cards")
    private final List<String> playedCards;
    @SerializedName("player_uuid")
    private final UUID playerUUID;
    @SerializedName("game_uuid")
    private final UUID gameUUID;

    public PlayCardsObject(List<String> playedCards, UUID playerUUID, UUID gameUUID) {
        this.playedCards = playedCards;
        this.playerUUID = playerUUID;
        this.gameUUID = gameUUID;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public List<String> getPlayedCards() {
        return playedCards;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }
}
