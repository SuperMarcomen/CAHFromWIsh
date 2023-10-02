package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class JoinGameAction implements Action {

    private static final Logger logger = LogManager.getLogger(JoinGameAction.class);
    private final GameManager gameManager;

    public JoinGameAction(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public String execute() {
        logger.info("Sent request to join game");
        Player player = gameManager.getPlayer();
        gameManager.getGame().setPlayer(player);
        return new JSONObject()
                .put("method", "join_game")
                .put("game_uuid", gameManager.getGame().getUuid())
                .put("player_uuid", player.getUuid())
                .put("player_name", player.getName())
                .toString();
    }
}
