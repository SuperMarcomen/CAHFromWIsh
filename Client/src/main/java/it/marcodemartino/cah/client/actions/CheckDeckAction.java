package it.marcodemartino.cah.client.actions;

import com.google.gson.Gson;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.CheckDeckObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class CheckDeckAction implements Action {

    private final String deckName;
    private final UUID playerUUID;
    private final UUID gameUUID;
    private final boolean selected;
    private final Gson gson;
    private final Logger logger = LogManager.getLogger(ChooseDekcsAction.class);

    public CheckDeckAction(String deckName, UUID playerUUID, UUID gameUUID, boolean selected) {
        this.deckName = deckName;
        this.playerUUID = playerUUID;
        this.gameUUID = gameUUID;
        this.selected = selected;
        this.gson = GsonInstance.get();
    }

    @Override
    public String execute() {
        JSONObject jsonObject = new CheckDeckObject(deckName, playerUUID, gameUUID, selected);
        logger.info("Sent request to select the deck called {}", deckName);
        return gson.toJson(jsonObject);
    }
}
