package it.marcodemartino.cah.json.server;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

import java.util.UUID;

public class NotifyPlayerPlayedObject implements JSONObject {

    @SerializedName("method")
    private final String METHOD = "notify_player_played";
    @SerializedName("player_uuid")
    private final UUID playerUUID;
    @SerializedName("player_name")
    private final String playerName;

    public NotifyPlayerPlayedObject(UUID playerUUID, String playerName) {
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
