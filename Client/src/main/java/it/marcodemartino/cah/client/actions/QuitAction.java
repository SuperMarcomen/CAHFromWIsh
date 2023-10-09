package it.marcodemartino.cah.client.actions;

import com.google.gson.Gson;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.QuitObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuitAction implements Action {

    private final Logger logger = LogManager.getLogger(QuitAction.class);
    private final GameManager gameManager;
    private final Gson gson;

    public QuitAction(GameManager gameManager) {
        this.gameManager = gameManager;
        gson = GsonInstance.get();
    }

    @Override
    public String execute() {
        JSONObject jsonObject;

        if (gameManager.getPlayer() != null) {
            jsonObject = new QuitObject(gameManager.getPlayer().getUuid());
        } else {
            jsonObject = new QuitObject(null);
        }

        logger.info("Quitting player with UUID {}", gameManager.getPlayer().getUuid());
        return gson.toJson(jsonObject);
    }
}
