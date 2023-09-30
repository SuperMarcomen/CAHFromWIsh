package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.game.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class QuitAction implements Action {

    private final Logger logger = LogManager.getLogger(QuitAction.class);
    private final GameManager gameManager;

    public QuitAction(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public String execute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("method", "quit");

        if (gameManager.getPlayer() == null) return jsonObject.toString();
        logger.info("Quitting player with UUID {}", gameManager.getPlayer().getUuid());
        jsonObject.put("player_uuid", gameManager.getPlayer().getUuid());
        return jsonObject.toString();
    }
}
