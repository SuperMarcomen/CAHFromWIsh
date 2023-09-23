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
        logger.info("Sent request to start game");
        return new JSONObject()
                .put("method", "start_game")
                .put("game_uuid", gameUUID)
                .toString();
    }
}
