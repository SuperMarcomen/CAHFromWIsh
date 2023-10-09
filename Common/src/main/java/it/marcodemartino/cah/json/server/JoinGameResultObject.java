package it.marcodemartino.cah.json.server;

import com.google.gson.annotations.SerializedName;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.JoinResult;

public class JoinGameResultObject implements JSONObject {

    @SerializedName("method")
    private final String METHOD = "join_game_result";
    @SerializedName("result")
    private final JoinResult joinResult;

    public JoinGameResultObject(JoinResult joinResult) {
        this.joinResult = joinResult;
    }

    @Override
    public String getMethod() {
        return METHOD;
    }

    public JoinResult getJoinResult() {
        return joinResult;
    }
}
