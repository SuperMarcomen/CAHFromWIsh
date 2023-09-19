package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.UUID;

public class GameCreatedCommand extends Command {

    private static final Logger logger = LogManager.getLogger();
    private final GameManager gameManager;

    public GameCreatedCommand(PrintWriter out, GameManager gameManager) {
        super(out);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(String input) {
        String uuid = new JSONObject(input)
                .getString("uuid");
        logger.info("Created new game with UUID {}", uuid);
        gameManager.createGameWithUUID(UUID.fromString(uuid));
    }
}
