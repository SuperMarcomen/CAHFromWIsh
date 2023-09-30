package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.UUID;

public class JoinGameAction implements Action {

    private static final Logger logger = LogManager.getLogger();
    private final GameManager gameManager;
    private final UUID gameUUID;

    public JoinGameAction(GameManager gameManager, UUID gameUUID) {
        this.gameManager = gameManager;
        this.gameUUID = gameUUID;
    }

    @Override
    public String execute() {
        logger.info("Sent request to join game");
        Player player = gameManager.getPlayer();
        gameManager.createGameWithUUID(gameUUID);
        gameManager.getGame().setPlayer(player);
        return new JSONObject()
                .put("method", "join_game")
                .put("game_uuid", gameUUID)
                .put("player_uuid", player.getUuid())
                .put("player_name", player.getName())
                .toString();
    }
}
