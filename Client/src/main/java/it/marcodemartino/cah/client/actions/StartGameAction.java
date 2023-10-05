package it.marcodemartino.cah.client.actions;

import com.google.gson.Gson;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.StartGameObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class StartGameAction implements Action {

    private static final Logger logger = LogManager.getLogger(StartGameAction.class);
    private final UUID gameUUID;
    private final Gson gson;

    public StartGameAction(UUID gameUUID) {
        this.gameUUID = gameUUID;
        this.gson = new Gson();
    }

    @Override
    public String execute() {
        logger.info("Sent request to start game");
        JSONObject jsonObject = new StartGameObject(gameUUID);
        return gson.toJson(jsonObject);
    }
}
