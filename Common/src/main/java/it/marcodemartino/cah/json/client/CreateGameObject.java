package it.marcodemartino.cah.json.client;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;

public class CreateGameObject implements JSONObject {

    @SerializedName("method")
    private static final String METHOD = "new_game";

    @Override
    public String getMethod() {
        return METHOD;
    }
}
