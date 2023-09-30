package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.server.GameManager;
import it.marcodemartino.cah.server.entity.RemotePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.UUID;

public class JoinGameCommand extends Command {

    private static final Logger logger = LogManager.getLogger(JoinGameCommand.class);
    private final GameManager gameManager;

    public JoinGameCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(String input) {
        JSONObject object = new JSONObject(input);
        Game game = gameManager.getGame(UUID.fromString(object.getString("game_uuid")));
        Player player = new RemotePlayer(object.getString("player_name"), UUID.fromString(object.getString("player_uuid")), out);
        game.addPlayer(player);
        game.notifyPlayerJoin(player);
        logger.info("Player {} with UUID {} joined a game with UUID {}", player.getName(), player.getUuid(), object.getString("game_uuid"));
    }
}
