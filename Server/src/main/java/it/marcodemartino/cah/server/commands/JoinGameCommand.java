package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.JoinResult;
import it.marcodemartino.cah.json.client.JoinGameObject;
import it.marcodemartino.cah.json.server.JoinGameResultObject;
import it.marcodemartino.cah.server.GameManager;
import it.marcodemartino.cah.server.entity.RemotePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class JoinGameCommand extends Command<JoinGameObject> {

    private static final Logger logger = LogManager.getLogger(JoinGameCommand.class);
    private final GameManager gameManager;

    public JoinGameCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out, JoinGameObject.class);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(JoinGameObject object) {
        Game game = gameManager.getGame(object.getGameUUID());
        if (game == null) {
            JSONObject jsonObject = new JoinGameResultObject(JoinResult.NON_EXISTENT);
            send(jsonObject);
            return;
        }

        if (game.isStarted()) {
            JSONObject jsonObject = new JoinGameResultObject(JoinResult.ALREADY_STARTED);
            send(jsonObject);
            return;
        }

        Player playerWhoJoined = new RemotePlayer(object.getPlayerName(), object.getPlayerUUID(), out);
        game.addPlayer(playerWhoJoined);

        game.notifyPlayerJoin(playerWhoJoined);
        game.notifyPlayerAboutEveryone(playerWhoJoined);

        JSONObject jsonObject = new JoinGameResultObject(JoinResult.SUCCESSFUL);
        send(jsonObject);

        logger.info("Player {} with UUID {} joined a game with UUID {}", playerWhoJoined.getName(), playerWhoJoined.getUuid(), object.getGameUUID());
    }
}
