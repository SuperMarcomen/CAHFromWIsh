package it.marcodemartino.cah.json.client;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

import java.util.UUID;

public class StartGameObject implements JSONObject {

    @SerializedName("method")
    private static final String METHOD = "start_game";
    private final UUID gameUUID;

    public StartGameObject(UUID gameUUID) {
        this.gameUUID = gameUUID;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }
}
