package it.marcodemartino.cah.json.client;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

import java.util.List;
import java.util.UUID;

public class StartGameObject implements JSONObject {

    @SerializedName("method")
    private final String METHOD = "start_game";
    private final UUID gameUUID;
    private final List<String> decksNames;

    public StartGameObject(UUID gameUUID, List<String> decksNames) {
        this.gameUUID = gameUUID;
        this.decksNames = decksNames;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public UUID getGameUUID() {
        return gameUUID;
    }

    public List<String> getDecksNames() {
        return decksNames;
    }
}
