package it.marcodemartino.cah.json.server;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

import java.util.UUID;

public class NotifyPlayerJoinObject implements JSONObject {

    @SerializedName("method")
    private final String METHOD = "notify_player_join";
    @SerializedName("player_uuid")
    private final UUID playerUUID;
    @SerializedName("game_uuid")
    private final String playerName;

    public NotifyPlayerJoinObject(UUID playerUUID, String playerName) {
        this.playerUUID = playerUUID;
        this.playerName = playerName;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
