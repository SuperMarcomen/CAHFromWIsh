package it.marcodemartino.cah.client.actions;

import com.google.gson.Gson;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.JoinGameObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JoinGameAction implements Action {

    private static final Logger logger = LogManager.getLogger(JoinGameAction.class);
    private final GameManager gameManager;
    private final Gson gson;

    public JoinGameAction(GameManager gameManager) {
        this.gameManager = gameManager;
        this.gson = GsonInstance.get();
    }

    @Override
    public String execute() {
        logger.info("Sent request to join game");
        Player player = gameManager.getPlayer();
        gameManager.getGame().setPlayer(player);
        JSONObject jsonObject = new JoinGameObject(gameManager.getGame().getUuid(), player.getUuid(), player.getName());
        return gson.toJson(jsonObject);
    }
}
