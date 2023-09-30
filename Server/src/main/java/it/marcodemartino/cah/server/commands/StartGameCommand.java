package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.server.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.UUID;

public class StartGameCommand extends Command {

    private static final Logger logger = LogManager.getLogger(StartGameCommand.class);
    private final GameManager gameManager;

    public StartGameCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(String input) {
        JSONObject object = new JSONObject(input);
        Game game = gameManager.getGame(UUID.fromString(object.getString("game_uuid")));
        game.sendStartCardsToAllPlayer();
        logger.info("Started a game with UUID {}", object.getString("game_uuid"));
    }
}
