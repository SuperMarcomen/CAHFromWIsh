package it.marcodemartino.cah.client.actions;

import com.google.gson.Gson;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.ChooseDecksObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class ChooseDekcsAction implements Action {

    private final Logger logger = LogManager.getLogger(ChooseDekcsAction.class);
    private final UUID gameUUID;
    private final Gson gson;

    public ChooseDekcsAction(UUID gameUUID) {
        this.gameUUID = gameUUID;
        gson = GsonInstance.get();
    }

    @Override
    public String execute() {
        logger.info("Sending request to choose decks");
        JSONObject jsonObject = new ChooseDecksObject(gameUUID);
        return gson.toJson(jsonObject);
    }
}
