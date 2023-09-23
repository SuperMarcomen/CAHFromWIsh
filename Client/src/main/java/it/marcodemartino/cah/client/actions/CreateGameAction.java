package it.marcodemartino.cah.client.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class CreateGameAction implements Action {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute() {
        logger.info("Sent request to create game");
        return new JSONObject()
                .put("method", "new_game")
                .toString();
    }
}
