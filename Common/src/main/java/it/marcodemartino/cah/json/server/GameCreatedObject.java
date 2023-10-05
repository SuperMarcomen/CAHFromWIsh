package it.marcodemartino.cah.json.server;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

import java.util.UUID;

public class GameCreatedObject implements JSONObject {

    @SerializedName("method")
    private static final String METHOD = "game_created";
    private final UUID uuid;

    public GameCreatedObject(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public UUID getUuid() {
        return uuid;
    }
}
