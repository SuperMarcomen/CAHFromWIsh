package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.game.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class CreateGameAction implements Action {

    private static final Logger logger = LogManager.getLogger();
    private final GameManager gameManager;

    public CreateGameAction(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public String execute() {
        return new JSONObject()
                .put("method", "new_game")
                .toString();
    }
}
