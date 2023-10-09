package it.marcodemartino.cah.client.actions;

import com.google.gson.Gson;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.StartGameObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.UUID;

public class StartGameAction implements Action {

    private static final Logger logger = LogManager.getLogger(StartGameAction.class);
    private final UUID gameUUID;
    private final List<String> decksNames;
    private final Gson gson;

    public StartGameAction(UUID gameUUID, List<String> decksNames) {
        this.gameUUID = gameUUID;
        this.decksNames = decksNames;
        this.gson = GsonInstance.get();
    }

    @Override
    public String execute() {
        logger.info("Sent request to start game");
        JSONObject jsonObject = new StartGameObject(gameUUID, decksNames);
        return gson.toJson(jsonObject);
    }
}
