package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.game.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.UUID;

public class JoinGameAction implements Action {

    private static final Logger logger = LogManager.getLogger();
    private final Player player;
    private final UUID gameUUID;

    public JoinGameAction(Player player, UUID gameUUID) {
        this.player = player;
        this.gameUUID = gameUUID;
    }

    @Override
    public String execute() {
        return new JSONObject()
                .put("method", "join_game")
                .put("game_uuid", gameUUID)
                .put("player_uuid", player.getUuid())
                .put("player_name", player.getName())
                .toString();
    }

    @Override
    public void handleResponse(String response) {
        String status = new JSONObject(response)
                .getString("status");
        if (status.equals("ok")) {
            logger.info("Player {} with UUID {} joined a game with UUID {}", player.getName(), player.getUuid(), gameUUID.toString());
        } else {
            logger.warn("The response after joining the game {} was: {}", gameUUID.toString(), status);
        }
    }
}
