package it.marcodemartino.cah.json.client;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

import java.util.UUID;

public class QuitObject implements JSONObject {

    @SerializedName("method")
    private static final String METHOD = "quit";
    @SerializedName("player_uuid")
    private final UUID playerUUID;

    public QuitObject(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }
}
