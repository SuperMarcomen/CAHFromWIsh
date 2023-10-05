package it.marcodemartino.cah.json.client;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

import java.util.UUID;

public class JoinGameObject implements JSONObject {

    @SerializedName("method")
    private static final String METHOD = "join_game";
    @SerializedName("game_uuid")
    private final UUID gameUUID;
    @SerializedName("player_uuid")
    private final UUID playerUUID;
    @SerializedName("player_name")
    private final String playerName;

    public JoinGameObject(UUID gameUUID, UUID playerUUID, String playerName) {
        this.gameUUID = gameUUID;
        this.playerUUID = playerUUID;
        this.playerName = playerName;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
