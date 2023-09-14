package it.marcodemartino.cah.client.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.UUID;

public class StartGameAction implements Action {

    private static final Logger logger = LogManager.getLogger();
    private final UUID gameUUID;

    public StartGameAction(UUID gameUUID) {
        this.gameUUID = gameUUID;
    }

    @Override
    public String execute() {
        return new JSONObject()
                .put("method", "start_game")
                .put("game_uuid", gameUUID)
                .toString();
    }

    @Override
    public void handleResponse(String response) {
        String status = new JSONObject(response)
                .getString("status");
        if (status.equals("ok")) {
            logger.info("The game with UUID {} was started", gameUUID.toString());
        } else {
            logger.warn("The response after starting the game {} was: {}", gameUUID.toString(), status);
        }
    }
}
